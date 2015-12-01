
package com.yimayhd.membercenter.controller;

import java.util.HashMap;
import java.util.Map;

import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by root on 15-11-30.
 */
@RestController
public class TestController {

    @Resource
    private UserService userService;

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
    
    @RequestMapping("/test/exception")
    public Map<String,String> testException(){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("message","error happen");
    	
    	throw new RuntimeException("测试异常");
    	
    	//return map;

    }
}

