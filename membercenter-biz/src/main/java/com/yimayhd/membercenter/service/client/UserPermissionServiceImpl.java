package com.yimayhd.membercenter.service.client;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.cache.CacheManager;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.dto.UserMenuOptionDTO;
import com.yimayhd.membercenter.client.query.MenuQuery;
import com.yimayhd.membercenter.client.query.UserMenuQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.UserPermissionService;
import com.yimayhd.membercenter.manager.UserPermissionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czf on 2016/3/1.
 */
public class UserPermissionServiceImpl implements UserPermissionService {
    private static final Logger log = LoggerFactory.getLogger(UserPermissionServiceImpl.class);
    @Resource
    private CacheManager cacheManager;
    @Autowired
    private UserPermissionManager userPermissionManager;
    private final String USER_MENU_INFO_CACHE_HEAD = "membercenter_user_menu_info_";
    private final int DEFAULT_CACHE_VALID_TIME = 60 * 120;

    @Override
    public MemPageResult<HaMenuDO> getMenuListByUserId(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        MemPageResult<HaMenuDO> menuDOMemPageResult = new MemPageResult<HaMenuDO>();
        try {
            MemPageResult<HaMenuDO> haMenuDOResult = userPermissionManager.getMenuList(userMenuQuery, userMenuOptionDTO);
            if (!haMenuDOResult.isSuccess()) {
                menuDOMemPageResult.setReturnCode(haMenuDOResult.getReturnCode());
                return menuDOMemPageResult;
            }
            if (!userMenuOptionDTO.isContainUrl()) {
                //查询菜单时，组合层级结构
                menuDOMemPageResult.setList(combineMenu(haMenuDOResult.getList()));
            } else {
                menuDOMemPageResult.setList(haMenuDOResult.getList());
            }

        } catch (Exception e) {
            log.error("getMenuListByUserId current exception : {}", e);
            menuDOMemPageResult.setReturnCode(MemberReturnCode.PAGE_QUERY_USER_MENU_FAILED);
        }
        return menuDOMemPageResult;
    }

    @Override
    public MemResult<List<HaMenuDO>> getMenuList(MenuQuery menuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        MemResult<List<HaMenuDO>> memResult = new MemResult<List<HaMenuDO>>();
        try {
            List<HaMenuDO> haMenuDOList = userPermissionManager.getAllMenuList(menuQuery, userMenuOptionDTO);
            memResult.setValue(haMenuDOList);
        } catch (Exception e) {
            log.error("getMenuList current exception : {}", e);
            memResult.setReturnCode(MemberReturnCode.PAGE_QUERY_ALL_MENU_FAILED);
        }
        return memResult;
    }

    /**
     * 组合菜单层级结构
     *
     * @param haMenuDOList 菜单列表
     */
    private List<HaMenuDO> combineMenu(List<HaMenuDO> haMenuDOList) {
        List<HaMenuDO> haMenuDOListNew = new ArrayList<HaMenuDO>();
        if (haMenuDOList == null || haMenuDOList.size() == 0) {
            return haMenuDOList;
        }
        Map<Long, HaMenuDO> haMenuDOMap = new HashMap<Long, HaMenuDO>();
        for (HaMenuDO haMenuDO : haMenuDOList) {
            haMenuDOMap.put(haMenuDO.getId(), haMenuDO);
        }
        //层级关系(默认只有两级)
      /*  for (HaMenuDO haMenuDO : haMenuDOMap.values()){
            System.out.println(JSON.toJSONString(haMenuDO));
        }*/
        for (HaMenuDO haMenuDO : haMenuDOMap.values()) {
            if (haMenuDOMap.containsKey(haMenuDO.getParentId())) {
                HaMenuDO haMenuDOParent = haMenuDOMap.get(haMenuDO.getParentId());
                if (CollectionUtils.isEmpty(haMenuDOParent.getHaMenuDOList())) {
                    haMenuDOParent.setHaMenuDOList(new ArrayList<HaMenuDO>());
                }
                haMenuDOParent.getHaMenuDOList().add(haMenuDO);
            }
        }

        for (HaMenuDO haMenuDO : haMenuDOMap.values()) {
            if (1 == haMenuDO.getLevel()) {
                haMenuDOListNew.add(haMenuDO);
            }
        }

        return haMenuDOListNew;

    }

    private String getCacheKey(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        if (userMenuQuery != null && userMenuOptionDTO != null) {
            return USER_MENU_INFO_CACHE_HEAD + "_" +userMenuQuery.getProjectCode()+"_"+ userMenuQuery.getDomain() +
                    "_" + userMenuQuery.getUserId() +
                    "_" + userMenuOptionDTO.isContainUrl();
        } else {
            return null;
        }
    }

    private boolean catchUserMenu(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO, MemPageResult<HaMenuDO> menuDOMemPageResult) {
        log.debug("catchUserMenu,key={},", getCacheKey(userMenuQuery, userMenuOptionDTO));
        String key = getCacheKey(userMenuQuery, userMenuOptionDTO);
        return key != null && menuDOMemPageResult != null && menuDOMemPageResult.getList() != null
                && menuDOMemPageResult.getList().size() > 0 && cacheManager.addToTair(key, menuDOMemPageResult, DEFAULT_CACHE_VALID_TIME);
    }

    private MemPageResult<HaMenuDO> getUserMenuFromCatch(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        log.debug("getUserMenuFromCatch,key={},", getCacheKey(userMenuQuery, userMenuOptionDTO));
        String key = getCacheKey(userMenuQuery, userMenuOptionDTO);
        if (key != null) {
            return (MemPageResult<HaMenuDO>) cacheManager.getFormTair(key);
        } else {
            return null;
        }
    }


    /**
     * 根据用户ID获取用户权限列表, 服务器缓存
     *
     * @param userMenuQuery
     * @param userMenuOptionDTO
     * @return
     */
    @Override
    public MemPageResult<HaMenuDO> getMenuListByUserIdFromCatch(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        MemPageResult<HaMenuDO> menuDOMemPageResult = getUserMenuFromCatch(userMenuQuery, userMenuOptionDTO);
        if (menuDOMemPageResult == null) {
            log.debug("user {} menu catch is null", userMenuQuery.getUserId());
            menuDOMemPageResult = getMenuListByUserId(userMenuQuery, userMenuOptionDTO);
            if (menuDOMemPageResult != null && menuDOMemPageResult.getList() != null && menuDOMemPageResult.getList().size() > 0) {
                catchUserMenu(userMenuQuery, userMenuOptionDTO, menuDOMemPageResult);
            }
        }
        return menuDOMemPageResult;
    }

    /**
     *  缓存用户权限列表
     * @param userMenuQuery
     * @param userMenuOptionDTO
     * @return
     */
    public boolean catchUserMenu(UserMenuQuery userMenuQuery, UserMenuOptionDTO userMenuOptionDTO) {
        log.debug("catchUserMenu,key={},", getCacheKey(userMenuQuery, userMenuOptionDTO));
        MemPageResult<HaMenuDO> menuDOMemPageResult = getMenuListByUserId(userMenuQuery, userMenuOptionDTO);
        return catchUserMenu(userMenuQuery, userMenuOptionDTO, menuDOMemPageResult);

    }
}
