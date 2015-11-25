package com.yimayhd.membercenter.api;

import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.TravelKa;


import com.yimayhd.membercenter.entity.TravelKaPageInfoList;
import com.yimayhd.membercenter.errorcode.TravelKaApiCode;

import net.pocrd.annotation.*;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

/**
 * Created by Administrator on 2015/11/14.
 */
@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = com.yimayhd.membercenter.api.TravelKaApi.class, owner = "侯冬辉")
public interface TravelKaApi {

    @HttpApi(name = "membercenter.getTravelKaDetail", desc = "查询旅游咖信息", security = SecurityType.None, owner = "侯冬辉")
    @DesignedErrorCode({TravelKaApiCode.C_INTERNAL_SERVER_ERROR})
    public TravelKa getTravelKaDetail(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "theUserId", desc = "用户id") long theUserId
    );

    @HttpApi(name = "membercenter.getTravelKaListPage", desc = "返回旅游咖列表", security = SecurityType.None, owner = "侯冬辉")
    @DesignedErrorCode({TravelKaApiCode.C_INTERNAL_SERVER_ERROR})
    public TravelKaPageInfoList getTravelKaListPage(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "pageInfo", desc = "分页信息") PageInfo pageInfo
    );

}
