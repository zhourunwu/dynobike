package com.jeeplus.modules.sports.web;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.oa.service.OaNotifyService;
import com.jeeplus.modules.sports.entity.Device;
import com.jeeplus.modules.sports.entity.DeviceType;
import com.jeeplus.modules.sports.entity.Manufacturer;
import com.jeeplus.modules.sports.entity.Owner;
import com.jeeplus.modules.sports.service.DeviceService;
import com.jeeplus.modules.sports.service.ManufacturerService;
import com.jeeplus.modules.sports.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/sports/device")
public class DeviceController {

    /** AdminPath **/
    private String strAdminPath;

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private DeviceService deviceService;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private ManufacturerService manufacturerService;

	public	DeviceController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}


	@Autowired
	private OaNotifyService oaNotifyService;
	@RequestMapping(value = {"list", ""})
	public String list(Model model,Device device, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(device.getSearchName()!=null&&device.getSearchName().length()>0){
			String searchName = URLDecoder.decode(device.getSearchName(),"utf-8");
			device.setSearchName(searchName);
		}
		Page<Device> page = deviceService.getDeviceList(new Page<Device>(request, response),device);
		model.addAttribute("page", page);
		return "modules/sports/deviceList";
	}


	/**
	 * 新增、编辑页面
	 * @param device
	 * @param model
     * @return
     */
	@RequestMapping(value = "form")
	public String form(Device device, Model model) {
		if (device.getId() != null){
			device = deviceService.getDevice(device.getId());
		}
		model.addAttribute("device", device);
		return "modules/sports/deviceForm";
	}

	@ResponseBody
	@RequestMapping(value = "typeData")
	public List<DeviceType> typeData( HttpServletResponse response) {
		List<DeviceType> typelist = deviceService.getDeviceTypeList(new DeviceType());

		return typelist;
	}

	@ResponseBody
	@RequestMapping(value = "manufacturerData")
	public List<Manufacturer> manufacturerData(HttpServletResponse response) {
		List<Manufacturer> list = manufacturerService.getManufacturerList(new Manufacturer());

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "ownerData")
	public List<Owner> ownerData(HttpServletResponse response) {
		List<Owner> list = ownerService.getOwnerList(new Owner());

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "deviceData")
	public List<Device> deviceData(HttpServletResponse response) {
		List<Device> list = deviceService.getDeviceList(new Device());

		return list;
	}

	/**
	 * 保存|修改设备类型
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute Device device)
	{
		deviceService.saveOrUpdateDevice(device);

		return	"redirect:/a/sports/device";
	}

	/**
	 * 删除设备类型
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String	delete(@RequestParam(required = true) int id)
	{
		deviceService.deleteDevice(id);

		return	"redirect:/a/sports/device";
	}

	/**
	 * 删除设备类型
	 * @return
	 */
	@RequestMapping(value = "/deleteAll")
	public String	deleteAll(@RequestParam(required = true) int[] ids)
	{
		for (int i = 0,len = ids.length;i < len;i++){
			deviceService.deleteDevice(ids[i]);
		}
		return	"redirect:/a/sports/device";
	}

}
