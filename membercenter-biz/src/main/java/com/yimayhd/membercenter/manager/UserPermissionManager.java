package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.client.domain.HaUserRoleDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.enums.HaMenuType;
import com.yimayhd.membercenter.client.enums.UserRoleStatus;
import com.yimayhd.membercenter.client.query.MenuQuery;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.dao.UserRoleDao;
import com.yimayhd.membercenter.mapper.HaMenuMapper;
import com.yimayhd.membercenter.mapper.HaRoleMenuMapper;
import com.yimayhd.membercenter.mapper.HaUserRoleMapper;
import com.yimayhd.membercenter.query.HaMenuQuery;
import com.yimayhd.membercenter.query.HaRoleMenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czf on 2016/3/1.
 */
public class UserPermissionManager {

	@Autowired
	private UserRoleDao userRoleDao ;
    @Autowired
    private HaUserRoleMapper haUserRoleMapper;
    @Autowired
    private HaRoleMenuMapper haRoleMenuMapper;
    @Autowired
    private HaMenuMapper haMenuMapper;

    public MemPageResult<HaMenuDO> getMenuList(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        MemPageResult<HaMenuDO> menuDOMemPageResult = new MemPageResult<HaMenuDO>();
        //查role
        List<Long> haRoleIdList = haUserRoleMapper.getHaRoleList(userMenuQuery);
        if(CollectionUtils.isEmpty(haRoleIdList)){
            menuDOMemPageResult.setReturnCode(MemberReturnCode.USER_NON_ROLE_ERROR);
            return menuDOMemPageResult;
        }
        //查menu
        HaRoleMenuQuery haRoleMenuQuery = fillHaRoleMenuQuery(haRoleIdList);
        List<Long> haMenuIdList = haRoleMenuMapper.getHaRoleIdList(haRoleMenuQuery);
        if(CollectionUtils.isEmpty(haMenuIdList)){
            menuDOMemPageResult.setReturnCode(MemberReturnCode.USER_ROLE_NON_URL_ERROR);
            return menuDOMemPageResult;
        }

        //查menu
        HaMenuQuery haMenuQuery = fillHaMenuQuery(haMenuIdList,userMenuQuery,userMenuOptionDTO);
        menuDOMemPageResult.setList(haMenuMapper.getMenuList(haMenuQuery));
        return menuDOMemPageResult;
    }

    public List<HaMenuDO> getAllMenuList(MenuQuery menuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        HaMenuQuery haMenuQuery = new HaMenuQuery();
        haMenuQuery.setDomain(menuQuery.getDomain());
        if(!userMenuOptionDTO.isContainUrl()) {
            haMenuQuery.setType(1);
        }

        return haMenuMapper.getAllMenuList(haMenuQuery);
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
    
    public boolean addRole4User(long userId, long roleId){
    	HaUserRoleDO userRoleDO = userRoleDao.getUserRole(userId, roleId);
    	if( userRoleDO != null  ){
    		//已存在，更新
    		if( UserRoleStatus.ACTIVE.getStatus() == userRoleDO.getStatus() ){
    			return true;
    		}
			userRoleDO.setStatus(UserRoleStatus.ACTIVE.getStatus());
			HaUserRoleDO updateResult = userRoleDao.updateStatus(userRoleDO);
			if( updateResult == null ){
				return false;
			}
			return true;
    	}
    	//不存在，新建
    	userRoleDO = new HaUserRoleDO() ;
    	userRoleDO.setHaRoleId(roleId);
    	userRoleDO.setHaUserId(userId);
    	userRoleDO.setStatus(UserRoleStatus.ACTIVE.getStatus());
    	HaUserRoleDO insertResult =userRoleDao.insert(userRoleDO);
    	if( insertResult == null ){
			return false;
		}
		return true;
    }
    
    public boolean disactiveUserRole(long userId, long roleId){
    	HaUserRoleDO userRoleDO = userRoleDao.getUserRole(userId, roleId);
    	if( userRoleDO != null  ){
    		//已存在，更新
    		if( UserRoleStatus.DISACTIVE.getStatus() == userRoleDO.getStatus() ){
    			return true;
    		}
    		userRoleDO.setStatus(UserRoleStatus.DISACTIVE.getStatus());
    		HaUserRoleDO updateResult = userRoleDao.updateStatus(userRoleDO);
    		if( updateResult == null ){
    			return false;
    		}
    		return true;
    	}
    	return true;
    }
    
    
}
