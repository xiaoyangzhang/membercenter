package com.yimay.membercenter.dubbo;

import com.yimay.membercenter.DubboBaseTest;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.service.UserPermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/3/3.
 */
public class UserPermissionServiceTest extends DubboBaseTest {
    @Autowired
    private UserPermissionService userPermissionService;

    @Test
    public void getMenuListByUserId(){
        long userId = 12800;
        MemPageResult<HaMenuDO> a = userPermissionService.getMenuListByUserId(userId);
        printResult(a,"");
    }
    @Test
    public void getUrlListByUserId(){
        long userId = 12800;
        MemPageResult<HaMenuDO> a = userPermissionService.getUrlListByUserId(userId);
        printResult(a,"");
    }
}
