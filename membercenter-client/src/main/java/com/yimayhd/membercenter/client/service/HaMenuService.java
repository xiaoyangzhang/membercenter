package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.query.MenuListQuery;
import com.yimayhd.membercenter.client.result.BasePageResult;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * Created by xushubing on 2016/8/8.
 */
public interface HaMenuService {
    /**
     * 根据id查询菜单
     *
     * @param id 菜单id
     * @return
     */
    public MemResult<HaMenuDO> getMenuById(long id);

    /**
     * 添加菜单
     *
     * @param haMenuDO
     * @return
     */
    public MemResult<HaMenuDO> addMenu(HaMenuDO haMenuDO);

    /**
     * 更新菜单状态
     *
     * @param id
     * @return
     */
    public MemResult<Boolean> updateMenuStatus(long id);

    /**
     * 分页查询菜单
     *
     * @param menuListQuery
     * @return
     */
    public BasePageResult<HaMenuDO> selectList(MenuListQuery menuListQuery);

    /**
     * @param menuListQuery
     * @return
     */
    public BasePageResult<HaMenuDO> selectChildList(MenuListQuery menuListQuery);
}
