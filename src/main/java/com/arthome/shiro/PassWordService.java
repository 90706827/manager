package com.arthome.shiro;

import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * ClassName PassWordService
 * Description 自定义密码加密规则
 * Author Mr.Jangni
 * Date 2019/1/31 21:20
 * Version 1.0
 **/
@Component
public class PassWordService {

    private DefaultHashService defaultHashService = new DefaultHashService();
   
    /**
     * Author Mr.Jangni
     * Description
     * Date 2019/1/31 21:44
     * Param [passWord, salt] 密码明文 加盐
     * Return java.lang.String
     **/
    public String encryptPassword(String passWord, String salt) throws IllegalArgumentException {
//        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
//        hashService.setHashAlgorithmName("SHA-512");
//        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
//        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
//        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
//        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("SHA-512")
                .setSource(ByteSource.Util.bytes(passWord))
                .setSalt(ByteSource.Util.bytes(salt))
                .setIterations(2)
                .build();
        return defaultHashService.computeHash(request).toHex();
    }
    /**
     * Author Mr.Jangni
     * Description 验证密码正确
     * Date 2019/1/31 22:48
     * Param [passWord, salt, cipherText]
     * Return boolean  相同 true
     **/
    public boolean passwordsMatch(String passWord, String salt,String cipherText){
       return cipherText.equals(encryptPassword(passWord,salt));
    }


    public static void main(String[] args) {
        PassWordService passWordService = new PassWordService();
        System.out.println(passWordService.encryptPassword(new Md5Hash("admin").toString(),"12345"));
    }
}