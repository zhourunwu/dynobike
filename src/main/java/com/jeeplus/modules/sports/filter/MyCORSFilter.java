package com.jeeplus.modules.sports.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component 
//public class MyCORSFilter implements Filter  {
//
//	/**
//	 * 
//	 */
//	@Override 
//	public void init(FilterConfig filterConfig) throws ServletException 
//	{ 
//		
//	}
//	
//	/**
//	 * 
//	 */
//	@Override 
//	public void destroy() 
//	{ 
//		
//	}
//
//	/**
//	 * 
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		HttpServletResponse Response = (HttpServletResponse)response; 
////		String origin = (String)request.getRemoteHost()+":"  + request.getRemotePort();
//		
//		Response.setHeader("Access-Control-Allow-Origin", "*"); 
//		Response.setHeader("Access-Control-Allow-Methods", "HEAD, GET, POST, PUT, DELETE, OPTIONS"); 
//		Response.setHeader("Access-Control-Max-Age", "3600"); 
//		Response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type,if-modified-since,Origin,Accept"); 
//		Response.setHeader("Access-Control-Allow-Credentials","true"); 
//		
//		chain.doFilter(request, response); 				
//	} 	
//}

/**
 * Created by kazaff on 2014/12/1.
 */
public class MyCORSFilter extends OncePerRequestFilter{

    //private static final Log LOG = LogFactory.getLog(CORSFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	
        response.addHeader("Access-Control-Allow-Origin", "*");

        if	(request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) 
        {
        	response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
            response.addHeader("Access-Control-Max-Age", "120");
        }

        filterChain.doFilter(request, response);
    }
}
