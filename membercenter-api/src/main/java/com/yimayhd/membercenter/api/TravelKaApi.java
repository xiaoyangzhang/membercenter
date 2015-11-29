package com.yimayhd.membercenter.api;

import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.TravelKa;
import com.yimayhd.membercenter.entity.TravelKaPageInfoList;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

/**
 * Created by Administrator on 2015/11/14.
 */
@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = TravelKaApi.class, owner = "侯冬辉")
public interface TravelKaApi {

    @HttpApi(name = "membercenter.getTravelKaDetail", desc = "查询旅游咖信息", security = SecurityType.None, owner = "侯冬辉")
    public TravelKa getTravelKaDetail(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "theUserId", desc = "用户id") long theUserId
    );

    @HttpApi(name = "membercenter.getTravelKaListPage", desc = "返回旅游咖列表", security = SecurityType.None, owner = "侯冬辉")
    public TravelKaPageInfoList getTravelKaListPage(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "pageInfo", desc = "分页信息") PageInfo pageInfo,
            @ApiParameter(required = true, name = "type", desc = "列表分类，人气：POPULARITY，新晋：NEWJOIN") String type
    );

}
