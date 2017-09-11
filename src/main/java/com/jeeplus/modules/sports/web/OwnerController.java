package com.jeeplus.modules.sports.web;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Owner;
import com.jeeplus.modules.sports.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping(value = "${adminPath}/sports/owner")
public class OwnerController {

    /** AdminPath **/
    private String strAdminPath;

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OwnerService ownerService;

	public OwnerController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}


	@RequestMapping(value = {"list", ""})
	public String list(Model model, Owner owner, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(owner.getSearchName()!=null&&owner.getSearchName().length()>0){
			String searchName = URLDecoder.decode(owner.getSearchName(),"utf-8");
			owner.setSearchName(searchName);
		}
		Page<Owner> page = ownerService.getOwnerList(new Page<Owner>(request, response),owner);
		model.addAttribute("page", page);
		return "modules/sports/ownerList";
	}

	@RequestMapping(value = "form")
	public String form(Owner owner, Model model) {
		if (owner.getId() != null){
			owner = ownerService.getOwner(owner.getId());
		}
		model.addAttribute("owner", owner);
		return "modules/sports/ownerForm";
	}

	/**
	 * 保存|修改 所有者
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public String VideoAddSave(@ModelAttribute Owner owner)
	{
		ownerService.saveOrUpdateOwner(owner);

		return	"redirect:/a/sports/owner";
	}

	/**
	 * 删除所有者
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String	delete(@RequestParam(required = true) int id)
	{
		ownerService.deleteOwner(id);

		return	"redirect:/a/sports/owner";
	}

	/**
	 * 删除所有者
	 * @return
	 */
	@RequestMapping(value = "/deleteAll")
	public String	deleteAll(@RequestParam(required = true) int[] ids)
	{
		for (int i = 0,len = ids.length;i < len;i++){
			ownerService.deleteOwner(ids[i]);
		}
		return	"redirect:/a/sports/owner";
	}
}
