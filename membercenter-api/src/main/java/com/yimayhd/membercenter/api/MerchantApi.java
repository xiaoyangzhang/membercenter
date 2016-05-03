package com.yimayhd.membercenter.api;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.entity.merchant.Merchant;
import com.yimayhd.membercenter.entity.merchant.MerchantList;
import com.yimayhd.membercenter.entity.merchant.MerchantQuery;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.DesignedErrorCode;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

/**
 * Created by zhaozhaonan on 2016/03/15.
 */
@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = MemberReturnCode.class, owner = "zhaozhaonan、zhangjian")
public interface MerchantApi {

    @HttpApi(name = "membercenter.queryMerchantInfo", desc = "获取店铺信息", security = SecurityType.None, owner = "zhaozhaonan")
    @DesignedErrorCode({MemberReturnCode.SYSTEM_ERROR_C})
    public Merchant queryMerchantInfo(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) long domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "merchantId", desc = "店铺Id") long merchantId
    );

    
    @HttpApi(name = "membercenter.queryMerchantList", desc = "获取店铺列表", security = SecurityType.None, owner = "zhaozhaonan")
    @DesignedErrorCode({MemberReturnCode.SYSTEM_ERROR_C})
    public MerchantList queryMerchantList(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) long domainId,
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "mechantQuery", desc = "获取店铺列表") MerchantQuery mechantQuery
    );
    
}
