package com.jeeplus.modules.sports.web;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Message;
import com.jeeplus.modules.sports.service.ManufacturerService;
import com.jeeplus.modules.sports.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value = "${adminPath}/sports/message")
public class MessageController {

    /** AdminPath **/
    private String strAdminPath;

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private ManufacturerService manufacturerService;

    @Autowired
    private MessageService messageService;

	public MessageController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}


	@RequestMapping(value = {"list", ""})
	public String list(Model model, Message message, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Page<Message> page = messageService.getMessageList(new Page<Message>(request, response),message);
		model.addAttribute("page", page);
		return "modules/sports/messageList";
	}

	@RequestMapping(value = "form")
	public String form(Message message, Model model) {
		model.addAttribute("page", message);
		return "modules/sports/messageForm";
	}

	/**
	 * 保存|修改 消息指令
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public String VideoAddSave(@ModelAttribute Message message)
	{
		messageService.saveOrUpdateMessage(message);

		return	"redirect:/a/sports/message";
	}

}
