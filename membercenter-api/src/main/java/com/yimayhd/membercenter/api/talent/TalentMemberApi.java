/*
 * FileName: TalentMemberApi.java
 * Author:   liubb
 * Date:     2016年3月15日 下午4:25:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.api.talent;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.entity.talent.TalentInfo;
import com.yimayhd.membercenter.entity.talent.TalentInfoList;
import com.yimayhd.membercenter.entity.talent.TalentQuery;

import net.pocrd.annotation.ApiAutowired;
import net.pocrd.annotation.ApiGroup;
import net.pocrd.annotation.ApiParameter;
import net.pocrd.annotation.DesignedErrorCode;
import net.pocrd.annotation.HttpApi;
import net.pocrd.define.CommonParameter;
import net.pocrd.define.SecurityType;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@ApiGroup(name = "membercenter", minCode = 16000000, maxCode = 17000000, codeDefine = MemberReturnCode.class, owner = "lbb")
public interface TalentMemberApi {
    
    /**
     * 
     * 功能描述: <br>
     * 〈达人主页详情〉
     *
     * @param appId
     * @param domainId
     * @param deviceId
     * @param userId
     * @param versionCode
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @HttpApi(name = "membercenter.getTalentDetail", desc = "达人页获取达人基本信息", security = SecurityType.None, owner = "lbb")
    @DesignedErrorCode({MemberReturnCode.MEMBER_NOT_FOUND_C})
    public TalentInfo getTalentDetail(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId, 
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "merchantId", desc = "达人Id") long merchantId);

    
    /**
     * 
     * 功能描述: <br>
     * 〈达人首页获取达人列表〉
     *  
     * @param appId
     * @param domainId
     * @param deviceId
     * @param userId
     * @param versionCode
     * @param tagId
     * @param cityCode
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @HttpApi(name = "membercenter.queryTalentList", desc = "获取达人列表", security = SecurityType.None, owner = "lbb")
    @DesignedErrorCode({MemberReturnCode.MEMBER_NOT_FOUND_C})
    public TalentInfoList queryTalentList(
            @ApiAutowired(CommonParameter.applicationId) int appId,
            @ApiAutowired(CommonParameter.domainId) int domainId, 
            @ApiAutowired(CommonParameter.deviceId) long deviceId,
            @ApiAutowired(CommonParameter.userId) long userId,
            @ApiAutowired(CommonParameter.versionCode) int versionCode,
            @ApiParameter(required = true, name = "talentQuery", desc = "达人列表查询") TalentQuery talentQuery
            );
    
}
