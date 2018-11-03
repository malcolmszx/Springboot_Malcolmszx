package com.kingdee.abc.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


public class RSAUtil {
	
	/** 指定加密算法为RSA */
    private static String ALGORITHM = "RSA";
    /*指定加密模式和填充方式*/
    private static String ALGORITHM_MODEL = "RSA/ECB/PKCS1Padding";
    /** 指定key的大小，一般为1024，越大安全性越高 */
    private static int KEYSIZE = 1024;
    /** 指定公钥存放文件 */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /** 指定私钥存放文件 */
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    /**
     * 生成密钥对
     */
    private static void generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        /** 用对象流将生成的密钥写入文件 */
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
                PUBLIC_KEY_FILE));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
                PRIVATE_KEY_FILE));
        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);
        /** 清空缓存，关闭文件输出流 */
        oos1.close();
        oos2.close();
    }

    /**
     * 加密方法 source： 源数据
     */
    public static byte[] encrypt(String source) throws Exception {

        /** 将文件中的公钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                PUBLIC_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODEL);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return b1;
    }

    /**
     * 解密算法 cryptograph:密文
     */
    public static String decrypt(byte[] cryptograph) throws Exception {
        /** 将文件中的私钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                PRIVATE_KEY_FILE));
        Key key = (Key) ois.readObject();
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODEL);
        cipher.init(Cipher.DECRYPT_MODE, key);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(cryptograph);
        return new String(b);
    }
    
    
    //以base64编码密钥
    public static Map<String ,String> generateKeyPair1() throws Exception{
        SecureRandom sr = new SecureRandom();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(KEYSIZE, sr);
        KeyPair kp = kpg.generateKeyPair();
        Key publicKey = kp.getPublic();
        Key privateKey = kp.getPrivate();
        byte[] pb = publicKey.getEncoded();
        String pbStr =  new String(new Base64().encode(pb));
        byte[] pr = privateKey.getEncoded();
        String prStr =  new String(new Base64().encode(pr));
        Map<String, String> map = new HashMap<String, String>();
        map.put("publicKey",pbStr);
        map.put("privateKey", prStr);
        return map;
    }


    public static void main(String[] args) throws Exception {
    	
    	generateKeyPair1();
    	
        generateKeyPair();//生成密钥对
        String source = "Hello World, Hell0 , Wang Yuan ";// 要加密的字符串
        byte[] cryptograph = encrypt(source);// 生成的密文

        //可以将密文进行base64编码进行传输
        System.out.println(new String(Base64.decodeBase64(cryptograph)));
        String target = decrypt(cryptograph);// 解密密文
        System.out.println(target);
    }

	
	
	

}
