package com.jeeplus.modules.sports.uploadcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jeeplus.modules.sports.util.UploaderUtil;
import com.jeeplus.modules.sports.util.UploaderUtil.UploaderResult;

@Controller
@RequestMapping(value = "${adminPath}/sports/uploader")
public class UploaderController {
    
    @RequestMapping(value = {"/upload", "/upload.json"}, method = RequestMethod.POST)
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
    	
        ModelAndView m = new ModelAndView();
        UploaderResult result = UploaderUtil.uploader(request, "/data", "filenameUploader", "/uploader", null, 1024 * 1024);
        
        m.addObject("result", result);
        
        return m;
    }    
}
