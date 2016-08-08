package com.yimayhd.membercenter.service.client;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.query.MenuListQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.HaMenuService;
import com.yimayhd.membercenter.dao.MenuDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xushubing on 2016/8/8.
 */
public class HaMenuServiceImpl implements HaMenuService {
    private static final Logger loger = LoggerFactory.getLogger(HaMenuServiceImpl.class);

    @Resource
    private MenuDao menuDao;

    /**
     * 根据id查询菜单
     *
     * @param id 菜单id
     * @return
     */
    @Override
    public MemResult<HaMenuDO> getMenuById(long id) {
        MemResult<HaMenuDO> menuDOMemResult = new MemResult<>();
        if (id <= 0) {
            loger.error("getMenuById parameter error,id={}", id);
            menuDOMemResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return menuDOMemResult;
        }
        HaMenuDO haMenuDO = menuDao.getById(id);
        if (haMenuDO == null) {
            loger.error("getMenuById return null error,id={}", id);
            menuDOMemResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return menuDOMemResult;
        }
        menuDOMemResult.setValue(haMenuDO);
        return menuDOMemResult;
    }

    /**
     * 添加菜单
     *
     * @param haMenuDO
     * @return
     */
    @Override
    public MemResult<HaMenuDO> addMenu(HaMenuDO haMenuDO) {
        MemResult<HaMenuDO> menuDOMemResult = new MemResult<>();
        if (haMenuDO == null) {
            loger.error("addMenu parameter error,parameter={}", JSON.toJSONString(haMenuDO));
            menuDOMemResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return menuDOMemResult;
        }
        HaMenuDO insert = menuDao.insert(haMenuDO);
        if (insert == null) {
            loger.error("addMenu return null error,parameter={}", JSON.toJSONString(haMenuDO));
            menuDOMemResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
            return menuDOMemResult;
        }
        menuDOMemResult.setValue(insert);
        return menuDOMemResult;
    }

    /**
     * 更新菜单状态
     *
     * @param id
     * @return
     */
    @Override
    public MemResult<Boolean> updateMenuStatus(long id) {
        MemResult<Boolean> menuDOMemResult = new MemResult<>();
        if (id <= 0) {
            loger.error("updateMenuStatus parameter error,id={}", id);
            menuDOMemResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return menuDOMemResult;
        }
        try {
            menuDao.delete(id);
        } catch (Exception e) {
            loger.error("updateMenuStatus  error,parameter={},e={}", id, e);
            menuDOMemResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
            return menuDOMemResult;
        }
        return menuDOMemResult;
    }

    /**
     * 分页查询菜单
     *
     * @param menuListQuery
     * @return
     */
    @Override
    public MemPageResult<List<HaMenuDO>> selectList(MenuListQuery menuListQuery) {
        return null;
    }


    /**
     * @param menuListQuery
     * @return
     */
    @Override
    public MemPageResult<List<HaMenuDO>> selectChildList(MenuListQuery menuListQuery) {
        return null;
    }
}
