package com.yimayhd.membercenter.manager;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberProfileDO;
import com.yimayhd.membercenter.client.domain.TerminalDeviceDO;
import com.yimayhd.membercenter.client.domain.UserAbilityRelationDO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.client.result.BasePageResult;
import com.yimayhd.membercenter.mapper.MemberProfileDOMapper;
import com.yimayhd.membercenter.mapper.TerminalDeviceDOMapper;
import com.yimayhd.membercenter.mapper.UserAbilityRelationMapper;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.UserDO;

/**
 * Created by Administrator on 2015/11/14.
 */
public class MemberProfileManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberProfileManager.class);


    @Autowired
    private MemberProfileDOMapper memberProfileDOMapper;

    @Autowired
    private UserAbilityRelationMapper userAbilityRelationMapper;

    @Autowired
    private TerminalDeviceDOMapper terminalDeviceDOMapper;

    @Autowired
    private UserRepo userRepo;   // 用户相关接口

    public List<UserAbilityRelationDO> getUserAbilityRelationByUserId(long userId) {
        return userAbilityRelationMapper.getByUserId(userId);
    }

    public MemberProfileDO getMemberProfileDetail(long id) {
        return memberProfileDOMapper.getById(id);
    }

    public MemberProfileDO getMemberProfileByUserId(long userId) {
        return memberProfileDOMapper.getByUserId(userId);
    }


    public UserDO getUserDOById(long userId) {
        return userRepo.getUserDOById(userId);
    }

    public List<UserDO> getUserList(List<Long> userIds) {
        return userRepo.getUserList(userIds);
    }


    public BasePageResult<MemberProfileDO> pageQueryUser(TravelkaPageQuery userPageQuery) {
        BasePageResult<MemberProfileDO> basePageResult = new BasePageResult<MemberProfileDO>();

        try {
            basePageResult.setPageNo(userPageQuery.getPageNo());
            basePageResult.setPageSize(userPageQuery.getOldPageSize());
            List<MemberProfileDO> userDOs = memberProfileDOMapper.pageQuery(userPageQuery);
            basePageResult.setList(userDOs);
            if (userPageQuery.isNeedCount()) {
                int count = memberProfileDOMapper.queryCount(userPageQuery);
                basePageResult.setTotalCount(count);
                basePageResult.setHasNext(count > userPageQuery.getPageNo() * userPageQuery.getOldPageSize());
            } else if (userPageQuery.isHasNextMod()) {
                if (!CollectionUtils.isEmpty(userDOs) && userDOs.size() > userPageQuery.getOldPageSize()) {
                    basePageResult.setHasNext(true);
                    userDOs.remove(userDOs.size() - 1);
                }
            }
        } catch (Exception e) {
            LOGGER.error("userPageQuery " + userPageQuery.toString(), e);
            basePageResult.setReturnCode(MemberReturnCode.DB_READ_FAILED);
        }

        return basePageResult;
    }

    public TerminalDeviceDO getDeviceByCode(String deviceCode) {
        if (StringUtils.isBlank(deviceCode)) {
            return null;
        }
        TerminalDeviceDO terminalDeviceDO = null;
        try {
            terminalDeviceDO = terminalDeviceDOMapper.getByDeviceCode(deviceCode);
        } catch (Exception e) {
            LOGGER.error("terminalDeviceDOMapper.getByDeviceCode(deviceCode); exception ,", e);
        }
        return terminalDeviceDO;
    }


}