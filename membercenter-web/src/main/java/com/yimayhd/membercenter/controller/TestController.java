package com.yimayhd.membercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping(value="/testView")
	public ModelAndView forward(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test/scrollrefresh");
		
		return mv;
	}
}
