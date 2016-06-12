package com.yimayhd.membercenter.manager;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.HaRoleDO;
import com.yimayhd.membercenter.client.query.RoleListQuery;
import com.yimayhd.membercenter.client.result.BasePageResult;
import com.yimayhd.membercenter.dao.RoleDao;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xushubing on 2016/6/8.
 */
public class RoleManager {
    private final static Logger logger = LoggerFactory.getLogger(RoleManager.class);
    @Autowired
    private RoleDao roleDao;

    /**
     * 添加角色
     *
     * @param haMenuDO
     * @return
     */
    public HaRoleDO addMenu(HaRoleDO haRoleDO) {
        roleDao.insert(haRoleDO);
        return haRoleDO;
    }

    /**
     * 删除角色，逻辑删除
     *
     * @param id 角色id
     */
    public void deleteMenu(long id) {
        roleDao.delete(id);
    }

    /**
     * 分页查询角色
     *
     * @param roleListQuery
     * @return
     */
    public BasePageResult<HaRoleDO> queryRole(RoleListQuery roleListQuery) {
        BasePageResult<HaRoleDO> baseResult = new BasePageResult<HaRoleDO>();
        try {
            // 查询总数
            int count = roleDao.queryCount(roleListQuery);
            if (ParmCheckUtil.MIN_CODE >= count) {
                logger.info("queryRole param:{}  queryCount is zero", JSONObject.toJSONString(roleListQuery));
                return baseResult;
            }
            // 分页查询
            List<HaRoleDO> haRoleDOs = roleDao.pageQuery(roleListQuery);
            baseResult.setList(haRoleDOs);
            baseResult.setTotalCount(count);
            baseResult.setPageNo(roleListQuery.getPageNo());
            baseResult.setHasNext(count > roleListQuery.getPageNo() * roleListQuery.getPageSize());
            logger.debug("queryRole param:{} return success", JSONObject.toJSONString(roleListQuery));
        } catch (Exception e) {
            logger.error("queryRole param:{} error, mes is:{}", JSONObject.toJSONString(roleListQuery), e);
            baseResult.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return baseResult;
    }
}
