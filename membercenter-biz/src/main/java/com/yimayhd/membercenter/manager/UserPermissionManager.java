package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.enums.HaMenuType;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.mapper.HaMenuMapper;
import com.yimayhd.membercenter.mapper.HaRoleMenuMapper;
import com.yimayhd.membercenter.mapper.HaUserRoleMapper;
import com.yimayhd.membercenter.query.HaMenuQuery;
import com.yimayhd.membercenter.query.HaRoleMenuQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czf on 2016/3/1.
 */
public class UserPermissionManager {

    @Autowired
    private HaUserRoleMapper haUserRoleMapper;
    @Autowired
    private HaRoleMenuMapper haRoleMenuMapper;
    @Autowired
    private HaMenuMapper haMenuMapper;

    public List<HaMenuDO> getMenuList(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        //查role
        List<Long> haRoleIdList = haUserRoleMapper.getHaRoleList(userMenuQuery);

        //查menu
        HaRoleMenuQuery haRoleMenuQuery = fillHaRoleMenuQuery(haRoleIdList);
        List<Long> haMenuIdList = haRoleMenuMapper.getHaRoleIdList(haRoleMenuQuery);

        //查menu
        HaMenuQuery haMenuQuery = fillHaMenuQuery(haMenuIdList,userMenuQuery,userMenuOptionDTO);
        return haMenuMapper.getMenuList(haMenuQuery);
    }
    private HaRoleMenuQuery fillHaRoleMenuQuery(List<Long> haRoleIdList){
        HaRoleMenuQuery haRoleMenuQuery = new HaRoleMenuQuery();
        haRoleMenuQuery.setRoleIdList(haRoleIdList);
        return haRoleMenuQuery;
    }
    private HaMenuQuery fillHaMenuQuery(List<Long> haMenuIdList,UserMenuQuery userMenuQuery,UserMenuOptionDTO userMenuOptionDTO){
        HaMenuQuery haMenuQuery = new HaMenuQuery();
        haMenuQuery.setMenuIdList(haMenuIdList);
        haMenuQuery.setDomain(userMenuQuery.getDomain());
        List<Integer> typeList = new ArrayList<Integer>();
        //不包含url时，只查菜单
        if(!userMenuOptionDTO.isContainUrl()){
            typeList.add(HaMenuType.MENU.getType());
        }

        haMenuQuery.setTypeList(typeList);
        return haMenuQuery;
    }
}
