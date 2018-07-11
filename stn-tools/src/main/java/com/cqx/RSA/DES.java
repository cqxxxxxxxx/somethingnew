package com.cqx.RSA;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * Created by BG307435 on 2018/3/5.
 */
public class DES {

    private String src = "Hello,sahadev!";

    @Test
    public void aaa() {

        try {
//以DES的方式初始化Key生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);// 设置密钥的长度为56位
// 生成一个Key
            SecretKey generateKey = keyGenerator.generateKey();
// 转变为字节数组
            byte[] encoded = generateKey.getEncoded();
            String str = String.valueOf(encoded);
            System.out.println("String Key:  " + str);
// 生成密钥字符串
            String encodeHexString = Hex.encodeHexString(encoded);
            System.out.println("Key ： " + encodeHexString);
// 再把我们的字符串转变为字节数组，可以用于另一方使用，验证
            byte[] decodeHex = Hex.decodeHex(encodeHexString.toCharArray());
// 生成密钥对象
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodeHex, "DES");

// 获取加解密实例
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
// 初始化加密模式
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
// 加密
            byte[] doFinal = cipher.doFinal(src.getBytes());
            System.out.println("加密结果 : " + new HexBinaryAdapter().marshal(doFinal));

// 初始化解密模式
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
// 解密
            byte[] doFinal2 = cipher.doFinal(doFinal);
// 输出解密结果
            System.out.println("解密结果 : " + new String(doFinal2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}