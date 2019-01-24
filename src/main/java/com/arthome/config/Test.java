package com.arthome.config;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * ClassName Test
 * Description
 * Author  Mr.Jangni
 * Date 2019/1/20 1:36
 * Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        DefaultHashService hashService = new DefaultHashService();
        //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-256");
        //是否生成公盐，默认false
        hashService.setGeneratePublicSalt(false);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
               .setAlgorithmName("SHA-512")
                .setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123"))
                .setIterations(1).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }
}
