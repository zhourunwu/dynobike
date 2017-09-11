package com.jeeplus.modules.sports.restcontroller;

import com.jeeplus.modules.sports.common.CustomHttpHeaderConst;
import com.jeeplus.modules.sports.common.ReturnModel;
import com.jeeplus.modules.sports.common.TokenConstants;
import com.jeeplus.modules.sports.common.utils.StringUtil;
import com.jeeplus.modules.sports.entity.*;
import com.jeeplus.modules.sports.service.*;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/7/26.
 */
@Scope("prototype")
@Controller
@RequestMapping(value = "${adminPath}/api/v1")
public class RestController {

    protected Logger log = Logger.getLogger(getClass());

    @Resource
    private UsersService usersService;
    @Resource
    private TokenService tokenService;
    @Resource
    private DeviceService deviceService;
    @Resource
    private MessageService messageService;
    @Resource
    private UserSportInfoService userSportInfoService;
    @Resource
    private ConnectionService connectionService;


    /**
     * 匿名访问的URI
     */
//    final String baseUrl = "http://IP-Address:Port/dynobike/a";
    final String baseUrl = "/api/v1";
    final String [] anonymousUri ={
            baseUrl+"/token"
    };
    final String [] anonymousToken ={
            baseUrl+"/login",
            baseUrl+"/register"
    };

    /**
     * 判断是否匿名访问的URI,无需token
     * @param uri
     * @return
     */
    private boolean isAnonymousUri(String uri)
    {
        for (int i=0;i< anonymousUri.length;i++)
        {
            if(uri.endsWith(anonymousUri[i]))
                return true;
        }
        return false;
    }

    /**
     * 判断是否匿名访问的URI,无需token
     * @param uri
     * @return
     */
    private boolean isAnonymousToken(String uri)
    {
        for (int i=0;i< anonymousToken.length;i++)
        {
            if(uri.endsWith(anonymousToken[i]))
                return true;
        }
        return false;
    }

    private Map<String,String> checkBaseStatus(HttpServletRequest request,
                                               HttpServletResponse response, Boolean loginAction) {
        String requestUri = request.getRequestURI();
        String tokenStr = request.getHeader(CustomHttpHeaderConst.Token);

        Map<String,String> result = new HashMap<String, String>();
        if(!isAnonymousUri(requestUri)){
            //注册、重置密码和获取验证码时，如果有验证码，则需要判断验证码是否时伪造的
            if (!StringUtil.isEmpty(tokenStr)) {
                Device device= deviceService.getDeviceByToken(tokenStr);
                if (device==null) {
                    log.error(String.format("token在数据库不存在,token is %s", tokenStr));
                    result.put("tokenStatus",String.valueOf(TokenConstants.TOKEN_NOT_EXIST));
                    result.put("msg","token不存在");
                    return result;
                }
                // 一个设备可以关联多个用户，所以一个token在token表中会有多条纪录，设备和用户之间是多对多的关系
                Token token = tokenService.findByToken(tokenStr);
                if(token != null){
                  /*  if(token.getStatus() == TokenConstants.VALID){
                        result.put("tokenStatus",String.valueOf(TokenConstants.VALID));
                        Users user = tokenService.getUserByToken(tokenStr);
                        if(user == null || user.getStatus() != Constants.USER_STATUS_EFFICTIVE){
                            // 排除账号停用的情况
                            result.put("tokenStatus",String.valueOf(TokenConstants.BLACKLIST));
                            result.put("msg","用户账号无效，状态："+ (user.getStatus() == Constants.USER_STATUS_UNRECOGNIZE?"未激活":user.getStatus() == Constants.USER_STATUS_DELETE?"已删除":"已停用"));
                            return result;
                        }
                    }
                    else */if (token.getStatus() == TokenConstants.LOGOUT) {
                        if(!isAnonymousToken(requestUri)) {
                            result.put("tokenStatus", String.valueOf(TokenConstants.LOGOUT));
                            if(!loginAction){
                                result.put("msg", "token已登出");
                                return result;
                            }
                        }
                    } else if (token.getStatus() == TokenConstants.NOT_BIND) {
                        if(!isAnonymousToken(requestUri)){
                            result.put("tokenStatus",String.valueOf(TokenConstants.NOT_BIND));
                            result.put("msg","token未绑定");
                            return result;
                        }
                    } else if (token.getStatus() == TokenConstants.EXPIRED) {
                        if(!isAnonymousToken(requestUri)) {
                            result.put("tokenStatus", String.valueOf(TokenConstants.EXPIRED));
                            result.put("msg", "token已过期");
                            return result;
                        }
                    } else if (token.getStatus() == TokenConstants.BLACKLIST) {
                        result.put("tokenStatus",String.valueOf(TokenConstants.BLACKLIST));
                        result.put("msg","token在黑名单中");
                        return result;
                    } else if (token.getStatus() == TokenConstants.NEED_RELOGIN) {
                        if(!isAnonymousToken(requestUri)) {
                            result.put("tokenStatus", String.valueOf(TokenConstants.NEED_RELOGIN));
                            if(!loginAction){
                                result.put("msg", "该用户已在其他设备登录，token需重登陆");
                                return result;
                            }
                        }
                    }
                }
                else{
                    result.put("tokenStatus",String.valueOf(TokenConstants.TOKEN_NOT_EXIST));
                    result.put("msg","token不存在");
                    return result;
                }
            }
            else{
                result.put("tokenStatus",String.valueOf(TokenConstants.TOKEN_NOT_EXIST));
                result.put("msg","token不存在");
                return result;
            }
        }
        return result;
    }

    /**
     * 获取 Token 信息
//     * @param uuid  当前登录App的设备信息，唯一标识
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/token", produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel<String> getTokenInfo(
//            @RequestParam(value = "uuid", required = true) String uuid,
            @RequestBody Device device,
            HttpServletRequest request, HttpServletResponse response){
        ReturnModel<String> json = new ReturnModel<String>();
        json.setCode(new Integer(201));
        json.setMessage("获取Token失败!");
        try {
//            if(StringUtil.isEmpty(uuid)){
//                json.setMessage("传入设备唯一标识错误，内容为空!");
//                return json;
//            }
//            Device device = new Device();
//            device.setUuid(uuid);
            String token = tokenService.getToken(device);
            if(!StringUtil.isEmpty(token)) {
                json.setCode(new Integer(200));
                json.setData(token);
                json.setJsonType("String");
                json.setMessage("设备Token认证成功");
            }
        } catch (Exception ex) {
            json.setData(null);
            json.setMessage(ex.getMessage());
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 注销登录
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logOut",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel<String> logoOut(
            @RequestBody Device device,HttpServletRequest request, HttpServletResponse response){
        ReturnModel json = new ReturnModel();
        json.setCode(TokenConstants.fail);
        json.setMessage("登出失败");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
            if(!StringUtil.isEmpty(baseMsg)){
                json.setMessage(baseMsg);
                return json;
            }
            String token = tokenService.getTokenStr(request);
            //验证传入的token值是否有效
            try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                Token currentToken = tokenService.findByTokenAndStatus(token, 0);
                json.setCode(TokenConstants.success);
                if (currentToken != null) {
                    tokenService.logoutUser(currentToken.getUserId());
                    json.setMessage("登出成功!");
                } else {
                    json.setMessage("当前状态已登出!");
                }
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("登出失败，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * 注册
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel<String> register(@RequestBody Users users, HttpServletRequest request, HttpServletResponse response){
        ReturnModel<String> json = new ReturnModel<String>();
        json.setCode(TokenConstants.fail);
        json.setMessage("用户注册失败！");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
        if(!StringUtil.isEmpty(baseMsg)){
            json.setMessage(baseMsg);
            return json;
        }
        String token = tokenService.getTokenStr(request);
        //验证传入的token值是否有效
        try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                json.setCode(TokenConstants.success);
                ReturnModel js = usersService.saveOrUpdateUser(users);
                if(!StringUtil.isEmpty(js.getMessage())){
                    return js;
                }
                //返回用户UUID
                json.setData(js.getData().toString());
                json.setMessage("用户注册成功!");
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("用户注册失败，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * 获取设备初始预设指令信息
     * @param device 设备唯一标示
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMessage",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel<List<Message>> getMessage(@RequestBody Device device, HttpServletRequest request, HttpServletResponse response){
        ReturnModel<List<Message>> json = new ReturnModel<List<Message>>();
        json.setCode(TokenConstants.fail);
        json.setMessage("获取设备消息失败！");
        json.setJsonType("List<Message>");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
        if(!StringUtil.isEmpty(baseMsg)){
            json.setMessage(baseMsg);
            return json;
        }
        String token = tokenService.getTokenStr(request);
        //验证传入的token值是否有效
        try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                Token currentToken = tokenService.findByTokenAndStatus(token, 0);
                json.setCode(TokenConstants.success);
                if (currentToken != null) {
                    json.setData(messageService.getMessageByDeviceUuid(device.getUuid()));
                    json.setMessage("获取消息成功!");
                }
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("获取设备消息失败！，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * 客户端推送消息
     * @param message 推送消息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pushMessage",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel pushMessage(@RequestBody Message message, HttpServletRequest request, HttpServletResponse response){
        ReturnModel json = new ReturnModel();
        json.setCode(TokenConstants.fail);
        json.setMessage("设备推送消息失败！");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
        if(!StringUtil.isEmpty(baseMsg)){
            json.setMessage(baseMsg);
            return json;
        }
        String token = tokenService.getTokenStr(request);
        //验证传入的token值是否有效
        try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                Token currentToken = tokenService.findByTokenAndStatus(token, 0);
                json.setCode(TokenConstants.success);
                if (currentToken != null) {
                    message.setTargetType(3);
                    Device device = new Device();
                    device.setUuid(message.getDeviceUuid());
                    device = deviceService.getDeviceByUuid(device);
                    if (device == null){
                        json.setMessage(json.getMessage()+"：没有找到此设备！");
                        return json;
                    }
                    message.setTargetId(device.getId());
                    messageService.pushMessage(message);
                    json.setMessage("设备推送消息成功!");
                }
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("设备推送消息失败！，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * 登录
     * @param user  当前登录App的账号和密码
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel login(@RequestBody Users user, HttpServletRequest request, HttpServletResponse response){
        ReturnModel json = new ReturnModel();
        json.setCode(TokenConstants.fail);
        json.setMessage("登录失败!");
        try {
            if(StringUtil.isEmpty(user.getAccount())){
                json.setMessage("用户名不能为空!");
            }else if(StringUtil.isEmpty(user.getPassword())){
                json.setMessage("密码不能为空!");
            }else{
                Map<String,String> resultInfo = checkBaseStatus(request,response,false);
                String baseMsg = resultInfo.get("msg");
                String tokenStatus = resultInfo.get("tokenStatus");
                if(!StringUtil.isEmpty(baseMsg)){
                    json.setMessage(baseMsg);
                    return json;
                }
                String tokenStr = tokenService.getTokenStr(request);
               return usersService.login(user,tokenStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMessage("用户登录失败失败！，详细："+e.getMessage());
        }
        return json;
    }

    /**
     * 获取用户最新一次的运动信息
     * @param users 用户唯一标示
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sports",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel<UserSportInfo> sports(@RequestBody Users users, HttpServletRequest request, HttpServletResponse response){
        ReturnModel<UserSportInfo> json = new ReturnModel<UserSportInfo>();
        json.setCode(TokenConstants.fail);
        json.setMessage("获取用户运动信息失败！");
        json.setJsonType("UserSportInfo");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
        if(!StringUtil.isEmpty(baseMsg)){
            json.setMessage(baseMsg);
            return json;
        }
        String token = tokenService.getTokenStr(request);
        //验证传入的token值是否有效
        try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                Token currentToken = tokenService.findByTokenAndStatus(token, 0);
                json.setCode(TokenConstants.success);
                if (currentToken != null) {
                    json.setData(userSportInfoService.getLastUserSportInfo(users));
                    json.setMessage("用户运动信息成功!");
                }
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("获取用户运动失败！，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * 用户运动信息推送
     * @param userSportInfo 用户运动信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sportsBeat",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel sportsBeat(@RequestBody UserSportInfo userSportInfo, HttpServletRequest request, HttpServletResponse response){
        ReturnModel json = new ReturnModel();
        json.setCode(TokenConstants.fail);
        json.setMessage("用户运动信息推送失败！");
        json.setJsonType("userSportInfo");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
        if(!StringUtil.isEmpty(baseMsg)){
            json.setMessage(baseMsg);
            return json;
        }
        String token = tokenService.getTokenStr(request);
        //验证传入的token值是否有效
        try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                Token currentToken = tokenService.findByTokenAndStatus(token, 0);
                json.setCode(TokenConstants.success);
                if (currentToken != null) {
                    userSportInfo.setUserId(currentToken.getUserId());
                    //根据token获取设备Id，所有者Id
                    Device device = deviceService.getDeviceByToken(token);
                    if (device != null) {
                        userSportInfo.setDeviceId(device.getId());
                        if (device.getOwnerId() != null) {
                            userSportInfo.setOwnerId(device.getOwnerId());
                        }
                    }
                    userSportInfoService.saveOrUpdateUserSport(userSportInfo);
                    json.setMessage("用户运动信息推送成功!");
                }
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("用户运动信息推送失败！，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * 用户运动信息推送
     * @param device 心跳信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/heartBeat",  produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
    public ReturnModel sportsBeat(@RequestBody Device device, HttpServletRequest request, HttpServletResponse response){
        ReturnModel json = new ReturnModel();
        String ip = getClientIpAddress(request);
        System.out.print(ip);
        json.setCode(TokenConstants.fail);
        json.setMessage("心跳信息推送失败！");
        Map<String,String> resultInfo = checkBaseStatus(request,response,false);
        String baseMsg = resultInfo.get("msg");
        String tokenStatus = resultInfo.get("tokenStatus");
        if(!StringUtil.isEmpty(baseMsg)){
            json.setMessage(baseMsg);
            return json;
        }
        String token = tokenService.getTokenStr(request);
        //验证传入的token值是否有效
        try {
            if (StringUtil.isEmpty(token)) {
                json.setMessage("状态token无效!");
            } else {
                Token currentToken = tokenService.findByTokenAndStatus(token, 0);
                json.setCode(TokenConstants.success);
                if (currentToken != null) {
                    device = deviceService.getDeviceByUuid(device);
                    if (device == null){
                        json.setMessage(json.getMessage()+"：没有找到此设备！");
                        return json;
                    }
                    Connection connection = new Connection();
                    connection.setIp(getClientIpAddress(request));
                    connection.setDeviceId(device.getId());
                    connectionService.saveOrUpdateConnection(connection);
                    json.setMessage("心跳信息推送成功!");
                }
            }
        } catch (Exception exception) {
            json.setCode(TokenConstants.error);
            json.setMessage("心跳信息推送失败！，详细："+exception.getMessage());
        }
        return json;
    }

    /**
     * ip获取
     *
     * @param request
     * @return
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String clientIp = request.getHeader("x-forwarded-for");
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
