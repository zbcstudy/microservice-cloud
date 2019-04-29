package com.wondertek.springcloud;

import com.alibaba.druid.pool.DruidDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by win on 2019/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DeptProvider8002_App.class)
@WebAppConfiguration
public class EncryptorTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testGetPassword() {
        DruidDataSource druidDataSource = applicationContext.getBean(DruidDataSource.class);
        System.out.println(druidDataSource.getUsername());
        System.out.println(druidDataSource.getPassword());
    }

    @Test
    public void testEncrypt() {
        System.out.println(stringEncryptor.encrypt("root"));
        System.out.println(stringEncryptor.encrypt("123456"));
    }
}
