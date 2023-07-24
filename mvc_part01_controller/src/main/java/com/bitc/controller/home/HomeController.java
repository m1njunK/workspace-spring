package com.bitc.controller.home;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 	component-scan으로 Bean으로 등록되는 class annotation
 * 
 *	@Controller : Presentation Layer에서 Controller를 명시하기 위해서 사용 
 *	@Service	: Business Layer 에서 Service를 명시하기 위해서 사용
 *	@Repository : Persistence Layer 에서 Data Access Object를 명시하기 위해 사용
 *	@Component	: 그 외의 의미를 두지 않는 class Bean으로 등록하고 관리하기 위해 사용
 *	@Component의 하위 형태로 @Controller, @Service, @Repository가 존재
 */

@Controller
public class HomeController {
	// /WEB-INF/views/ + "home" + ".jsp";
	@RequestMapping(value = "main.home", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		session.setAttribute("test","session test");
		model.addAttribute("modelTest","test Model");
		return "home";
	}
	
}
