package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.result.MemPageResult;

/**
 * Created by czf on 2016/3/1.
 */
public interface UserPermissionService {
    /**
     * 根据用户ID获取菜单权限列表
     * @param id
     * @return
     * @throws Exception
     */
    MemPageResult<HaMenuDO> getMenuListByUserId(long id);

    /**
     * 根据用户ID获取权限url
     * @param id
     * @return
     * @throws Exception
     */
    MemPageResult<HaMenuDO> getUrlListByUserId(long id);
}
