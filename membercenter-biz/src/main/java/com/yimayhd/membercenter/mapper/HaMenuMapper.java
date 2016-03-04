package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.HaMenuDO;

import java.util.List;

/**
 * 菜单表
 * @author czf
 */
public interface HaMenuMapper{

    List<HaMenuDO> getMenuListByUserId(long id);
    List<HaMenuDO> getUrlListByUserId(long id);
    List<HaMenuDO> getMenuList();
}
