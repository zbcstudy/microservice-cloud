package com.wondertek.springcloud;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.junit.Test;

/**
 * Created by win on 2019/4/29.
 */
public class JasyptTest {

    @Test
    public void testJasyptEncrypt() {
        StandardPBEStringEncryptor encryptor = getEncryptor();
        String encrypt = encryptor.encrypt("root");
        System.out.println("root加密之后：" + encrypt);
    }

    @Test
    public void testJasyptDecrypt() {
        StandardPBEStringEncryptor encryptor = getEncryptor();
        String decrypt = encryptor.decrypt("Ga0PlOGX2zvzC8lfjYDlUw==");
        System.out.println("解密之后的值为：" + decrypt);
    }

    public static StandardPBEStringEncryptor getEncryptor() {
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //salt
        config.setPassword("abc");
        encryptor.setConfig(config);
        return encryptor;
    }
}
