package com.yimay.membercenter.dubbo;

import com.alibaba.fastjson.JSON;
import com.taobao.tair.json.Json;
import com.yimay.membercenter.DubboBaseTest;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.query.MenuQuery;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.UserPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-consumer-test.xml" })
public class UserPermissionServiceTest{
    @Autowired
    private UserPermissionService userPermissionService;

    @Test 
    public void getMenuListByUserId(){
        long userId = 17508;
        UserMenuQuery userMenuQuery = new UserMenuQuery();
        userMenuQuery.setUserId(userId);
        userMenuQuery.setDomain(1000);
        UserMenuOptionDTO userMenuOptionDTO = new UserMenuOptionDTO();
        userMenuOptionDTO.setContainUrl(true);
        MemPageResult<HaMenuDO> a = userPermissionService.getMenuListByUserId(userMenuQuery,userMenuOptionDTO);
        System.out.println(JSON.toJSONString(a));
    }

    @Test
    public void getAllMenuListTest(){
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setDomain(1000);
        UserMenuOptionDTO userMenuOptionDTO = new UserMenuOptionDTO();
        userMenuOptionDTO.setContainUrl(true);
        MemResult<List<HaMenuDO>> a = userPermissionService.getMenuList(menuQuery, userMenuOptionDTO);
        System.out.println(JSON.toJSONString(a));
    }

}
