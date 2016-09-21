package com.yimayhd.membercenter.client.service;

import com.yimay.membercenter.LocalBaseTest;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.enums.HaMenuProjectCode;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by xushubing on 2016/8/1.
 */
public class UserPermissionServiceTest extends LocalBaseTest {
    @Resource
    private UserPermissionService userPermissionService;

    @Test
    public void getMenuListByUserId() throws Exception {
        UserMenuQuery userMenuQuery = new UserMenuQuery();
        userMenuQuery.setUserId(17304);
        //userMenuQuery.setDomain(Constant.DOMAIN_JIUXIU);
        userMenuQuery.setProjectCode(HaMenuProjectCode.PALACE.getCode());
        UserMenuOptionDTO dto = new UserMenuOptionDTO();
        dto.setContainUrl(false);
        MemPageResult<HaMenuDO> queryResult = userPermissionService.getMenuListByUserId(userMenuQuery, dto);
        if (queryResult == null || !queryResult.isSuccess()) {

        }
    }

    @Test
    public void getMenuList() throws Exception {

    }

    @Test
    public void getMenuListByUserIdFromCatch() throws Exception {
        UserMenuQuery userMenuQuery = new UserMenuQuery();
        userMenuQuery.setUserId(17304);
        //userMenuQuery.setDomain(Constant.DOMAIN_JIUXIU);
        userMenuQuery.setProjectCode(HaMenuProjectCode.PALACE.getCode());
        UserMenuOptionDTO dto = new UserMenuOptionDTO();
        dto.setContainUrl(false);
        MemPageResult<com.yimayhd.membercenter.client.domain.HaMenuDO> queryResult = userPermissionService.getMenuListByUserIdFromCatch(userMenuQuery,dto);
        if (queryResult == null || !queryResult.isSuccess() ) {

        }
    }

    @Test
    public void catchUserMenu() throws Exception {
        UserMenuQuery userMenuQuery = new UserMenuQuery();
        userMenuQuery.setUserId(21206);
        //userMenuQuery.setDomain(Constant.DOMAIN_JIUXIU);
        userMenuQuery.setProjectCode(HaMenuProjectCode.PALACE.getCode());
        UserMenuOptionDTO dto = new UserMenuOptionDTO();
        dto.setContainUrl(false);
        boolean queryResult = userPermissionService.catchUserMenu(userMenuQuery, dto);

        logger.debug(queryResult+"");
    }

}