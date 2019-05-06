package com.wondertek.springcloud.redisson.aop;

import com.wondertek.springcloud.redisson.LockException;
import com.wondertek.springcloud.redisson.annotation.Lock;
import com.wondertek.springcloud.redisson.enums.LockModel;
import com.wondertek.springcloud.redisson.properties.RedissonProperties;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by win on 2019/5/6.
 */
@Aspect
public class LockAop {
    public static final Logger log = LoggerFactory.getLogger(LockAop.class);

    @Autowired
    RedissonProperties redissonProperties;

    @Autowired
    RedissonClient redissonClient;

    @Pointcut("@annotation(lock)")
    public void controllerAspect(Lock lock) {
    }

    /**
     * 通过spring Spel 获取参数
     *
     * @param key            定义的key值 以#开头 例如:#user
     * @param parameterNames 形参
     * @param values         形参值
     * @return
     */
    public String getValueBySpel(String key, String[] parameterNames, Object[] values) {
        if (!key.contains("#")) {
            String s = "redisson:lock" + key;
            log.info("没有使用Spel表达式value-->{}" + s);
            return s;
        }
        //Spel表达式解析
        ExpressionParser parser = new SpelExpressionParser();
        //Spel上下文
        EvaluationContext context = new StandardEvaluationContext();
        int length = parameterNames.length;
        for (int i = 0; i < length; i++) {
            context.setVariable(parameterNames[i], values[i]);
        }
        Expression expression = parser.parseExpression(key);
        Object value = expression.getValue(context);
        if (value != null) {
            String s = "redisson:lock" + value.toString();
            return s;
        }
        return "redisson:lock";
    }

    @Around("controllerAspect(lock)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, Lock lock) {
        String[] keys = lock.keys();
        if (keys.length == 0) {
            throw new RuntimeException("keys不能为空");
        }
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        Object[] args = joinPoint.getArgs();

        long attemptTimeout = lock.attemptTimeout();
        if (attemptTimeout == 0) {
            attemptTimeout = redissonProperties.getAttemptTimeout();
        }

        long lockWatchdogTimeout = lock.lockWatchdogTimeout();
        if (lockWatchdogTimeout == 0) {
            lockWatchdogTimeout = redissonProperties.getLockWatchdogTimeout();
        }

        //确定锁的模型
        LockModel lockModel = lock.lockModel();
        if (lockModel.equals(LockModel.AUTO)) {
            LockModel lockModel1 = redissonProperties.getLockModel();
            if (lockModel1 != null) {
                lockModel = lockModel1;
            } else if (keys.length > 1) {
                lockModel = LockModel.MULTIPLE;
            } else {
                lockModel = LockModel.REENTRANT;
            }
        }

        if (!lockModel.equals(LockModel.MULTIPLE) && !lockModel.equals(LockModel.REDLOCK) && keys.length > 1) {
            throw new RuntimeException("参数有多个,锁模式为->" + lockModel.name() + ".无法锁定");
        }
        log.info("锁模式->{},等待时间->{}秒,锁定最长时间->{}秒", lockModel.name(), attemptTimeout / 1000, lockWatchdogTimeout / 1000);

        boolean res = false;
        RLock rLock = null;
        //一直等待加锁
        switch (lockModel) {
            case FAIR:
                rLock = redissonClient.getFairLock(getValueBySpel(keys[0], parameterNames, args));
                break;
            case REDLOCK:
                RLock[] rLocks = new RLock[keys.length];
                int index = 0;
                for (String key : keys) {
                    rLocks[index++] = redissonClient.getLock(getValueBySpel(key, parameterNames, args));
                }
                rLock = new RedissonRedLock(rLocks);
                break;
            case MULTIPLE:
                RLock[] locks = new RLock[keys.length];
                int i = 0;
                for (String key : keys) {
                    locks[i++] = redissonClient.getLock(getValueBySpel(key, parameterNames, args));
                }
                rLock = new RedissonMultiLock(locks);
                break;
            case REENTRANT:
                rLock = redissonClient.getLock(getValueBySpel(keys[0], parameterNames, args));
                break;
            case READ:
                RReadWriteLock rReadWriteLock = redissonClient.getReadWriteLock(getValueBySpel(keys[0], parameterNames, args));
                rLock = rReadWriteLock.readLock();
                break;
            case WRITE:
                RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(getValueBySpel(keys[0], parameterNames, args));
                rLock = readWriteLock.writeLock();
                break;
        }
        //执行aop
        if (rLock != null) {
            try {
                if (attemptTimeout == -1) {
                    res = true;
                    //一直等待加锁
                    rLock.lock(attemptTimeout, TimeUnit.MILLISECONDS);
                } else {
                    res = rLock.tryLock(attemptTimeout, lockWatchdogTimeout, TimeUnit.MILLISECONDS);
                }

                if (res) {
                    Object result = joinPoint.proceed();
                    return result;
                } else {
                    throw new LockException("获取锁失败");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }finally {
                if (res) {
                    rLock.unlock();
                }
            }
        }

        throw new LockException("获取锁失败");
    }

}
