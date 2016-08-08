package com.yimayhd.membercenter.manager;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.query.MenuListQuery;
import com.yimayhd.membercenter.client.result.BasePageResult;
import com.yimayhd.membercenter.dao.MenuDao;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xushubing on 2016/6/8.
 */
public class MenuManager {
    private final static Logger logger = LoggerFactory.getLogger(MenuManager.class);
    @Autowired
    private MenuDao menuDao;

    public HaMenuDO getMenuById(long id) {
        if (id <= 0) {
            return null;
        }
        return menuDao.getById(id);
    }

    /**
     * 添加菜单
     *
     * @param haMenuDO
     * @return
     */
    public HaMenuDO addMenu(HaMenuDO haMenuDO) {
        menuDao.insert(haMenuDO);
        return haMenuDO;
    }

    public void updateMenuStatus(long id) {
        if (id > 0) {
            menuDao.delete(id);
        }
    }

    /**
     * 删除菜单，逻辑删除
     *
     * @param id 菜单id
     */
    public void deleteMenu(long id) {
        menuDao.delete(id);
    }

    /**
     * 分页查询菜单
     *
     * @param menuListQuery
     * @return
     */
    public BasePageResult<HaMenuDO> queryMenu(MenuListQuery menuListQuery) {
        BasePageResult<HaMenuDO> baseResult = new BasePageResult<HaMenuDO>();
        try {
            // 查询总数
            int count = menuDao.queryCount(menuListQuery);
            if (ParmCheckUtil.MIN_CODE >= count) {
                logger.info("queryMenu param:{}  queryCount is zero", JSONObject.toJSONString(menuListQuery));
                return baseResult;
            }
            // 分页查询
            List<HaMenuDO> haMenuDOs = menuDao.pageQuery(menuListQuery);
            baseResult.setList(haMenuDOs);
            baseResult.setTotalCount(count);
            baseResult.setPageNo(menuListQuery.getPageNo());
            baseResult.setHasNext(count > menuListQuery.getPageNo() * menuListQuery.getPageSize());
            logger.debug("queryMenu param:{} return success", JSONObject.toJSONString(menuListQuery));
        } catch (Exception e) {
            logger.error("queryMenu param:{} error, mes is:{}", JSONObject.toJSONString(menuListQuery), e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }
}
