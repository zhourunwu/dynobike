package com.jeeplus.modules.sports.common.utils;



import com.jeeplus.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class IpUtil {
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
	
	/**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
   public static String getIp2(HttpServletRequest request) {
       String remoteAddr = request.getRemoteAddr();
       String forwarded = request.getHeader("X-Forwarded-For");
       String realIp = request.getHeader("X-Real-IP");

       String ip = null;
       if (realIp == null) {
           if (forwarded == null) {
               ip = remoteAddr;
           } else {
               ip = remoteAddr + "/" + forwarded.split(",")[0];
           }
       } else {
           if (realIp.equals(forwarded)) {
               ip = realIp;
           } else {
               if(forwarded != null){
                   forwarded = forwarded.split(",")[0];
               }
               ip = realIp + "/" + forwarded;
           }
       }
       return ip;
   }

    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
    public static String getIp1(HttpServletRequest request) {
        String ip_address = request.getHeader("x-forwarded-for");
        if (ip_address == null || ip_address.length() == 0 || "unknown".equalsIgnoreCase(ip_address)) {
            ip_address = request.getHeader("Proxy-Client-IP");
        }
        if (ip_address == null || ip_address.length() == 0 || "unknown".equalsIgnoreCase(ip_address)) {
            ip_address = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip_address == null || ip_address.length() == 0 || "unknown".equalsIgnoreCase(ip_address)) {
            ip_address = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip_address == null || ip_address.length() == 0 || "unknown".equalsIgnoreCase(ip_address)) {
            ip_address = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip_address == null || ip_address.length() == 0 || "unknown".equalsIgnoreCase(ip_address)) {
            ip_address = request.getRemoteAddr();
        }
        if (ip_address.equals("0:0:0:0:0:0:0:1")) {
            ip_address = "127.0.0.1";
        }
        return ip_address;
    }
}
