/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sports.web;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.sports.entity.Users;
import com.jeeplus.modules.sports.service.UsersService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通知通告Controller
 * @author jeeplus
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sports/users")
public class UsersController extends BaseController {

	@Autowired
	private UsersService usersService;

	/**
	 * 我的通知列表
	 */
	@RequestMapping(value = "list")
	public String usersList(Users users, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Users> page = usersService.find(new Page<Users>(request, response), users);
		model.addAttribute("page", page);
		return "modules/sports/usersList";
	}



	
	@ModelAttribute
	public Users get(@RequestParam(required=false) String id) {

		Users entity = null;
		if (StringUtils.isNotBlank(id)){
			/*entity = UsersService.get(id);*/
		}
		
		if (entity == null){
			entity = new Users();
		}
		
		return entity;
	}
	
/*	@RequiresPermissions("stu:student:list")
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<Student> page = studentService.find(new Page<Student>(request, response), student);
		model.addAttribute("page", page);
		return "modules/student/studentList";
	}*/

	/*
	 * 查看，增加，编辑报告表单页面
	 */
	@RequiresPermissions(value={"stu:student:view","stu:student:add","stu:student:edit"},logical= Logical.OR)
	@RequestMapping(value = "form")
	public String form(Users users, Model model) {
		
		if (StringUtils.isNotBlank(users.getId())){
			users = usersService.getUserInfo(users.getId());
		}
		model.addAttribute("users", users);
		return "modules/sports/usersForm";
	}

	@RequiresPermissions(value={"sports:users:add","stu:student:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Users users, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, users)){
			return form(users, model);
		}
		usersService.save(users);
		addMessage(redirectAttributes, "保存会员'" + users.getWxId() + "'成功");
		return "redirect:" + adminPath + "/sports/users/list?repage";
	}

	/*@RequiresPermissions("stu:student:del")
	@RequestMapping(value = "delete")
	public String delete(Student student, RedirectAttributes redirectAttributes) {
		studentService.delete(student);
		addMessage(redirectAttributes, "删除学生成功");
		return "redirect:" + adminPath + "/stu/student/studentList?repage";
	}*/
	
	/*@RequiresPermissions("stu:student:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			studentService.delete(studentService.get(id));
		}
		addMessage(redirectAttributes, "删除学生成功");
		return "redirect:" + adminPath + "/stu/student/studentList?repage";
	}*/
	



	/**
	 * 我的通知列表-数据
	 */
	/*@RequiresPermissions("stu:student:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<Student> listData(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		//student.setSelf(true);
		Page<Student> page = studentService.find(new Page<Student>(request, response), student);
		return page;
	}
	
	/**
	 * 查看我的通知,重定向在当前页面打开
	 */
	@RequestMapping(value = "view")
	public String view(Users users, Model model) {
		if (StringUtils.isNotBlank(users.getId())){
			users = usersService.getUserInfo(users.getId());
			model.addAttribute("users", users);
			return "modules/sports/usersForm";
		}
		return "redirect:" + adminPath + "/sports/users/form?repage";
	}

	/**
	 * 查看我的通知-数据
	 */
/*	@RequestMapping(value = "viewData")
	@ResponseBody
	public OaNotify viewData(OaNotify oaNotify, Model model) {
		*//*if (StringUtils.isNotBlank(oaNotify.getId())){
			studentService.updateReadFlag(oaNotify);
			return oaNotify;
		}*//*
		return null;
	}*/
	
	/**
	 * 查看我的通知-发送记录
	 */
/*	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public OaNotify viewRecordData(OaNotify oaNotify, Model model) {
		if (StringUtils.isNotBlank(oaNotify.getId())){
			oaNotify = studentService.getRecordList(oaNotify);
			return oaNotify;
		}
		return null;
	}*/
	
/*	*//**
	 * 获取我的通知数目
	 *//*
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(Student student, Model model) {
		student.setSelf(true);
		student.setReadFlag("0");
		return String.valueOf(studentService.findCount(student));
	}*/
}