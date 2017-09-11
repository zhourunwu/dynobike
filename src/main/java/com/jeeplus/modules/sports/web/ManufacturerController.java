package com.jeeplus.modules.sports.web;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Manufacturer;
import com.jeeplus.modules.sports.service.ManufacturerService;
import com.jeeplus.modules.sports.service.SportSiteImageService;
import com.jeeplus.modules.sports.service.SportSiteVideoService;
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
@RequestMapping(value = "${adminPath}/sports/manufacturer")
public class ManufacturerController {

    /** AdminPath **/
    private String strAdminPath;

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private	SportSiteVideoService	videoService;

	@Autowired
	private SportSiteImageService	imageService;

	@Autowired
	private ManufacturerService manufacturerService;

	public ManufacturerController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}


	@RequestMapping(value = {"list", ""})
	public String list(Model model, Manufacturer manufacturer, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(manufacturer.getSearchName()!=null&&manufacturer.getSearchName().length()>0){
			String searchName = URLDecoder.decode(manufacturer.getSearchName(),"utf-8");
			manufacturer.setSearchName(searchName);
		}
		Page<Manufacturer> page = manufacturerService.getManufacturerList(new Page<Manufacturer>(request, response),manufacturer);
		model.addAttribute("page", page);
		return "modules/sports/manufacturerList";
	}

	@RequestMapping(value = "form")
	public String form(Manufacturer manufacturer, Model model) {
		if (manufacturer.getId() != null){
			manufacturer = manufacturerService.getManufacture(manufacturer.getId());
		}
		model.addAttribute("manufacturer", manufacturer);
		return "modules/sports/manufacturerForm";
	}

	/**
	 * 保存|修改 厂商
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute Manufacturer manufacturer)
	{
		manufacturerService.saveOrUpdateManufacturer(manufacturer);

		return	"redirect:/a/sports/manufacturer";
	}

	/**
	 * 删除厂商
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String	delete(@RequestParam(required = true) int id)
	{
		manufacturerService.deleteManufacturer(id);

		return	"redirect:/a/sports/manufacturer";
	}

	/**
	 * 删除厂商
	 * @return
	 */
	@RequestMapping(value = "/deleteAll")
	public String	deleteAll(@RequestParam(required = true) int[] ids)
	{
		for (int i = 0,len = ids.length;i < len;i++){
			manufacturerService.deleteManufacturer(ids[i]);
		}
		return	"redirect:/a/sports/manufacturer";
	}

}
