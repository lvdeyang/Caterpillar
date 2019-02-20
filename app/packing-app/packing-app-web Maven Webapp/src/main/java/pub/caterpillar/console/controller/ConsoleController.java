package pub.caterpillar.console.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.console.login.vo.AdminVO;

@Controller
@RequestMapping(value = "/console")
public class ConsoleController{

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		
		ModelAndView mv = null;
		
		HttpSession session = request.getSession();
		Object target = session.getAttribute(AdminVO.CATCHNAME);
		
		mv = new ModelAndView("web/console/index");
		
		return mv;
	}
	
}
