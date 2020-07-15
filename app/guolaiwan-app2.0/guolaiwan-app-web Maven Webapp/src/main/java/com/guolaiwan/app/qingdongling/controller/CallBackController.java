package com.guolaiwan.app.qingdongling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class CallBackController {

	@ResponseBody
	@RequestMapping(value = "/callBackAction", method = RequestMethod.GET)//id为0时使用备源
    public String getCallBackAction(){
        return "success";
    }
	@ResponseBody
	@RequestMapping(value = "/callBackAction", method = RequestMethod.POST)//id为0时使用备源
    public String postCallBackAction(){
        return "200";
    }
}
