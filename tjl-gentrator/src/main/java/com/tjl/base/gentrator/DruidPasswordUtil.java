package com.tjl.base.gentrator;

import com.alibaba.druid.filter.config.ConfigTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 数据库连接 加解密
 * @author tjl
 * @Type DruidPasswordUtil
 * @date 2019/12/13 14:45
 * @Version 1.0
 */
public class DruidPasswordUtil {
    private static Logger logger = LoggerFactory.getLogger(DruidPasswordUtil.class);
    public static final String MAP_PUBLIC_KEY = "publicKey";
    public static final String MAP_PRIVATE_KEY = "privateKey";
    public static final String MAP_PASSWORD = "password";

    public DruidPasswordUtil() {
    }

    public static Map<String, String> encrypt(String password) {
        HashMap encryptResult = new HashMap(3);
        try {
            String[] arr = ConfigTools.genKeyPair(512);
            encryptResult.put(MAP_PUBLIC_KEY, arr[0]);
            encryptResult.put(MAP_PRIVATE_KEY, arr[1]);
            encryptResult.put(MAP_PASSWORD, ConfigTools.encrypt(arr[0], password));
            return encryptResult;
        } catch (Exception var4) {
            logger.error("druid encrypt error !", var4);
            return null;
        }
    }

    public static String decrypt(String publicKey, String encryptPassword) {
        try {
            return ConfigTools.decrypt(publicKey, encryptPassword);
        } catch (Exception var3) {
            logger.error("druid decrypt error !", var3);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(decrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIJyeV//uaNUdsujwDG/jZOCo0H8IULc9brfJPJqj+gwF+8muPA2rgZc/NFQSjfNAKGaMj7ipRnybIt9xsj1H4sCAwEAAQ==",
                "HMvv4QVjoUryC3kW3VKWvPzGqoNKK4hS2/XM+1tXD1iIXxmUE6BCv363OCU+7Y5lqXm8KNGfNb2PoxZgCFIn8g=="));
    }
}
