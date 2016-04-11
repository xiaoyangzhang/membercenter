package com.yimayhd.membercenter.service.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.commentcenter.client.enums.IconType;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.api.MerchantApi;
import com.yimayhd.membercenter.client.domain.merchant.MerchantInfoDO;
import com.yimayhd.membercenter.client.query.MerchantQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.converter.TalentConverter;
import com.yimayhd.membercenter.entity.merchant.Merchant;
import com.yimayhd.membercenter.entity.merchant.MerchantInfo;
import com.yimayhd.membercenter.entity.merchant.MerchantList;
import com.yimayhd.membercenter.entity.merchant.MerchantQuery;
import com.yimayhd.membercenter.manager.talent.TalentInfoManager;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.enums.MerchantOption;

import net.pocrd.dubboext.DubboExtProperty;

/**
 * Created by zhaozhaonan on 2016/03/15
 *
 */
public class MerchantApiImpl implements MerchantApi {

    private static final Logger logger = LoggerFactory.getLogger(MerchantApiImpl.class);

    @Autowired
    TalentInfoManager talentInfoManager;

    /**
     * 查询商家基本信息
     */
    @Override
    public Merchant queryMerchantInfo(int appId, long domainId, long deviceId, long userId, int versionCode,
            long merchantId) {
        try {
            if (0 >= merchantId) {
                DubboExtProperty.setErrorCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("getTalentDetail merchantId is null");
                return null;
            }
            long start = System.currentTimeMillis();
            MemResult<MerchantUserDTO> result = talentInfoManager.queryTalentInfo(merchantId, domainId);
            Merchant merchant = new Merchant();
            if (result.isSuccess()) {
                MerchantInfoDO merchantInfoDO = MerchantConverter.merchantToDO(result.getValue().getMerchantDO(),
                        IconType.CATE.getType());
                merchant.sellerId = merchantInfoDO.getSellerId();
                merchant.name = merchantInfoDO.getName();
                merchant.avgprice = merchantInfoDO.getAvgprice();
                merchant.address = merchantInfoDO.getMerchantAddress();
                merchant.serviceTel = merchantInfoDO.getServiceTel();
                merchant.serviceTime = merchantInfoDO.getServiceTime();
                merchant.cityCode = String.valueOf(merchantInfoDO.getCityCode());
                merchant.city = merchantInfoDO.getCityName();
                merchant.latitude = merchantInfoDO.getLatitude();
                merchant.longitude = merchantInfoDO.getLongitude();
                merchant.icon = merchantInfoDO.getIcon();
                merchant.backPic = merchantInfoDO.getMerchantBackPic();
                // 技能数据转换
                merchant.certificates = TalentConverter.certificateConvert(merchantInfoDO.getCertificates());
                merchant.certificateType = IconType.CATE.getType();
                // return merchant;
            }
            // DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            logger.info("queryMerchantInfo par:{} return success, costs:{}ms", merchantId,
                    (System.currentTimeMillis() - start));
            return merchant;
        } catch (Exception e) {
            logger.error("queryMerchantInfo par:{} error:{}", merchantId, e);
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return null;
    }

    /**
     * 查询商家列表信息
     */
    @Override
    public MerchantList queryMerchantList(int appId, long domainId, long deviceId, long userId, int versionCode,
            MerchantQuery mechantQuery) {
        try {
            // 参数校验
            if (null == mechantQuery || StringUtils.isBlank(mechantQuery.merchantType)
                    || null == MerchantOption.valueOfName(mechantQuery.merchantType)) {
                DubboExtProperty.setErrorCode(MemberReturnCode.PARAMTER_ERROR);
                logger.info("queryMerchantList mechantQuery is null");
                return null;
            }
            long start = System.currentTimeMillis();
            MerchantQueryDTO merchantQueryDTO = MerchantConverter.merchantQuery(mechantQuery, domainId);
            MemPageResult<MerchantInfoDO> pageResult = talentInfoManager.queryMerchantList(merchantQueryDTO);
            MerchantList merchantList = new MerchantList();
            if (pageResult.isSuccess()) {
                merchantList.pageNo = pageResult.getPageNo();
                merchantList.hasNext = pageResult.isHasNext();
                List<MerchantInfo> list = new ArrayList<MerchantInfo>();
                for (MerchantInfoDO merchantInfoDO : pageResult.getList()) {
                    MerchantInfo merchant = new MerchantInfo();
                    merchant.sellerId = merchantInfoDO.getSellerId();
                    merchant.name = merchantInfoDO.getName();
                    merchant.avgprice = merchantInfoDO.getAvgprice();
                    merchant.cityCode = String.valueOf(merchantInfoDO.getCityCode());
                    merchant.city = merchantInfoDO.getCityName();
                    merchant.icon = merchantInfoDO.getIcon();
                    list.add(merchant);
                }
                merchantList.merchantList = list;
                // return merchantList;
            }
            // DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
            logger.info("queryMerchantList par:{} return success, costs:{}ms", JSONObject.toJSONString(mechantQuery),
                    (System.currentTimeMillis() - start));
            return merchantList;
        } catch (Exception e) {
            logger.error("queryMerchantList par:{} error:{}", mechantQuery, e);
            DubboExtProperty.setErrorCode(MemberReturnCode.SYSTEM_ERROR);
        }
        return null;
    }
}
