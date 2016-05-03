package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.query.MenuQuery;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;

import java.util.List;

/**
 * Created by czf on 2016/3/1.
 */
public interface UserPermissionService {
    /**
     * 根据用户ID获取用户权限列表
     * @param userMenuQuery
     * @param userMenuOptionDTO
     * @return
     */
    MemPageResult<HaMenuDO> getMenuListByUserId(UserMenuQuery userMenuQuery,UserMenuOptionDTO userMenuOptionDTO);

    /**
     * 获取全部权限列表
     * @param menuQuery
     * @param userMenuOptionDTO
     * @return
     */
    MemResult<List<HaMenuDO>> getMenuList(MenuQuery menuQuery,UserMenuOptionDTO userMenuOptionDTO);

}
