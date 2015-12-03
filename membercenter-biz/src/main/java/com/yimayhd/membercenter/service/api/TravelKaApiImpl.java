package com.yimayhd.membercenter.service.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimayhd.membercenter.entity.*;
import com.yimayhd.membercenter.entity.KaClub;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.TravelKa;
import com.yimayhd.membercenter.entity.TravelKaClub;
import com.yimayhd.membercenter.entity.TravelKaPageInfoList;
import com.yimayhd.user.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.api.TravelKaApi;
import com.yimayhd.membercenter.client.domain.MemberProfileDO;
import com.yimayhd.membercenter.client.domain.UserAbilityRelationDO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.client.result.BasePageResult;
import com.yimayhd.membercenter.conventer.TravelKaConverter;
import com.yimayhd.membercenter.manager.MemberProfileManager;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.enums.BaseStatus;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;
import net.pocrd.dubboext.DubboExtProperty;

/**
 * Created by Administrator on 2015/11/14.
 */
public class TravelKaApiImpl implements TravelKaApi {

    private static final Logger logger = LoggerFactory.getLogger(TravelKaApiImpl.class);
    @Autowired
    private MemberProfileManager memberProfileManager;

    @HttpApi(name = "membercenter.getTravelKaDetail", desc = "查询旅游咖信息", security = SecurityType.RegisteredDevice, owner = "侯冬辉")
    public TravelKa getTravelKaDetail(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "theUserId", desc = "用户id") long theUserId
    ){
        TravelKa travelKa = null;
        try{
            if(theUserId <=0){
                DubboExtProperty.setErrorCode(MemberReturnCode.PARAMTER_ERROR);
                return null;
            }
            logger.info("TravelKaApiImpl method getTravelKaDetail userId:"+ theUserId);
            //1 . 根据用户id 查询 大咖详情
            MemberProfileDO memberProfileDO = memberProfileManager.getMemberProfileByUserId(theUserId);
            UserDO userDO = memberProfileManager.getUserDOById(theUserId);
            if(memberProfileDO != null){
                //2. 根据用户id 去用户中心 查询用户详情
                //3. 查询大咖能力
                List<UserAbilityRelationDO> userAbilityRelationDOs =  memberProfileManager.getUserAbilityRelationByUserId(theUserId);
                //4. 组装数据
                travelKa = TravelKaConverter.converntTravelKaDetail(userAbilityRelationDOs, memberProfileDO, userDO);
                travelKa.isTravelKa = String.valueOf(BaseStatus.YES.getType());
                TravelKaClub travelKaClub = memberProfileManager.getTravelKaClub(theUserId);
                travelKa.travelKaClub = travelKaClub;

            } else {
                // 只返回用户信息即可
                TravelKaConverter.converntTravelKaDetail4UserInfo(userDO);
                travelKa.isTravelKa = String.valueOf(BaseStatus.NO.getType());
            }
        }catch (Exception e){
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            logger.error("TravelKaApiImpl method getTravelKaDetail error",e);
        }
        return travelKa;
    }


    @HttpApi(name = "user.getTravelKaListPage", desc = "返回旅游咖列表", security = SecurityType.RegisteredDevice, owner = "侯冬辉")
    public TravelKaPageInfoList getTravelKaListPage(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "pageInfo", desc = "分页信息") PageInfo pageInfo,
            @ApiParameter(required = true, name = "type", desc = "列表分类，人气：POPULARITY，新晋：NEWJOIN") String type
    ){
        TravelkaPageQuery pageQuery = null;
        TravelKaPageInfoList travelKaPageInfoList = null;
        try{
            TravelkaPageQuery travelkaPageQuery = TravelKaConverter.TravelkaPageQuery(pageInfo);
            String orderCol = "";
            if(type.equals("POPULARITY")){
                orderCol = "sort_column1"; // 人气
            }else if(type.equals("NEWJOIN")){
                orderCol = "sort_column2"; //新晋
            }
            travelkaPageQuery.setOrderbyCol(orderCol);
            BasePageResult<MemberProfileDO> basePageResult = memberProfileManager.pageQueryUser(travelkaPageQuery);
            List<MemberProfileDO> memberProfileDOs = basePageResult.getList();
            List<Long> userIds = transData(memberProfileDOs);
            List<UserDO> userList = memberProfileManager.getUserList(userIds);
            Map<Long,UserDO> userDOMap = praseUserData(userList);
            travelKaPageInfoList = TravelKaConverter.travelKaPageInfoList(memberProfileDOs,userDOMap,basePageResult.getPageNo(), basePageResult.isHasNext());
        }catch (Exception e){
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            logger.error("TravelKaApiImpl method getTravelKaListPage error",e);
        }

        return travelKaPageInfoList;
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


}
