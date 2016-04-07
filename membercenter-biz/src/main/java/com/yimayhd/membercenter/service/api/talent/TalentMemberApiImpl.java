/*
 * FileName: TalentMemberApiImpl.java
 * Author:   liubb
 * Date:     2016年3月15日 下午4:53:39
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.service.api.talent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.commentcenter.client.enums.IconType;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.api.talent.TalentMemberApi;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.query.talent.TalentQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.TalentConverter;
import com.yimayhd.membercenter.entity.talent.TalentInfo;
import com.yimayhd.membercenter.entity.talent.TalentInfoList;
import com.yimayhd.membercenter.entity.talent.TalentQuery;
import com.yimayhd.membercenter.entity.talent.TalentUserInfo;
import com.yimayhd.membercenter.manager.talent.TalentInfoManager;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.dto.MerchantUserDTO;

import net.pocrd.dubboext.DubboExtProperty;

/**
 * 〈一句话功能简述〉<br>
 * 〈达人API接口实现类〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentMemberApiImpl implements TalentMemberApi {

    private static final Logger logger = LoggerFactory.getLogger(TalentMemberApiImpl.class);

    @Autowired
    TalentInfoManager talentInfoManager;

    @Override
    public TalentInfo getTalentDetail(int appId, int domainId, long deviceId, long userId, int versionCode,
            long merchantId) {
        try {
            if (0 >= merchantId) {
                DubboExtProperty.setErrorCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("getTalentDetail talentId is null");
                return null;
            }
            long start = System.currentTimeMillis();
            MemResult<MerchantUserDTO> result = talentInfoManager.queryTalentInfo(merchantId, domainId);
            TalentInfo talentInfo = new TalentInfo();
            if (result.isSuccess()) {
                // 达人基本信息
                TalentInfoDO talentInfoDO = TalentConverter.merchantToTalent(result.getValue().getMerchantDO(),
                        result.getValue().getUserDO(), IconType.EXPERT.getType());
                talentInfo.userId = talentInfoDO.getId();
                talentInfo.avatar = talentInfoDO.getAvatar();
                talentInfo.city = talentInfoDO.getCity();
                talentInfo.cityCode = String.valueOf(talentInfoDO.getCityCode());
                talentInfo.gender = String.valueOf(talentInfoDO.getGender());
                talentInfo.nickName = talentInfoDO.getNickName();
                talentInfo.serveCount = talentInfoDO.getServeCount();
                talentInfo.serveDesc = talentInfoDO.getServeDesc();
                talentInfo.telNum = talentInfoDO.getTelNum();
                talentInfo.type = talentInfoDO.isType();
                talentInfo.pictures = talentInfoDO.getPictures();
                // 技能数据转换
                talentInfo.certificates = TalentConverter.certificateConvert(talentInfoDO.getCertificates());
                talentInfo.certificateType = IconType.EXPERT.getType();
                // return talentInfo;
            }
            logger.info("getTalentDetail par:{} return success, costs:{}ms", merchantId,
                    (System.currentTimeMillis() - start));
            // DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            return talentInfo;
        } catch (Exception e) {
            logger.error("getTalentDetail par:{} error:{}", merchantId, e);
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return null;
    }

    @Override
    public TalentInfoList queryTalentList(int appId, int domainId, long deviceId, long userId, int versionCode,
            TalentQuery talentQuery) {
        try {
            // 参数校验
            if (ParmCheckUtil.checkQueryMerchantList(talentQuery)) {
                DubboExtProperty.setErrorCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryTalentList talentQuery is null");
                return null;
            }
            long start = System.currentTimeMillis();
            TalentQueryDTO talentQueryDTO = TalentConverter.talentQuery(talentQuery, domainId);
            MemPageResult<TalentInfoDO> pageResult = talentInfoManager.queryTalentList(talentQueryDTO);
            TalentInfoList talentInfoList = new TalentInfoList();
            if (pageResult.isSuccess()) {
                talentInfoList.pageNo = pageResult.getPageNo();
                talentInfoList.hasNext = pageResult.isHasNext();
                List<TalentUserInfo> list = new ArrayList<TalentUserInfo>();
                for (TalentInfoDO talentInfoDO : pageResult.getList()) {
                    TalentUserInfo talentUserInfo = new TalentUserInfo();
                    talentUserInfo.userId = talentInfoDO.getId();
                    talentUserInfo.avatar = talentInfoDO.getAvatar();
                    talentUserInfo.city = talentInfoDO.getCity();
                    talentUserInfo.cityCode = String.valueOf(talentInfoDO.getCityCode());
                    talentUserInfo.gender = String.valueOf(talentInfoDO.getGender());
                    talentUserInfo.nickName = talentInfoDO.getNickName();
                    talentUserInfo.serveCount = talentInfoDO.getServeCount();
                    talentUserInfo.type = talentInfoDO.isType();
                    talentUserInfo.serveDesc = talentInfoDO.getServeDesc();
                    list.add(talentUserInfo);
                }
                talentInfoList.talentList = list;
                // return talentInfoList;
            }
            // DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            logger.info("queryTalentList par:{} return success, costs:{}ms", JSONObject.toJSONString(talentQuery),
                    (System.currentTimeMillis() - start));
            return talentInfoList;

        } catch (Exception e) {
            logger.error("queryTalentList par:{} error:{}", JSONObject.toJSONString(talentQuery), e);
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return null;
    }
}
