package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimayhd.membercenter.client.domain.TravelKaVO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.conventer.TravelKaConverter;
import com.yimayhd.membercenter.entity.TravelKa;
import com.yimayhd.membercenter.entity.TravelKaClub;

import com.yimayhd.membercenter.repo.SnsRepo;
import com.yimayhd.user.client.enums.BaseStatus;
import net.pocrd.dubboext.DubboExtProperty;
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

    @Autowired
    private SnsRepo snsRepo; // sns

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

    public TravelKaClub getTravelKaClub(long userId) {
        return  snsRepo.getTravelKaClub(userId);
    }

    public TravelKa getTravelKaDetail(long userId){
        if(userId<=0){
            return null;
        }
        TravelKa travelKa = null;
        try{
            MemberProfileDO memberProfileDO = getMemberProfileByUserId(userId);
            UserDO userDO = getUserDOById(userId);
            TravelKaClub travelKaClub = getTravelKaClub(userId);
            if(memberProfileDO != null){
                List<UserAbilityRelationDO> userAbilityRelationDOs = getUserAbilityRelationByUserId(userId);
                travelKa = TravelKaConverter.converntTravelKaDetail(userAbilityRelationDOs, memberProfileDO, userDO);
                travelKa.isTravelKa = String.valueOf(BaseStatus.YES.getType());

            } else {
                // 只返回用户信息即可
                travelKa = TravelKaConverter.converntTravelKaDetail4UserInfo(userDO);
                travelKa.isTravelKa = String.valueOf(BaseStatus.NO.getType());
            }
            travelKa.travelKaClub = travelKaClub;
        }catch(Exception e){
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            LOGGER.error("MemberProfileManager method getTravelKaDetail error",e);
        }

        return travelKa;
    }

    public MemResult<TravelKaVO> getTravelKaVODetail(long userId){
        MemResult<TravelKaVO> result = new MemResult<TravelKaVO>();
        if(userId<=0){
            return null;
        }

        TravelKaVO travelKa = null;
        try{
            MemberProfileDO memberProfileDO = getMemberProfileByUserId(userId);
            UserDO userDO = getUserDOById(userId);
//            TravelKaClub travelKaClub = getTravelKaClub(userId);
            if(memberProfileDO != null){
                List<UserAbilityRelationDO> userAbilityRelationDOs = getUserAbilityRelationByUserId(userId);
                travelKa = TravelKaConverter.converntTravelKaVODetail(userAbilityRelationDOs, memberProfileDO, userDO);
            }
            result.setValue(travelKa);
        }catch(Exception e){
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            LOGGER.error("MemberProfileManager method getTravelKaVODetail error",e);
        }
        return result;
    }

    public MemPageResult<TravelKaVO> getTravelKaListManagerPage(TravelkaPageQuery travelkaPageQuery){
        MemPageResult<TravelKaVO> memPageResult = new MemPageResult<TravelKaVO>();
        memPageResult.setPageNo(travelkaPageQuery.getPageNo());
        memPageResult.setPageSize(travelkaPageQuery.getPageSize());

        travelkaPageQuery.setHasNextMod(true);

        BasePageResult basePageResult = pageQueryUserManager(travelkaPageQuery);
        List<MemberProfileDO> memberProfileDOs = basePageResult.getList();
        if(CollectionUtils.isEmpty(memberProfileDOs)){
            return null;
        }
        List<Long> userIds = transData(memberProfileDOs);
        List<UserDO> userList = getUserList(userIds);
        Map<Long,UserDO> userDOMap = praseUserData(userList);
        List<TravelKaVO> travelKaVOs = TravelKaConverter.convertTravelKaList(memberProfileDOs, userDOMap, basePageResult.getPageNo(), basePageResult.isHasNext());
        memPageResult.setList(travelKaVOs);
        return memPageResult;
    }

    private List<Long> transData(List<MemberProfileDO> list){
        List<Long> res = new ArrayList();
        if(list != null){
            for (MemberProfileDO memberProfileDO : list){
                Long userId = memberProfileDO.getUserId();
                res.add(userId);
            }
        }
        return res;
    }

    public Map<Long ,UserDO> praseUserData(List<UserDO> userList){
        Map<Long ,UserDO> map = new HashMap<Long, UserDO>();
        if(userList != null){
            for(UserDO userDO : userList){
                map.put(userDO.getId(),userDO);
            }
        }
        return map;
    }

    public BasePageResult<MemberProfileDO> pageQueryUserManager(TravelkaPageQuery userPageQuery) {
        BasePageResult<MemberProfileDO> basePageResult = new BasePageResult<MemberProfileDO>();

        try {
            basePageResult.setPageNo(userPageQuery.getPageNo());
            basePageResult.setPageSize(userPageQuery.getOldPageSize());
            List<MemberProfileDO> userDOs = memberProfileDOMapper.pageQueryManager(userPageQuery);
            basePageResult.setList(userDOs);
            if (userPageQuery.isNeedCount()) {
                int count = memberProfileDOMapper.queryCountManager(userPageQuery);
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


}
