
package com.yimayhd.membercenter.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.mq.MsgSenderService;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by root on 15-11-30.
 */
@RestController
public class TestController {

    @Resource
    private UserService userService;
    @Resource
    private MsgSenderService msgSender;

    @RequestMapping("/test/send")
    public Map<String, Object> test(@RequestParam String mobile) {
        Map<String, Object> map = new HashMap<>();
        BaseResult<Boolean> baseResult = userService.sendPhoneVerifyCode("18601907819");

        map.put("result", baseResult.getValue());
        return map;
    }

    @RequestMapping("/test/validate")
    public Map<String, Object> validate(@RequestParam String mobile, @RequestParam String code) {
        Map<String, Object> map = new HashMap<>();
        BaseResult<Boolean> baseResult = userService.validatePhoneVerifyCode(mobile, code);
        
        map.put("result", baseResult.getValue());
        return map;
    }



    @RequestMapping("/test/dimen")
    public Map<String, Object> dimen() {
        Map<String, Object> map = new HashMap<>();
        BaseResult<String> dimenResult = userService.getTwoDimensionCode(3500L, 1L);
        System.out.println(dimenResult.getValue());

        BaseResult<UserDO> baseResult = userService.getUserDOByTwoDimensionCode(dimenResult.getValue());

        map.put("result", baseResult.getValue());
        return map;
    }

    @RequestMapping("/test/mobile")
    public Map<String, Object> findMobile() {
        Map<String, Object> map = new HashMap<>();
        BaseResult<String> mobileResult = userService.findMobileByUserId(3500L);

        BaseResult<UserDO> userDOBaseResult = userService.getUserDOByMobile("18601907819");
        System.out.println("18601907819=" + userDOBaseResult.getValue().getId());
        mobileResult = userService.findMobileByUserId(userDOBaseResult.getValue().getId());


        map.put("result", mobileResult.getValue());
        return map;
    }
    
    @RequestMapping("/test/exception")
    public Response testException(){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("message","error happen");
    	
    	throw new RuntimeException("测试异常");
    	//return map;

    }
    
    @RequestMapping("/test/testMQSend")
    public SendResult testMQSend(){
    	UserVO vo = new UserVO();
    	vo.setUserId(123456L);
    	vo.setName("sunshine");
    	return msgSender.sendMessage(vo,"topic1","tag1");
    }
    
    @RequestMapping("/test/testMQRecieve")
    public SendResult testMQRecieve(){
    	UserVO vo = new UserVO();
    	vo.setUserId(123456L);
    	vo.setName("sunshine");
    	return msgSender.sendMessage(vo,"topic1","tag1");
    }
    
    @RequestMapping("/test/view")
    public ModelAndView testView(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/test/index");
    	
    	return mv;
    }
    
}

