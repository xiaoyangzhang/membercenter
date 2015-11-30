
package com.yimayhd.membercenter.controller;

import java.util.HashMap;
import java.util.Map;

import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        BaseResult<Boolean> baseResult = userService.sendPhoneVerifyCode("18601907819");

        map.put("result", baseResult.getValue());
        return map;
    }
}

