package com.guolaiwan.app.web.publicnum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.javacv.FishNewLiveService;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/fishtest")
public class TestController {

	@RequestMapping(value = "/index")
	public ModelAndView merchantIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/testlive");
		return mv;
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/switchm", method = RequestMethod.GET)
	public Object switchm(HttpServletRequest request,int proNo) throws Exception {
		FishNewLiveService service=
				FishNewLiveService.getinstance("rtmp://192.165.56.70/live/test", "rtmp://192.165.56.70/live/test1", "rtmp://192.165.56.70/live/test2");
	    if(!service.isStart()){
	    	service.startPush();
	    }
		service.switchPro(proNo);
	    return "success";
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public Object stop(HttpServletRequest request) throws Exception {
		FishNewLiveService service=
				FishNewLiveService.getinstance("rtmp://192.165.56.70/live/test", "rtmp://192.165.56.70/live/test1", "rtmp://192.165.56.70/live/test2");
	    service.stopPush();
	    return "success";
	}
	
}
