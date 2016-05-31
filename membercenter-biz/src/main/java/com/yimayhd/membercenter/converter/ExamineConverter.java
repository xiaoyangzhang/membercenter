/*
 * FileName: ExaminConverter.java
 * Author:   liubb
 * Date:     2016年3月24日 下午9:12:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yimayhd.fhtd.utils.PicFeatureUtil;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.enums.ExamineDetail;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.enums.ExamineType;
import com.yimayhd.membercenter.enums.PictureUrl;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.enums.CertificateOption;
import com.yimayhd.user.client.enums.MerchantOption;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核对象转换〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineConverter {

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param examineDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ExamineInfoDTO examineDOToDTO(ExamineDO examineDO) {
        if(null == examineDO){
            return null;
        }
        ExamineInfoDTO examinDTO = new ExamineInfoDTO();
        // 图片
        Map<String, String> pictureMap = PicFeatureUtil.fromString(examineDO.getPicturesUrl());
        // 信息明细
        Map<String, String> featureMap = PicFeatureUtil.fromString(examineDO.getFeature());
        // 达人技能
        Map<String, String> certificateMap = PicFeatureUtil.fromString(examineDO.getCertificate());
        examinDTO.setId(examineDO.getId());
        examinDTO.setDomainId(examineDO.getDomainId());
        examinDTO.setType(examineDO.getType());
        examinDTO.setExaminStatus(examineDO.getStatues());
        examinDTO.setSellerId(examineDO.getSellerId());
        examinDTO.setSellerName(ParmCheckUtil.checkString(examineDO.getSellerName()));
        //店铺名称
        examinDTO.setMerchantName(featureMap.get(ExamineDetail.MERCHANT_NAME.getId()));
        examinDTO.setLegralName(featureMap.get(ExamineDetail.LEGRAL_NAME.getId()));
        examinDTO.setAddress(featureMap.get(ExamineDetail.ADDRESS.getId()));
        examinDTO.setSaleScope(ParmCheckUtil.checkString(examineDO.getSaleScope()));
        examinDTO.setLegralCardUp(pictureMap.get(PictureUrl.LEGRAL_CARD_UP.getId()));
        examinDTO.setLegralCardDown(pictureMap.get(PictureUrl.LEGRAL_CARD_DOWN.getId()));
        examinDTO.setBusinessLicense(pictureMap.get(PictureUrl.BUSINESS_LICENSE.getId()));
        examinDTO.setOrgCard(pictureMap.get(PictureUrl.ORG_CARD.getId()));
        examinDTO.setAffairsCard(pictureMap.get(PictureUrl.AFFAIRS_CARD.getId()));
        examinDTO.setOpenCard(pictureMap.get(PictureUrl.OPEN_CARD.getId()));
        examinDTO.setTravingCard(pictureMap.get(PictureUrl.TRAVING_CARD.getId()));
        examinDTO.setTouchProve(pictureMap.get(PictureUrl.TOUCH_PROVE.getId()));
        examinDTO.setTravelAgencyAuthorization(pictureMap.get(PictureUrl.TRAVEL_AGENCY_AUTHORIZATION.getId()));
        examinDTO.setTravelAgencyInsurance(pictureMap.get(PictureUrl.TRAVEL_AGENCY_INSURANCE.getId()));
        examinDTO.setPrincipleName(examineDO.getPrincipalName());
        examinDTO.setPrincipleCard(featureMap.get(ExamineDetail.PRINCIPAL_CRAD.getId()));
        examinDTO.setPrincipleCardId(featureMap.get(ExamineDetail.PRINCIPAL_CRAD_ID.getId()));
        examinDTO.setPrincipleTel(examineDO.getTelNum());
        examinDTO.setPrincipleMail(featureMap.get(ExamineDetail.PRINCIPAL_MAIL.getId()));
        examinDTO.setPrincipleCardUp(pictureMap.get(PictureUrl.PRINCIPAL_CARD_UP.getId()));
        examinDTO.setPrincipleCardDown(pictureMap.get(PictureUrl.PRINCIPAL_CARD_DOWN.getId()));
        examinDTO.setFinanceOpenBankId(featureMap.get(ExamineDetail.FINANCE_OPEN_BANK_ID.getId()));
        examinDTO.setFinanceOpenBankName(featureMap.get(ExamineDetail.FINANCE_OPEN_BANK_NAME.getId()));
        examinDTO.setFinanceOpenName(featureMap.get(ExamineDetail.FINANCE_OPEN_NAME.getId()));
        examinDTO.setAccountNum(featureMap.get(ExamineDetail.ACCOUNT_NUM.getId()));
        examinDTO.setAccountBankProvince(featureMap.get(ExamineDetail.ACCOUNT_BANK_PROVINCE.getId()));
        examinDTO.setAccountBankProvinceCode(featureMap.get(ExamineDetail.ACCOUNT_BANK_PROVINCE_CODE.getId()));
        examinDTO.setAccountBankCity(featureMap.get(ExamineDetail.ACCOUNT_BANK_CITY.getId()));
        examinDTO.setAccountBankCityCode(featureMap.get(ExamineDetail.ACCOUNT_BANK_CITY_CODE.getId()));
        examinDTO.setAccountBankName(featureMap.get(ExamineDetail.ACCOUNT_BANK_NAME.getId()));
        examinDTO.setCooperation1(pictureMap.get(PictureUrl.COOPERATION1.getId()));
        examinDTO.setCooperation2(pictureMap.get(PictureUrl.COOPERATION2.getId()));
        examinDTO.setCooperation3(pictureMap.get(PictureUrl.COOPERATION3.getId()));
        examinDTO.setCooperation4(pictureMap.get(PictureUrl.COOPERATION4.getId()));
        examinDTO.setCooperation5(pictureMap.get(PictureUrl.COOPERATION5.getId()));
        //2期
//        examinDTO.setAmusementParkReport(pictureMap.get(PictureUrl.AMUSEMENTPARK_REPORT.getId()));
//        examinDTO.setHotelGoodsAuthorization(pictureMap.get(PictureUrl.HOTEL_GOODS_AUTHORIZATION.getId()));
//        examinDTO.setIsDirectSale(examineDO.getIsDirectSale());
//        examinDTO.setWildlifeSale(pictureMap.get(PictureUrl.WILDLIFE_SALE.getId()));
//        examinDTO.setWaterWildlifeSale(pictureMap.get(PictureUrl.WATER_WILDLIFE_SALE.getId()));
//        examinDTO.setSpecialSaleLicense(pictureMap.get(PictureUrl.SPECIAL_SALE_LICENSE.getId()));
//        examinDTO.setSpecialSaleAuthorization(pictureMap.get(PictureUrl.SPECIAL_SALE_AUTHORIZATION.getId()));
//        examinDTO.setSeaTransportationLicense(pictureMap.get(PictureUrl.SEA_TRANSPORTATION_LICENSE.getId()));
//        examinDTO.setScenicTicketUpScanning(pictureMap.get(PictureUrl.SCENIE_TICKET_UP_SCANNING.getId()));
//        examinDTO.setScenicTicketDownScanning(pictureMap.get(PictureUrl.SCENIE_TICKET_DOWN_SCANNING.getId()));
//        examinDTO.setScenicTicketAuthorization(pictureMap.get(PictureUrl.SCENIC_TICKET_AUTHORIZATION.getId()));
//        examinDTO.setScenicQualityLevel(pictureMap.get(PictureUrl.SCENIC_QUALITY_LEVEL.getId()));
//        examinDTO.setScenicPriceRegister(pictureMap.get(PictureUrl.SCENIC_PRICE_REGISTER.getId()));
//        examinDTO.setScenicGoodsAuthorization(pictureMap.get(PictureUrl.SCENIC_GOODS_AUTHORIZATION.getId()));
//        examinDTO.setRelationBetweenHotelAngGroup(pictureMap.get(PictureUrl.RELATION_BETWEEN_HOTEL_AND_GROUP.getId()));
//        examinDTO.setMerchantCategoryId(examineDO.getMerchantCategoryId());
        // type
        examinDTO.setTouristCard(certificateMap.get(CertificateOption.TOURIST_CARD.getCode()));
        examinDTO.setDrivingLinence(certificateMap.get(CertificateOption.DRIVING_LICENCE.getCode()));
        examinDTO.setDivingLinence(certificateMap.get(CertificateOption.DIVING_LICENCE.getCode()));
        examinDTO.setPhotographyCertificate(certificateMap.get(CertificateOption.PHOTOGRAPHY_CERTIFICATE.getCode()));
        examinDTO.setClimbingCertificate(certificateMap.get(CertificateOption.CLIMBING_CERTIFICATE.getCode()));
        examinDTO.setTrainingCertificate(certificateMap.get(CertificateOption.TRAINING_CERTIFICATE.getCode()));
        examinDTO.setTeacherCertificate(certificateMap.get(CertificateOption.TEACHER_CERTIFICATE.getCode()));
        examinDTO.setArtCertificate(certificateMap.get(CertificateOption.ART_CERTIFICATE.getCode()));

        examinDTO.setCreateDate(examineDO.getGmtCreated());
        return examinDTO;
    } 

    /**
     * 
     * 功能描述: <br>
     * 〈审核信息DTO转DO〉
     *
     * @param examinDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ExamineDO examinDTOToDO(ExamineInfoDTO examinDTO) {
        ExamineDO examineDO = new ExamineDO();
        Map<String, String> pictureMap = new HashMap<String, String>();
        Map<String, String> featureMap = new HashMap<String, String>();
        Map<String, String> certificateMap = new HashMap<String, String>();
        featureMap.put(ExamineDetail.LEGRAL_NAME.getId(), examinDTO.getLegralName());
        featureMap.put(ExamineDetail.ADDRESS.getId(), examinDTO.getAddress());
        featureMap.put(ExamineDetail.PRINCIPAL_CRAD.getId(), examinDTO.getPrincipleCard());
        featureMap.put(ExamineDetail.PRINCIPAL_CRAD_ID.getId(), examinDTO.getPrincipleCardId());
        featureMap.put(ExamineDetail.PRINCIPAL_MAIL.getId(), examinDTO.getPrincipleMail());
        featureMap.put(ExamineDetail.FINANCE_OPEN_BANK_ID.getId(), examinDTO.getFinanceOpenBankId());
        featureMap.put(ExamineDetail.FINANCE_OPEN_BANK_NAME.getId(), examinDTO.getFinanceOpenBankName());
        featureMap.put(ExamineDetail.FINANCE_OPEN_NAME.getId(), examinDTO.getFinanceOpenName());
        featureMap.put(ExamineDetail.ACCOUNT_NUM.getId(), examinDTO.getAccountNum());
        featureMap.put(ExamineDetail.ACCOUNT_BANK_PROVINCE.getId(), examinDTO.getAccountBankProvince());
        featureMap.put(ExamineDetail.ACCOUNT_BANK_PROVINCE_CODE.getId(), examinDTO.getAccountBankProvinceCode());
        featureMap.put(ExamineDetail.ACCOUNT_BANK_CITY.getId(), examinDTO.getAccountBankCity());
        featureMap.put(ExamineDetail.ACCOUNT_BANK_CITY_CODE.getId(), examinDTO.getAccountBankCityCode());
        featureMap.put(ExamineDetail.ACCOUNT_BANK_NAME.getId(), examinDTO.getAccountBankName());
        featureMap.put(ExamineDetail.MERCHANT_NAME.getId(), examinDTO.getMerchantName());
        //featureMap.put(ExamineDetail.SHOP_TYPE.getId(), examinDTO.getShopType());//2期-店铺性质
        pictureMap.put(PictureUrl.LEGRAL_CARD_UP.getId(), examinDTO.getLegralCardUp());
        pictureMap.put(PictureUrl.LEGRAL_CARD_DOWN.getId(), examinDTO.getLegralCardDown());
        pictureMap.put(PictureUrl.BUSINESS_LICENSE.getId(), examinDTO.getBusinessLicense());
        pictureMap.put(PictureUrl.ORG_CARD.getId(), examinDTO.getOrgCard());
        pictureMap.put(PictureUrl.AFFAIRS_CARD.getId(), examinDTO.getAffairsCard());
        pictureMap.put(PictureUrl.OPEN_CARD.getId(), examinDTO.getOpenCard());
        pictureMap.put(PictureUrl.TRAVING_CARD.getId(), examinDTO.getTravingCard());
        pictureMap.put(PictureUrl.TOUCH_PROVE.getId(), examinDTO.getTouchProve());
        pictureMap.put(PictureUrl.TRAVEL_AGENCY_AUTHORIZATION.getId(), examinDTO.getTravelAgencyAuthorization());
        pictureMap.put(PictureUrl.TRAVEL_AGENCY_INSURANCE.getId(), examinDTO.getTravelAgencyInsurance());
        pictureMap.put(PictureUrl.PRINCIPAL_CARD_UP.getId(), examinDTO.getPrincipleCardUp());
        pictureMap.put(PictureUrl.PRINCIPAL_CARD_DOWN.getId(), examinDTO.getPrincipleCardDown());
        pictureMap.put(PictureUrl.COOPERATION1.getId(), examinDTO.getCooperation1());
        pictureMap.put(PictureUrl.COOPERATION2.getId(), examinDTO.getCooperation2());
        pictureMap.put(PictureUrl.COOPERATION3.getId(), examinDTO.getCooperation3());
        pictureMap.put(PictureUrl.COOPERATION4.getId(), examinDTO.getCooperation4());
        pictureMap.put(PictureUrl.COOPERATION5.getId(), examinDTO.getCooperation5());
        //2期
        examineDO.setIsDirectSale(examinDTO.getIsDirectSale());
        examineDO.setMerchantCategoryId(examinDTO.getMerchantCategoryId());
        //2期 -资质
//        pictureMap.put(PictureUrl.AMUSEMENTPARK_REPORT.getId(), examinDTO.getAmusementParkReport());
//        pictureMap.put(PictureUrl.HOTEL_GOODS_AUTHORIZATION.getId(), examinDTO.getHotelGoodsAuthorization());
        //pictureMap.put(PictureUrl.INTERMEDIARY_LICENSE.getId(), examinDTO.getInterMediaryLicense());
//        pictureMap.put(PictureUrl.RELATION_BETWEEN_HOTEL_AND_GROUP.getId(), examinDTO.getRelationBetweenHotelAngGroup());
//        pictureMap.put(PictureUrl.SCENIC_GOODS_AUTHORIZATION.getId(), examinDTO.getScenicGoodsAuthorization());
//        pictureMap.put(PictureUrl.SCENIC_PRICE_REGISTER.getId(), examinDTO.getScenicPriceRegister());
//        pictureMap.put(PictureUrl.SCENIC_QUALITY_LEVEL.getId(), examinDTO.getScenicQualityLevel());
//        pictureMap.put(PictureUrl.SCENIC_TICKET_AUTHORIZATION.getId(), examinDTO.getScenicTicketAuthorization());
//        pictureMap.put(PictureUrl.SCENIE_TICKET_DOWN_SCANNING.getId(), examinDTO.getScenicTicketDownScanning());
//        pictureMap.put(PictureUrl.SCENIE_TICKET_UP_SCANNING.getId(), examinDTO.getScenicTicketUpScanning());
//        pictureMap.put(PictureUrl.SEA_TRANSPORTATION_LICENSE.getId(), examinDTO.getSeaTransportationLicense());
//        pictureMap.put(PictureUrl.WILDLIFE_SALE.getId(), examinDTO.getWildlifeSale());
//        pictureMap.put(PictureUrl.WATER_WILDLIFE_SALE.getId(), examinDTO.getWaterWildlifeSale());
//        pictureMap.put(PictureUrl.TRAVEL_AGENCY_BRANCH_AGREEMENT.getId(), examinDTO.getTravelAgencyBranchAgreement());
//        pictureMap.put(PictureUrl.SPECIAL_SALE_LICENSE.getId(), examinDTO.getSpecialSaleLicense());
//        pictureMap.put(PictureUrl.SPECIAL_SALE_AUTHORIZATION.getId(), examinDTO.getSpecialSaleAuthorization());
        if(examinDTO.getType() == ExamineType.TALENT.getType()){
            certificateMap.put(CertificateOption.TOURIST_CARD.getCode(), examinDTO.getTouristCard());
            certificateMap.put(CertificateOption.DRIVING_LICENCE.getCode(), examinDTO.getDrivingLinence());
            certificateMap.put(CertificateOption.DIVING_LICENCE.getCode(), examinDTO.getDivingLinence());
            certificateMap.put(CertificateOption.PHOTOGRAPHY_CERTIFICATE.getCode(), examinDTO.getPhotographyCertificate());
            certificateMap.put(CertificateOption.CLIMBING_CERTIFICATE.getCode(), examinDTO.getClimbingCertificate());
            certificateMap.put(CertificateOption.TRAINING_CERTIFICATE.getCode(), examinDTO.getTrainingCertificate());
            certificateMap.put(CertificateOption.TEACHER_CERTIFICATE.getCode(), examinDTO.getTeacherCertificate());
            certificateMap.put(CertificateOption.ART_CERTIFICATE.getCode(), examinDTO.getArtCertificate());
            
            // 技能
            examineDO.setCertificate(PicFeatureUtil.toString(certificateMap));  
        }
        // 附属属性
        examineDO.setFeature(PicFeatureUtil.toString(featureMap));
        // 图片
        examineDO.setPicturesUrl(PicFeatureUtil.toString(pictureMap));

        examineDO.setId(examinDTO.getId());
        examineDO.setPrincipalName(examinDTO.getPrincipleName());
        examineDO.setTelNum(examinDTO.getPrincipleTel());
        examineDO.setSaleScope(examinDTO.getSaleScope());
        examineDO.setSellerId(examinDTO.getSellerId());
        examineDO.setSellerName(examinDTO.getSellerName());
        examineDO.setDomainId(examinDTO.getDomainId());
        examineDO.setType(examinDTO.getType());
        // 默认审核中
        examineDO.setStatues(ExamineStatus.EXAMIN_NOT_ABLE.getStatus());
        examineDO.setGmtCreated(new Date());
        examineDO.setGmtModified(new Date());
        return examineDO;

    }

    
    /**
     * 
     * 功能描述: <br>
     * 〈账号信息〉
     *
     * @param examineDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static AccountDTO examineToAccount(ExamineDO examineDO){
        if(null == examineDO){
            return null;
        }
        AccountDTO accountDTO = new AccountDTO();
        // 信息明细
        Map<String, String> featureMap = PicFeatureUtil.fromString(examineDO.getFeature());
        accountDTO.setFinanceOpenName(featureMap.get(ExamineDetail.FINANCE_OPEN_NAME.getId()));
        accountDTO.setAccountNum(featureMap.get(ExamineDetail.ACCOUNT_NUM.getId()));
        accountDTO.setAccountBankProvince(featureMap.get(ExamineDetail.ACCOUNT_BANK_PROVINCE.getId()));
        accountDTO.setAccountBankProvinceCode(featureMap.get(ExamineDetail.ACCOUNT_BANK_PROVINCE_CODE.getId()));
        accountDTO.setAccountBankCity(featureMap.get(ExamineDetail.ACCOUNT_BANK_CITY.getId()));
        accountDTO.setAccountBankCityCode(featureMap.get(ExamineDetail.ACCOUNT_BANK_CITY_CODE.getId()));
        accountDTO.setAccountBankName(featureMap.get(ExamineDetail.ACCOUNT_BANK_NAME.getId()));
        accountDTO.setProducterName(featureMap.get(ExamineDetail.PRODUCTER_NAME.getId()));
        accountDTO.setProducterTel(featureMap.get(ExamineDetail.PRODUCTER_TEL.getId()));
        accountDTO.setProducterMail(featureMap.get(ExamineDetail.PRODUCTER_MAIL.getId()));
        accountDTO.setFinanceName(featureMap.get(ExamineDetail.FINANCE_NAME.getId()));
        accountDTO.setFinanceTel(featureMap.get(ExamineDetail.FINANCE_TEL.getId()));
        accountDTO.setFinanceMail(featureMap.get(ExamineDetail.FINANCE_MAIL.getId()));
        
        accountDTO.setDomainId(examineDO.getDomainId());
        accountDTO.setSellerId(examineDO.getSellerId());
        accountDTO.setType(examineDO.getType());
        return accountDTO;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈新增账号信息〉
     *
     * @param examineDO
     * @param accountDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ExamineDO accountToExamine(ExamineDO examineDO, AccountDTO accountDTO){
        ExamineDO examine =  new ExamineDO();
        // 信息明细
        Map<String, String> featureMap = PicFeatureUtil.fromString(examineDO.getFeature());
        featureMap.put(ExamineDetail.PRODUCTER_NAME.getId(), accountDTO.getProducterName());
        featureMap.put(ExamineDetail.PRODUCTER_TEL.getId(), accountDTO.getProducterTel());
        featureMap.put(ExamineDetail.PRODUCTER_MAIL.getId(), accountDTO.getProducterMail());
        featureMap.put(ExamineDetail.FINANCE_NAME.getId(), accountDTO.getFinanceName());
        featureMap.put(ExamineDetail.FINANCE_TEL.getId(), accountDTO.getFinanceName());
        featureMap.put(ExamineDetail.FINANCE_MAIL.getId(), accountDTO.getFinanceMail());
        examineDO.setFeature(PicFeatureUtil.toString(featureMap));
        examine.setGmtModified(new Date());
        examine.setId(examineDO.getId());
        return examineDO;
    }
    /**
     * 
     * 功能描述: <br>
     * 〈审核状态信息转换〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ExamineDO examineDealToDO(ExamineDealDTO examineDealDTO) {
        ExamineDO examineDO = new ExamineDO();
        examineDO.setDomainId(examineDealDTO.getDomainId());
        examineDO.setSellerId(examineDealDTO.getSellerId());
        examineDO.setType(examineDealDTO.getType());
        examineDO.setId(examineDealDTO.getId());
        // 判断审核是否通过
        if (examineDealDTO.isCheckIsOk()) {
            examineDO.setStatues(ExamineStatus.EXAMIN_OK.getStatus());
        } else {
            examineDO.setStatues(ExamineStatus.EXAMIN_ERROR.getStatus());
        }
        examineDO.setExamineMes(examineDealDTO.getExamineMes());
        examineDO.setGmtModified(new Date());
        examineDO.setReviewerId(examineDealDTO.getReviewerId());
        return examineDO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈查询〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ExamineDO examineQueryToDO(InfoQueryDTO examineQueryDTO) {
        ExamineDO examineDO = new ExamineDO();
        examineDO.setSellerId(examineQueryDTO.getSellerId());
        examineDO.setDomainId(examineQueryDTO.getDomainId());
        examineDO.setType(examineQueryDTO.getType());
        return examineDO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈审核信息转店铺信息〉
     *
     * @param examineDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MerchantDO examineToMerchant(ExamineDO examineDO){
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setSellerId(examineDO.getSellerId());
        merchantDO.setDomainId(examineDO.getDomainId());
        merchantDO.setMerchantPrincipalTel(examineDO.getTelNum());
        merchantDO.setMerchantPrincipal(ParmCheckUtil.checkString(examineDO.getPrincipalName()));
        //信息明细
        Map<String, String> featureMap = PicFeatureUtil.fromString(examineDO.getFeature());
        merchantDO.setAddress(ParmCheckUtil.checkString(featureMap.get(ExamineDetail.ADDRESS.getId())));
        //店铺名称
        merchantDO.setName(ParmCheckUtil.checkString(featureMap.get(ExamineDetail.MERCHANT_NAME.getId())));
        //达人
        if(ExamineType.TALENT.getType() == examineDO.getType()){
            merchantDO.setOption(MerchantOption.TALENT.getOption());
            List<CertificateOption> options = new ArrayList<CertificateOption>();
            //身份证
            options.add(CertificateOption.ID_CARD);
            //达人技能
            Map<String, String> certificateMap = PicFeatureUtil.fromString(examineDO.getCertificate());
            if(null != certificateMap && 0 != certificateMap.size()){
                for (Map.Entry<String, String> entry : certificateMap.entrySet()) {
                    CertificateOption option = CertificateOption.valueOfCode(entry.getKey());
                    if(null != option){
                        options.add(option);
                    }
                }
            }
            merchantDO.setCertificate(
                        CertificateOption.addOption(options.toArray(new CertificateOption[options.size()])));
        }else{
            merchantDO.setOption(MerchantOption.MERCHANT.getOption());
        }
        return merchantDO;
    }
}
