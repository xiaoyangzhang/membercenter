
package com.yimayhd.membercenter.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CommonController {

    @RequestMapping("/notauth")
    public ModelAndView toNotAuthView(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/notauth");
    	
    	return mv;
    }
    
}

