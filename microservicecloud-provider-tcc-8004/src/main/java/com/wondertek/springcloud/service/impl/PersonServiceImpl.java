package com.wondertek.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wondertek.springcloud.dao.PersonRepository;
import com.wondertek.springcloud.entities.Person;
import com.wondertek.springcloud.entities.User;
import com.wondertek.springcloud.service.PersonService;
import com.wondertek.springcloud.service.UserService;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zbc
 * @Date 21:17-2019/1/23
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    public static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserService userService;

    @Override
    @Compensable(confirmMethod = "confirmSavePerson", cancelMethod = "cancelSavePerson")
    @HystrixCommand(fallbackMethod = "hystrixSavePerson")
    public Map<String, Object> savePerson(Person person) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", false);
        resultMap.put("message", "save person false by save user error");
        log.info("Service--保存person对象");
        User user = new User();
        user.setId(3l);
        user.setName("user03");
        user.setAddress("user-address-03");
        user.setStatus(0);
        Person person1 = personRepository.save(person);
        System.out.println(person1.getId());
        log.info("person对象保存成功，开始保存user对象");
        userService.saveUser(user);
        return resultMap;
    }

    /**
     * 确认操作
     *
     * @param person
     */
    @Override
    public Map<String, Object> confirmSavePerson(Person person) {
        log.info("person confirm doing");
        person.setStatus(1);
        personRepository.save(person);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", false);
        resultMap.put("message", "save person false by save user error");
        return resultMap;
    }

    /**
     * 异常取消操作
     *
     * @param person
     */
    @Override
    public void cancelSavePerson(Person person) {
        log.info("person cancel doing");
        person.setStatus(0);
        personRepository.save(person);
    }

    public Map<String, Object> hystrixSavePerson(Person person) {
        log.info("-----save person error");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", false);
        resultMap.put("message", "save person false by save user error");
        return resultMap;
    }
}
