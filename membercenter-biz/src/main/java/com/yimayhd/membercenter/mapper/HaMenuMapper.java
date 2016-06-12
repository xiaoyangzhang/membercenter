package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.query.MenuListQuery;
import com.yimayhd.membercenter.query.HaMenuQuery;

import java.util.List;

/**
 * 菜单表
 *
 * @author czf
 */
public interface HaMenuMapper {

    List<HaMenuDO> getMenuList(HaMenuQuery haMenuQuery);

    List<HaMenuDO> getAllMenuList(HaMenuQuery haMenuQuery);

    int add(HaMenuDO haMenuDO);

    HaMenuDO getById(long id);

    int modify(HaMenuDO haMenuDO);

    void delete(long id);

    List<HaMenuDO> pageQuery(MenuListQuery menuListQuery);

    Integer queryCount(MenuListQuery menuListQuery);
}
