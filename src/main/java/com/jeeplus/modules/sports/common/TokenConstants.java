package com.jeeplus.modules.sports.common;

/**
 * Created by sam on 2016/3/21.
 */
public interface TokenConstants {

    int VALID = 0;
    int EXPIRED = 1; //token已过期
    int BLACKLIST = 2; //token在黑名单中
    int NOT_BIND = 3; //token未绑定
    int LOGOUT = 4; //token已登出
    int NEED_RELOGIN = 5; //该用户已在其他设备登录，token需重登陆
    int TOKEN_NOT_EXIST = 6; //token不存在

    int success = 200; //成功
    int fail = 201; //失败
    int error = 202; //异常


}
