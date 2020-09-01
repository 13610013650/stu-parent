package com.stu.rocketmq.util;

import com.stu.rocketmq.component.RsaComponent;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @title:
 * @description: Res非对称加解密工具类
 * @author: zhangsheng
 * @create Date: 2020/5/18 9:34
 * @modify User:
 * @modify Date:
 * @modify Description:
 */
public final class RsaUtil {


    private static final Logger logger = LoggerFactory.getLogger(RsaUtil.class);

    private static final String PUBLIC_KEY = "public";

    private static final String PRIVATE_KEY = "private";

    public static class Encryption{
        public static final String RSA = "RSA";
    }

    /**
     * 放置公钥与私钥
     * <p>
     * volatile 保证当前的map在主内存中保存最新值
     */
    private static volatile Map<String, String> keyMap = new HashMap<String, String>(2);



    /**
     * 获取公钥
     *
     * @return
     */
    public static String getPublicKey() {
        String publicKey = keyMap.get(PUBLIC_KEY);
        if (publicKey == null) {
            // 加锁 防止多线程生产多个密钥
            synchronized (keyMap.getClass()) {
                if (publicKey == null) {
                    try {
                        createKeyPair();
                        publicKey = keyMap.get(PUBLIC_KEY);
                    } catch (NoSuchAlgorithmException e) {
                        logger.error("获取随机公私钥错误:{}", e.getMessage());
                    }
                }
            }
        }
        return publicKey;
    }

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    private  static String getPrivateKey() {
        String privateKey = keyMap.get(PRIVATE_KEY);
        if (privateKey == null) {
            logger.error("未获取到私钥...");
        }
        return privateKey;
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public  static void createKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RsaComponent.Encryption.RSA);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(PUBLIC_KEY, publicKeyString);
        keyMap.put(PRIVATE_KEY, privateKeyString);
        logger.info("自动生成的公钥：{}",publicKeyString);
        logger.info("自动生成的私钥：{}",privateKeyString);
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static  String encrypt(String str) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(getPublicKey());
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RsaComponent.Encryption.RSA).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(RsaComponent.Encryption.RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str) {
        String outStr = "";
        // 64位解码加密后的字符串
        byte[] inputByte;
        try {
            inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(getPrivateKey());
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RsaComponent.Encryption.RSA).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance(RsaComponent.Encryption.RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码:{}", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error("未找到找到指定算法:{}", e.getMessage());
        } catch (InvalidKeyException e) {
            logger.error("无效的秘钥:{}", e.getMessage());
        } catch (NoSuchPaddingException e) {
            logger.error("请求特定填充机制, 但该环境中未提供时:{}", e.getMessage());
        } catch (BadPaddingException e) {
            logger.error("预期对输入数据使用特定填充机制, 但未正确填充数据:{}", e.getMessage());
        } catch (InvalidKeySpecException e) {
            logger.error("无效的密钥规范:{}", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            logger.error("非法的块大小:{}", e.getMessage());
        }
        return outStr;
    }


    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        createKeyPair();
        Set<Map.Entry<String, String>> entries = keyMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        //加密字符串
        String message = "d1";
        System.out.println("随机生成的公钥为:" + keyMap.get("public"));
        System.out.println("随机生成的私钥为:" + keyMap.get("private"));
        String messageEn = encrypt(message);
        System.out.println("加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn);
        System.out.println("还原后的字符串为:" + messageDe);
    }

}