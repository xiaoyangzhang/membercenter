package com.yimayhd.membercenter.service.client;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.query.MenuQuery;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.UserPermissionService;
import com.yimayhd.membercenter.manager.UserPermissionManager;
import com.yimayhd.membercenter.mapper.HaMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czf on 2016/3/1.
 */
public class UserPermissionServiceImpl implements UserPermissionService {
    private static final Logger log = LoggerFactory.getLogger(UserPermissionServiceImpl.class);

    @Autowired
    private UserPermissionManager userPermissionManager;

    @Override
    public MemPageResult<HaMenuDO> getMenuListByUserId(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        MemPageResult<HaMenuDO> menuDOMemPageResult = new MemPageResult<HaMenuDO>();
        try {
            MemPageResult<HaMenuDO> haMenuDOResult = userPermissionManager.getMenuList(userMenuQuery, userMenuOptionDTO);
            if(!haMenuDOResult.isSuccess()){
                menuDOMemPageResult.setReturnCode(haMenuDOResult.getReturnCode());
                return menuDOMemPageResult;
            }
            if(!userMenuOptionDTO.isContainUrl()){
                //查询菜单时，组合层级结构
                menuDOMemPageResult.setList(combineMenu(haMenuDOResult.getList()));
            }else{
                menuDOMemPageResult.setList(haMenuDOResult.getList());
            }

        }catch (Exception e){
            log.error("getMenuListByUserId current exception : {}",e);
            menuDOMemPageResult.setReturnCode(MemberReturnCode.PAGE_QUERY_USER_MENU_FAILED);
        }
        return menuDOMemPageResult;
    }

    @Override
    public MemResult<List<HaMenuDO>> getMenuList(MenuQuery menuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        MemResult<List<HaMenuDO>> memResult = new MemResult<List<HaMenuDO>>();
        try{
            List<HaMenuDO> haMenuDOList = userPermissionManager.getAllMenuList(menuQuery, userMenuOptionDTO);
            memResult.setValue(haMenuDOList);
        }catch (Exception e){
            log.error("getMenuList current exception : {}",e);
            memResult.setReturnCode(MemberReturnCode.PAGE_QUERY_ALL_MENU_FAILED);
        }
        return memResult;
    }

    /**
     * 组合菜单层级结构
     * @param haMenuDOList 菜单列表
     */
    private List<HaMenuDO> combineMenu(List<HaMenuDO> haMenuDOList){
        List<HaMenuDO> haMenuDOListNew = new ArrayList<HaMenuDO>();
        if(haMenuDOList == null || haMenuDOList.size() == 0){
            return haMenuDOList;
        }
        Map<Long,HaMenuDO> haMenuDOMap = new HashMap<Long,HaMenuDO>();
        for (HaMenuDO haMenuDO : haMenuDOList){
            haMenuDOMap.put(haMenuDO.getId(), haMenuDO);
        }
        //层级关系(默认只有两级)
        for (HaMenuDO haMenuDO : haMenuDOMap.values()){
            System.out.println(JSON.toJSONString(haMenuDO));
        }
        for (HaMenuDO haMenuDO : haMenuDOMap.values()){
            if(haMenuDOMap.containsKey(haMenuDO.getParentId())){
                HaMenuDO haMenuDOParent = haMenuDOMap.get(haMenuDO.getParentId());
                if(CollectionUtils.isEmpty(haMenuDOParent.getHaMenuDOList())){
                    haMenuDOParent.setHaMenuDOList(new ArrayList<HaMenuDO>());
                }
                haMenuDOParent.getHaMenuDOList().add(haMenuDO);
            }
        }

        for (HaMenuDO haMenuDO : haMenuDOMap.values()){
            if(1 == haMenuDO.getLevel()){
                haMenuDOListNew.add(haMenuDO);
            }
        }

        return haMenuDOListNew;

    }
}