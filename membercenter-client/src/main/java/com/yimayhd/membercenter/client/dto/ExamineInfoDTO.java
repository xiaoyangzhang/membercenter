/*
 * FileName: ExaminDTO.java
 * Author:   liubb
 * Date:     2016年3月24日 下午7:50:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yimayhd.membercenter.client.domain.MerchantScopeDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantQualificationDO;

/**
 * 〈一句话功能简述〉<br>
 * 〈审核基本信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExamineInfoDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = 9159164458112785789L;
    // id
    private long id;

    private long sellerId;
    //商户名称
    private String sellerName;
    
    private int domainId;
    //审核状态
    private int examinStatus;
    //入住类型
    private int type;
    // 店铺名称    此字段只有店铺有 达人无
    private String merchantName;
    // 法人姓名
    private String legralName;
    // 营业地址
    private String address;
    // 经营范围
    private String saleScope;
    // 法人身份证正面
    private String legralCardUp;
    // 法人身份证反面
    private String legralCardDown;
    // 营业执照副本正面
    private String businessLicense;
    // 组织机构代码证正面
    private String orgCard;
    // 税务登记证正面
    private String affairsCard;
    // 开户许可证正面
    private String openCard;
    // 旅行社业务经营许可证正面
    private String travingCard;
    // 联系人变更证明
    private String touchProve;
    //旅行社授权书
    private String travelAgencyAuthorization;
    //旅行社责任险证明正面
    private String travelAgencyInsurance;

    // *******************负责人信息****************************//
    // 商户负责人姓名
    private String principleName;
    // 负责人证件类型名称
    private String principleCard;
    // 负责人证件号码
    private String principleCardId;
    // 负责人手机号码
    private String principleTel;
    // 负责人邮箱
    private String principleMail;
    // 负责人身份证正面
    private String principleCardUp;
    // 负责人身份证反面
    private String principleCardDown;

    // ******************财务信息*********************//

    // 财务开户银行ID
    private String financeOpenBankId;
    // 财务开户银行NAME
    private String financeOpenBankName;
    // 财务开户名称
    private String financeOpenName;
    // 财务结算账号
    private String accountNum;
    // 财务结算开户行省份
    private String accountBankProvince;
    // 财务结算开户行省份code
    private String accountBankProvinceCode;
    // 财务结算开户行城市
    private String accountBankCity;
    // 财务结算开户行城市CODE
    private String accountBankCityCode;
    // 开户行名称
    private String accountBankName;

    // *****************合作合同******************//
    // 合同1
    private String cooperation1;
    // 合同2
    private String cooperation2;
    // 合同3
    private String cooperation3;
    // 合同4
    private String cooperation4;
    // 合同5
    private String cooperation5;

    // ********************达人资质证明****************************//
    // 导游证
    private String touristCard;
    // 行驶证
    private String drivingLinence;
    // 潜水证
    private String divingLinence;
    // 摄影证
    private String photographyCertificate;
    // 登山证
    private String climbingCertificate;
    // 健身教练证
    private String trainingCertificate;
    // 教师证
    private String teacherCertificate;
    // 美术证
    private String artCertificate;
    //申请时间
    private Date createDate;
    //手持身份证
    private String cardInHand;
    private int isDirectSale;//是否直营
	private long merchantCategoryId;//商家身份
	 // ********************2期商户资质证明****************************//
//	private String interMediaryLicense;//因私出入境中介机构经营许可证正面
//	private String seaTransportationLicense;//水路运输许可证正面
//	private String travelAgencyBranchAgreement;//旅行社分社补充协议
//	private String scenicTicketAuthorization;//景点门票授权书或合作协议
//	private String hotelGoodsAuthorization;//酒店商品授权书
//	private String  specialSaleLicense; //特种经营许可证副本复印件
//	private String specialSaleAuthorization;//旅游局特许经营授权书
//	private String wildlifeSale;//野生动物经营利用许可证
//	private String waterWildlifeSale;//水生野生动物经营利用许可证
//	private String amusementParkReport;//游乐特种设备（备案）登记证明和游乐设备最新的定期检测报告
//	private String scenicPriceRegister;//景区门票价格在物价局的备案登记证明
//	private String scenicTicketUpScanning;//景区门票扫描件正面
//	private String scenicTicketDownScanning;//景区门票扫描件反面
//	private String scenicGoodsAuthorization;//景区商品授权书
//	private String scenicQualityLevel;//景区质量等级证书
//	private String relationBetweenHotelAngGroup;//酒店与集团的所属关系说明
	
	private String lawPersonCard;//法人身份证号
	private String saleLicenseNumber;//营业执照号
	private String taxRegisterNumber;//税务登记号
	private Set<Long> idSet = new HashSet<Long>();
	/**
	 * 2.0优化:
	 */
    /**
     *账户类型
     */
    private String accountType;


	/**
     * 开户人身份证
     */
    private String openerCard;
    /**
     * 结算联行号
     */
    private String settlementCard;
    /**
     * 开户人手机号（银行预留手机号）
     */
    private String openerTel;

    public String getOpenerTel() {
		return openerTel;
	}
	public void setOpenerTel(String openerTel) {
		this.openerTel = openerTel;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getOpenerCard() {
		return openerCard;
	}
	public void setOpenerCard(String openerCard) {
		this.openerCard = openerCard;
	}
	public String getSettlementCard() {
		return settlementCard;
	}
	public void setSettlementCard(String settlementCard) {
		this.settlementCard = settlementCard;
	}
	public Set<Long> getIdSet() {
		return idSet;
	}
	public void setIdSet(Set<Long> idSet) {
		this.idSet = idSet;
	}
	public String getLawPersonCard() {
		return lawPersonCard;
	}
	public void setLawPersonCard(String lawPersonCard) {
		this.lawPersonCard = lawPersonCard;
	}
	public String getSaleLicenseNumber() {
		return saleLicenseNumber;
	}
	public void setSaleLicenseNumber(String saleLicenseNumber) {
		this.saleLicenseNumber = saleLicenseNumber;
	}
	public String getTaxRegisterNumber() {
		return taxRegisterNumber;
	}
	public void setTaxRegisterNumber(String taxRegisterNumber) {
		this.taxRegisterNumber = taxRegisterNumber;
	}

	private List<MerchantQualificationDO> merchantQualifications;//商家与资质关联对象集合
	private List<MerchantScopeDO> merchantScopes;//商家与经营范围关联对象集合
	
	
	public List<MerchantQualificationDO> getMerchantQualifications() {
		return merchantQualifications;
	}
	public void setMerchantQualifications(
			List<MerchantQualificationDO> merchantQualifications) {
		this.merchantQualifications = merchantQualifications;
	}
	public List<MerchantScopeDO> getMerchantScopes() {
		return merchantScopes;
	}
	public void setMerchantScopes(List<MerchantScopeDO> merchantScopes) {
		this.merchantScopes = merchantScopes;
	}
//	public String getInterMediaryLicense() {
//		return interMediaryLicense;
//	}
//	public void setInterMediaryLicense(String interMediaryLicense) {
//		this.interMediaryLicense = interMediaryLicense;
//	}
//	public String getSeaTransportationLicense() {
//		return seaTransportationLicense;
//	}
//	public void setSeaTransportationLicense(String seaTransportationLicense) {
//		this.seaTransportationLicense = seaTransportationLicense;
//	}
//	public String getTravelAgencyBranchAgreement() {
//		return travelAgencyBranchAgreement;
//	}
//	public void setTravelAgencyBranchAgreement(String travelAgencyBranchAgreement) {
//		this.travelAgencyBranchAgreement = travelAgencyBranchAgreement;
//	}
//	public String getScenicTicketAuthorization() {
//		return scenicTicketAuthorization;
//	}
//	public void setScenicTicketAuthorization(String scenicTicketAuthorization) {
//		this.scenicTicketAuthorization = scenicTicketAuthorization;
//	}
//	public String getHotelGoodsAuthorization() {
//		return hotelGoodsAuthorization;
//	}
//	public void setHotelGoodsAuthorization(String hotelGoodsAuthorization) {
//		this.hotelGoodsAuthorization = hotelGoodsAuthorization;
//	}
//	public String getSpecialSaleLicense() {
//		return specialSaleLicense;
//	}
//	public void setSpecialSaleLicense(String specialSaleLicense) {
//		this.specialSaleLicense = specialSaleLicense;
//	}
//	public String getSpecialSaleAuthorization() {
//		return specialSaleAuthorization;
//	}
//	public void setSpecialSaleAuthorization(String specialSaleAuthorization) {
//		this.specialSaleAuthorization = specialSaleAuthorization;
//	}
//	public String getWildlifeSale() {
//		return wildlifeSale;
//	}
//	public void setWildlifeSale(String wildlifeSale) {
//		this.wildlifeSale = wildlifeSale;
//	}
//	public String getWaterWildlifeSale() {
//		return waterWildlifeSale;
//	}
//	public void setWaterWildlifeSale(String waterWildlifeSale) {
//		this.waterWildlifeSale = waterWildlifeSale;
//	}
//	public String getAmusementParkReport() {
//		return amusementParkReport;
//	}
//	public void setAmusementParkReport(String amusementParkReport) {
//		this.amusementParkReport = amusementParkReport;
//	}
//	public String getScenicPriceRegister() {
//		return scenicPriceRegister;
//	}
//	public void setScenicPriceRegister(String scenicPriceRegister) {
//		this.scenicPriceRegister = scenicPriceRegister;
//	}
//	public String getScenicTicketUpScanning() {
//		return scenicTicketUpScanning;
//	}
//	public void setScenicTicketUpScanning(String scenicTicketUpScanning) {
//		this.scenicTicketUpScanning = scenicTicketUpScanning;
//	}
//	public String getScenicTicketDownScanning() {
//		return scenicTicketDownScanning;
//	}
//	public void setScenicTicketDownScanning(String scenicTicketDownScanning) {
//		this.scenicTicketDownScanning = scenicTicketDownScanning;
//	}
//	public String getScenicGoodsAuthorization() {
//		return scenicGoodsAuthorization;
//	}
//	public void setScenicGoodsAuthorization(String scenicGoodsAuthorization) {
//		this.scenicGoodsAuthorization = scenicGoodsAuthorization;
//	}
//	public String getScenicQualityLevel() {
//		return scenicQualityLevel;
//	}
//	public void setScenicQualityLevel(String scenicQualityLevel) {
//		this.scenicQualityLevel = scenicQualityLevel;
//	}
//	public String getRelationBetweenHotelAngGroup() {
//		return relationBetweenHotelAngGroup;
//	}
//	public void setRelationBetweenHotelAngGroup(String relationBetweenHotelAngGroup) {
//		this.relationBetweenHotelAngGroup = relationBetweenHotelAngGroup;
//	}
	public long getMerchantCategoryId() {
		return merchantCategoryId;
	}
	public void setMerchantCategoryId(long merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}
	public int getIsDirectSale() {
		return isDirectSale;
	}
	public void setIsDirectSale(int isDirectSale) {
		this.isDirectSale = isDirectSale;
	}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLegralName() {
        return legralName;
    }

    public void setLegralName(String legralName) {
        this.legralName = legralName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSaleScope() {
        return saleScope;
    }

    public void setSaleScope(String saleScope) {
        this.saleScope = saleScope;
    }

    public String getLegralCardUp() {
        return legralCardUp;
    }

    public void setLegralCardUp(String legralCardUp) {
        this.legralCardUp = legralCardUp;
    }

    public String getLegralCardDown() {
        return legralCardDown;
    }

    public void setLegralCardDown(String legralCardDown) {
        this.legralCardDown = legralCardDown;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getOrgCard() {
        return orgCard;
    }

    public void setOrgCard(String orgCard) {
        this.orgCard = orgCard;
    }

    public String getAffairsCard() {
        return affairsCard;
    }

    public void setAffairsCard(String affairsCard) {
        this.affairsCard = affairsCard;
    }

    public String getOpenCard() {
        return openCard;
    }

    public void setOpenCard(String openCard) {
        this.openCard = openCard;
    }

    public String getTravingCard() {
        return travingCard;
    }

    public void setTravingCard(String travingCard) {
        this.travingCard = travingCard;
    }

    public String getTouchProve() {
        return touchProve;
    }

    public void setTouchProve(String touchProve) {
        this.touchProve = touchProve;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public String getPrincipleCard() {
        return principleCard;
    }

    public void setPrincipleCard(String principleCard) {
        this.principleCard = principleCard;
    }

    public String getPrincipleCardId() {
        return principleCardId;
    }

    public void setPrincipleCardId(String principleCardId) {
        this.principleCardId = principleCardId;
    }

    public String getPrincipleTel() {
        return principleTel;
    }

    public void setPrincipleTel(String principleTel) {
        this.principleTel = principleTel;
    }

    public String getPrincipleMail() {
        return principleMail;
    }

    public void setPrincipleMail(String principleMail) {
        this.principleMail = principleMail;
    }

    public String getPrincipleCardUp() {
        return principleCardUp;
    }

    public void setPrincipleCardUp(String principleCardUp) {
        this.principleCardUp = principleCardUp;
    }

    public String getPrincipleCardDown() {
        return principleCardDown;
    }

    public void setPrincipleCardDown(String principleCardDown) {
        this.principleCardDown = principleCardDown;
    }

    public String getFinanceOpenBankId() {
        return financeOpenBankId;
    }

    public void setFinanceOpenBankId(String financeOpenBankId) {
        this.financeOpenBankId = financeOpenBankId;
    }

    public String getFinanceOpenBankName() {
        return financeOpenBankName;
    }

    public void setFinanceOpenBankName(String financeOpenBankName) {
        this.financeOpenBankName = financeOpenBankName;
    }

    public String getFinanceOpenName() {
        return financeOpenName;
    }

    public void setFinanceOpenName(String financeOpenName) {
        this.financeOpenName = financeOpenName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountBankProvince() {
        return accountBankProvince;
    }

    public void setAccountBankProvince(String accountBankProvince) {
        this.accountBankProvince = accountBankProvince;
    }

    public String getAccountBankProvinceCode() {
        return accountBankProvinceCode;
    }

    public void setAccountBankProvinceCode(String accountBankProvinceCode) {
        this.accountBankProvinceCode = accountBankProvinceCode;
    }

    public String getAccountBankCity() {
        return accountBankCity;
    }

    public void setAccountBankCity(String accountBankCity) {
        this.accountBankCity = accountBankCity;
    }

    public String getAccountBankCityCode() {
        return accountBankCityCode;
    }

    public void setAccountBankCityCode(String accountBankCityCode) {
        this.accountBankCityCode = accountBankCityCode;
    }

    public String getAccountBankName() {
        return accountBankName;
    }

    public void setAccountBankName(String accountBankName) {
        this.accountBankName = accountBankName;
    }

    public String getCooperation1() {
        return cooperation1;
    }

    public void setCooperation1(String cooperation1) {
        this.cooperation1 = cooperation1;
    }

    public String getCooperation2() {
        return cooperation2;
    }

    public void setCooperation2(String cooperation2) {
        this.cooperation2 = cooperation2;
    }

    public String getCooperation3() {
        return cooperation3;
    }

    public void setCooperation3(String cooperation3) {
        this.cooperation3 = cooperation3;
    }

    public String getCooperation4() {
        return cooperation4;
    }

    public void setCooperation4(String cooperation4) {
        this.cooperation4 = cooperation4;
    }

    public String getCooperation5() {
        return cooperation5;
    }

    public void setCooperation5(String cooperation5) {
        this.cooperation5 = cooperation5;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTouristCard() {
        return touristCard;
    }

    public void setTouristCard(String touristCard) {
        this.touristCard = touristCard;
    }

    public String getDrivingLinence() {
        return drivingLinence;
    }

    public void setDrivingLinence(String drivingLinence) {
        this.drivingLinence = drivingLinence;
    }

    public String getDivingLinence() {
        return divingLinence;
    }

    public void setDivingLinence(String divingLinence) {
        this.divingLinence = divingLinence;
    }

    public String getPhotographyCertificate() {
        return photographyCertificate;
    }

    public void setPhotographyCertificate(String photographyCertificate) {
        this.photographyCertificate = photographyCertificate;
    }

    public String getClimbingCertificate() {
        return climbingCertificate;
    }

    public void setClimbingCertificate(String climbingCertificate) {
        this.climbingCertificate = climbingCertificate;
    }

    public String getTrainingCertificate() {
        return trainingCertificate;
    }

    public void setTrainingCertificate(String trainingCertificate) {
        this.trainingCertificate = trainingCertificate;
    }

    public String getTeacherCertificate() {
        return teacherCertificate;
    }

    public void setTeacherCertificate(String teacherCertificate) {
        this.teacherCertificate = teacherCertificate;
    }

    public String getArtCertificate() {
        return artCertificate;
    }

    public void setArtCertificate(String artCertificate) {
        this.artCertificate = artCertificate;
    }

    public int getExaminStatus() {
        return examinStatus;
    }

    public void setExaminStatus(int examinStatus) {
        this.examinStatus = examinStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTravelAgencyAuthorization() {
        return travelAgencyAuthorization;
    }

    public void setTravelAgencyAuthorization(String travelAgencyAuthorization) {
        this.travelAgencyAuthorization = travelAgencyAuthorization;
    }

    public String getTravelAgencyInsurance() {
        return travelAgencyInsurance;
    }

    public void setTravelAgencyInsurance(String travelAgencyInsurance) {
        this.travelAgencyInsurance = travelAgencyInsurance;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCardInHand() {
        return cardInHand;
    }

    public void setCardInHand(String cardInHand) {
        this.cardInHand = cardInHand;
    }
    
}