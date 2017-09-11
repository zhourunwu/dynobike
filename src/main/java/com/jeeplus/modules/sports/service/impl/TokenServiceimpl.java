package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.modules.sports.common.CustomHttpHeaderConst;
import com.jeeplus.modules.sports.common.TokenConstants;
import com.jeeplus.modules.sports.common.utils.StringUtil;
import com.jeeplus.modules.sports.dao.DeviceDao;
import com.jeeplus.modules.sports.dao.TokenDao;
import com.jeeplus.modules.sports.dao.UsersDao;
import com.jeeplus.modules.sports.entity.Device;
import com.jeeplus.modules.sports.entity.Token;
import com.jeeplus.modules.sports.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by DELL on 2017/7/27.
 */
@Service
@Transactional
public class TokenServiceimpl implements TokenService {

    @Resource
    TokenDao tokenDao;
    @Resource
    DeviceDao deviceDao;
    @Resource
    UsersDao usersDao;


    public String getToken(Device device) {
        if(device == null || StringUtil.isEmpty(device.getUuid())){
            return null;
        }
        String tokenStr = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        Device dev = deviceDao.getByUuid(device.getUuid());
        if (dev == null) {

            Device _device = new Device();
            _device.setUuid(device.getUuid());
            _device.setTypeId(device.getTypeId());
            _device.setManufacturerId(device.getManufacturerId());
            _device.setCreateTime(new Date());
            _device.setNote(device.getNote());
            _device.setToken(tokenStr);
            deviceDao.insertSelective(_device);

            Token token = new Token();
            token.setCreateTime(new Date());
            token.setStatus(TokenConstants.NOT_BIND);
            token.setToken(tokenStr);
            tokenDao.insertSelective(token);

        } else{
            tokenStr = dev.getToken();
        }

        return tokenStr;
    }


    public String getTokenStr(HttpServletRequest request) {
        return request.getHeader(CustomHttpHeaderConst.Token);
    }

    public Token findByToken(String token) {
        return tokenDao.findByToken(token);
    }

    public boolean hasToken(HttpServletRequest servletRequest) {
        String token = getTokenStr(servletRequest);
        if (token == null)
            return false;
        else
            return true;
    }

    public void logoutUser(Integer userId) {
        tokenDao.logoutUser(userId);
    }

    public Token findByTokenAndStatus(String token, int status) {
        return tokenDao.findByTokenAndStatus(token,status);
    }

}
