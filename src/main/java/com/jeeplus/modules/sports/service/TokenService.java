package com.jeeplus.modules.sports.service;


import com.jeeplus.modules.sports.entity.Device;
import com.jeeplus.modules.sports.entity.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2016/11/15.
 */
public interface TokenService {

        String getToken(Device device);

//        Users getUserByToken(String token);
//
//        int getUserTypeByToken(HttpServletRequest servletRequest);
//
//        boolean hasToken(HttpServletRequest servletRequest);
//
        String getTokenStr(HttpServletRequest request);
//
//        Integer getAppTypeByToken(String token);
//
//        String getCurrentVersionByToken(String token);
//
//        Device getDeviceByToken(String token);
//
        Token findByToken(String token);
//
//        void invalidUserAndToken(Integer userId, String token);
//
        void logoutUser(Integer userId);
//
        Token findByTokenAndStatus(String token, int status);

//
//        Token getTokenByUserIdAndToken(Token token);
//
//        int insert(Token record);
//
//        void updateUserTokenStatus(Token token, int userId);
//
//        int getOnlineTokenNum();

}
