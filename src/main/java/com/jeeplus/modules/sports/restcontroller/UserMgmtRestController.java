package com.jeeplus.modules.sports.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeeplus.modules.sports.entity.BikeUser;
import com.jeeplus.modules.sports.entity.ReturnResult;


@RestController
@RequestMapping(value="/user")
public class UserMgmtRestController {

	@RequestMapping(value="/AddUser", method=RequestMethod.POST)
	public @ResponseBody ReturnResult AddUser(
			@RequestBody	BikeUser bikeUser,
			HttpServletRequest request, 
			HttpServletResponse response)
	{
		ReturnResult	result = new ReturnResult();
		
		result.setSuccess(true);
		result.setErrorMsg("");
		
		return	result;
	}
}
