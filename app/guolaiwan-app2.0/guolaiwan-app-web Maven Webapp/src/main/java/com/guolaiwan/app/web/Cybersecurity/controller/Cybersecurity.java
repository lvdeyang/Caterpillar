package com.guolaiwan.app.web.Cybersecurity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cybersecurity")

public class Cybersecurity {
	  //登录
			@RequestMapping(value = "/cyberLogin")
			public ModelAndView cybersecurityLogin(HttpServletRequest request,HttpSession session) throws Exception {
				ModelAndView mv = null;
				mv = new ModelAndView("mobile/cybersecurity/Login"); 
				return mv;
			}
	  //注册
	@RequestMapping(value = "/cyberregister")
	public ModelAndView cybersecurityregister(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/cybersecurity/register"); 
		return mv;
	}
	  //忘记密码
		@RequestMapping(value = "/cyberpassword")
		public ModelAndView cybersecuritypassword(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/password"); 
			return mv;
		}		
	  //我的标记
			@RequestMapping(value = "/cybermytag")
			public ModelAndView cybersecuritymytag(HttpServletRequest request,HttpSession session) throws Exception {
				ModelAndView mv = null;
				mv = new ModelAndView("mobile/cybersecurity/mytag"); 
				return mv;
			}
	  //设置
	@RequestMapping(value = "/cybersetup")
	public ModelAndView cybersecuritysetup(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/cybersecurity/setup"); 
		return mv;
	}
	  //账号与安全
		@RequestMapping(value = "/cybersecure")
		public ModelAndView cybersecuritysecure(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/secure"); 
			return mv;
		}
	  //修改手机号
		@RequestMapping(value = "/cyberrevise")
		public ModelAndView cybersecurityrevise(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/revise"); 
			return mv;
		}
		//修改密码
				@RequestMapping(value = "/cybercipher")
				public ModelAndView cybersecuritycipher(HttpServletRequest request,HttpSession session) throws Exception {
					ModelAndView mv = null;
					mv = new ModelAndView("mobile/cybersecurity/cipher"); 
					return mv;
				}
		//修改密码
		@RequestMapping(value = "/cyberfacilities")
		public ModelAndView cybersecurityfacilities(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/facilities"); 
			return mv;
		}		
	   //公安主页
		@RequestMapping(value = "/cyberhome")
		public ModelAndView cybersecurityhome(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/home"); 
			return mv;
		}
	   //我的
		@RequestMapping(value = "/cybermine")
		public ModelAndView cybersecuritymine(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/mine"); 
			return mv;
		} 
	   //预警信息
		@RequestMapping(value = "/cyberwarning")
		public ModelAndView cybersecuritywarning(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/warning"); 
			return mv;
		} 	
		//信息查询
		@RequestMapping(value = "/cyberInformation")
		public ModelAndView cybersecuritywarInformation(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/Information"); 
			return mv;
		} 
		//住宿管理
		@RequestMapping(value = "/cybermanagement")
		public ModelAndView cybersecuritywarmanagement(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/management"); 
			return mv;
		}
		//景区管理
				@RequestMapping(value = "/cyberScenic")
				public ModelAndView cybersecuritywarScenic(HttpServletRequest request,HttpSession session) throws Exception {
					ModelAndView mv = null;
					mv = new ModelAndView("mobile/cybersecurity/Scenic"); 
					return mv;
				} 	
		//客运管理
		@RequestMapping(value = "/cyberpassenger")
		public ModelAndView cybersecuritywarpassenger(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/passenger"); 
			return mv;
		}
		//物流管理
				@RequestMapping(value = "/cyberLogistics")
				public ModelAndView cybersecuritywarLogistics(HttpServletRequest request,HttpSession session) throws Exception {
					ModelAndView mv = null;
					mv = new ModelAndView("mobile/cybersecurity/Logistics"); 
					return mv;
				}
		//集会管理
		@RequestMapping(value = "/cyberrally")
		public ModelAndView cybersecuritywarrally(HttpServletRequest request,HttpSession session) throws Exception {
			ModelAndView mv = null;
			mv = new ModelAndView("mobile/cybersecurity/rally"); 
			return mv;
		}
}
