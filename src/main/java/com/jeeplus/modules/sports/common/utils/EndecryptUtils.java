package com.jeeplus.modules.sports.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.jeeplus.modules.sports.entity.Users;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public final class EndecryptUtils
{
  public static Users md5Password(String username, String password)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "account不能为空");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "password不能为空");
    SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
    String salt = secureRandomNumberGenerator.nextBytes().toHex();

    String password_cipherText = new Md5Hash(password, username + salt, 2).toHex();
    Users user = new Users();
    user.setPassword(password_cipherText);
    user.setSalt(salt);
    user.setAccount(username);
    return user;
  }

  public static boolean checkMd5Password(String username, String password, String salt, String md5cipherText)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "username不能为空");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "password不能为空");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(md5cipherText), "md5cipherText不能为空");

    String password_cipherText = new Md5Hash(password, username + salt, 2).toHex();
    return md5cipherText.equals(password_cipherText);
  }
}