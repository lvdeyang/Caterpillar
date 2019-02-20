package pub.caterpillar.console.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/console/home")
public class ConsoleHomeController{

	private static final Logger LOG = LoggerFactory.getLogger(ConsoleHomeController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		
		ModelAndView mv = null;
		
		return mv;
	}
	
}
