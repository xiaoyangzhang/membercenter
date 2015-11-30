
package com.yimayhd.membercenter.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 15-11-30.
 */
@RestController
public class TestController {

    @RequestMapping("/test/send")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }
}

