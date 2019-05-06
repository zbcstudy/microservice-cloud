package com.wondertek.springcloud.redisson.operation;

import org.redisson.api.RBinaryStream;
import org.redisson.api.RListMultimap;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 操作对象为二进制
 * Created by win on 2019/5/6.
 */
public class RedissonBinary {

    @Resource
    RedissonClient redissonClient;

    /**
     * 获取输出流
     * @param name
     * @return
     */
    public OutputStream getOutputStream(String name) {
        RListMultimap<Object, Object> listMultimap = redissonClient.getListMultimap("");
        RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
        return binaryStream.getOutputStream();
    }

    /**
     * 获取输入流
     * @param name
     * @return
     */
    public InputStream getInputStream(String name) {
        RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
        return binaryStream.getInputStream();
    }

    public InputStream getValue(String name, OutputStream outputStream) {
        try {
            RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
            InputStream inputStream = binaryStream.getInputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
            return binaryStream.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("【RedissonBinary.getValue()】error");
        }
    }

    /**
     * 设置对象的值
     * @param name 键
     * @param inputStream 值
     */
    public void setValue(String name, InputStream inputStream) {
        try {
            RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
            binaryStream.delete();
            OutputStream outputStream = binaryStream.getOutputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException("【RedissonBinary.setValue()】error");
        }
    }

    public Boolean delete(String name) {
        RBinaryStream binaryStream = redissonClient.getBinaryStream(name);
        return binaryStream.delete();
    }
}
