package com.yimayhd.membercenter.service.client;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.service.UserPermissionService;
import com.yimayhd.membercenter.manager.UserPermissionManager;
import com.yimayhd.membercenter.mapper.HaMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by czf on 2016/3/1.
 */
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionManager userPermissionManager;


    @Override
    public MemPageResult<HaMenuDO> getMenuListByUserId(long id) {
        MemPageResult<HaMenuDO> menuDOMemPageResult = new MemPageResult<HaMenuDO>();
        try {
            List<HaMenuDO> haMenuDOList = userPermissionManager.getMenuListByUserId(id);
            menuDOMemPageResult.setList(haMenuDOList);
        }catch (Exception e){
            menuDOMemPageResult.setReturnCode(MemberReturnCode.PAGE_QUERY_USER_MENU_FAILED);
        }
        return menuDOMemPageResult;
    }

    @Override
    public MemPageResult<HaMenuDO> getUrlListByUserId(long id) {
        MemPageResult<HaMenuDO> menuDOMemPageResult = new MemPageResult<HaMenuDO>();
        try {
            List<HaMenuDO> haMenuDOList = userPermissionManager.getUrlListByUserId(id);
            menuDOMemPageResult.setList(haMenuDOList);
        }catch (Exception e){
            menuDOMemPageResult.setReturnCode(MemberReturnCode.PAGE_QUERY_USER_URL_FAILED);
        }
        return menuDOMemPageResult;
    }
}
