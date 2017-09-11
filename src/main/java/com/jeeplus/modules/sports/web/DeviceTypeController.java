package com.jeeplus.modules.sports.web;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.DeviceType;
import com.jeeplus.modules.sports.service.DeviceService;
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
@RequestMapping(value = "${adminPath}/sports/deviceType")
public class DeviceTypeController {

    /** AdminPath **/
    private String strAdminPath;

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private	SportSiteVideoService	videoService;

	@Autowired
	private DeviceService deviceService;

	public DeviceTypeController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}


	@RequestMapping(value = {"list", ""})
	public String list(Model model, DeviceType deviceType, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(deviceType.getSearchName()!=null&&deviceType.getSearchName().length()>0){
			String searchName = URLDecoder.decode(deviceType.getSearchName(),"utf-8");
			deviceType.setSearchName(searchName);
		}
		Page<DeviceType> page = deviceService.getDeviceTypeList(new Page<DeviceType>(request, response),deviceType);
		model.addAttribute("page", page);
		return "modules/sports/deviceTypeList";
	}

	@RequestMapping(value = "form")
	public String form(DeviceType deviceType, Model model) {
		if (deviceType.getId() != null){
			deviceType = deviceService.getDeviceType(deviceType.getId());
		}
		model.addAttribute("deviceType", deviceType);
		return "modules/sports/deviceTypeForm";
	}

	/**
	 * 保存|修改设备类型
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute 	DeviceType deviceType)
	{
		deviceService.saveOrUpdateDeviceType(deviceType);

		return	"redirect:/a/sports/deviceType";
	}

	/**
	 * 删除设备类型
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String	delete(@RequestParam(required = true) int id)
	{
		deviceService.deleteDeviceType(id);

		return	"redirect:/a/sports/deviceType";
	}

	/**
	 * 删除设备类型
	 * @return
	 */
	@RequestMapping(value = "/deleteAll")
	public String	deleteAll(@RequestParam(required = true) int[] ids)
	{

		for (int i = 0,len = ids.length;i < len;i++){
			deviceService.deleteDeviceType(ids[i]);
		}
		return	"redirect:/a/sports/deviceType";
	}

}
