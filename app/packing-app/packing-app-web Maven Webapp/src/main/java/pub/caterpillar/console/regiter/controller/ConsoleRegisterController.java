package pub.caterpillar.console.regiter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/console/register")
public class ConsoleRegisterController{

	private static final Logger LOG = LoggerFactory.getLogger(ConsoleRegisterController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		
		ModelAndView mv = null;
		
		mv = new ModelAndView("web/register/register");
		
		return mv;
	}
	
}
