package com.yimayhd.membercenter.converter;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.membercenter.client.domain.CertificatesDO;
import com.yimayhd.membercenter.client.domain.merchant.MerchantInfoDO;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.query.MerchantQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.entity.merchant.MerchantQuery;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.dto.MerchantDTO;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.enums.MerchantOption;
import com.yimayhd.user.client.enums.ServiceFacilityOption;
import com.yimayhd.user.client.enums.ServiceTypeOption;

/**
 * Created by zhaozhaonan 2016/3/15 18:05
 */
public class MerchantConverter {

    /**
     * 
     * 功能描述: <br>
     * 〈商铺基本信息〉
     *
     * @param merchantDO
     * @param userDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MerchantInfoDO merchantToDO(MerchantDO merchantDO, int type) {
        MerchantInfoDO merchantInfoDO = new MerchantInfoDO();
        merchantInfoDO.setSellerId(merchantDO.getSellerId());
        merchantInfoDO.setName(merchantDO.getName());
        merchantInfoDO.setServiceTime(merchantDO.getServiceTime());
        merchantInfoDO.setAvgprice(merchantDO.getAvgprice());
        merchantInfoDO.setCityCode(merchantDO.getCityCode());
        merchantInfoDO.setCityName(merchantDO.getCityName());
        merchantInfoDO.setIcon(merchantDO.getLogo());
        // 经度 纬度
        merchantInfoDO.setLatitude(merchantDO.getLat());
        merchantInfoDO.setLongitude(merchantDO.getLon());
        merchantInfoDO.setMerchantAddress(merchantDO.getAddress());
        merchantInfoDO.setMerchantBackPic(merchantDO.getBackgroudImage());
        merchantInfoDO.setMerchantTel(merchantDO.getMerchantPrincipalTel());
        merchantInfoDO.setServiceTel(merchantDO.getServiceTel());
        // 店铺服务类型
        List<ServiceFacilityOption> facilityOptions = ServiceFacilityOption
                .getContainedOptions(merchantDO.getServiceType());
        if (!ParmCheckUtil.checkListNull(facilityOptions) && type > 0) {
            List<CertificatesDO> certificates = new ArrayList<CertificatesDO>();
            for (ServiceFacilityOption option : facilityOptions) {
                CertificatesDO certificatesDO = new CertificatesDO();
                certificatesDO.setId(Integer.valueOf(option.getCode()));
                certificatesDO.setType(type);
                certificatesDO.setName(option.getDesc());
                certificates.add(certificatesDO);
            }
            merchantInfoDO.setCertificates(certificates);
        }
        return merchantInfoDO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈获取列表信息〉
     *
     * @param merchantDO
     * @param userDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MerchantInfoDO merchantToDO(MerchantDO merchantDO) {
        return merchantToDO(merchantDO, 0);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈查询条件〉
     *
     * @param merchantQuery
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MerchantQueryDTO merchantQuery(MerchantQuery merchantQuery, long domainId) {
        MerchantQueryDTO merchantQueryDTO = new MerchantQueryDTO();
        merchantQueryDTO.setDomainId(domainId);
        merchantQueryDTO.setMerchantType(merchantQuery.merchantType);
        merchantQueryDTO.setPageNo(merchantQuery.pageInfo.pageNo);
        merchantQueryDTO.setPageSize(merchantQuery.pageInfo.pageSize);
        return merchantQueryDTO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈店铺对象转换〉
     *
     * @param pageResult
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MemPageResult<MerchantInfoDO> merchant(MemPageResult<MerchantInfoDO> pageResult,
            MemPageResult<MerchantUserDTO> result) {
        // 开始遍历
        if (null != result && result.isSuccess()) {
            List<MerchantInfoDO> merchantList = new ArrayList<MerchantInfoDO>();
            for (MerchantUserDTO merchantUser : result.getList()) {
                // 判断是否为空
                if (null != merchantUser.getMerchantDO()) {
                    merchantList.add(merchantToDO(merchantUser.getMerchantDO()));
                }
            }
            pageResult.setList(merchantList);
            pageResult.setPageNo(result.getPageNo());
            pageResult.setHasNext(result.getTotalCount() > result.getPageNo() * result.getPageSize());
        } else {
            pageResult.setReturnCode(result.getReturnCode());
        }
        return pageResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈达人店铺基本信息转换〉
     *
     * @param talentInfoDO
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MerchantDO talentInfoConverterToMerchant(TalentInfoDO talentInfoDO, int domainId) {
        MerchantDO merchantDO = new MerchantDO();
        // 商家Id
        merchantDO.setSellerId(talentInfoDO.getId());
        merchantDO.setDomainId(domainId);
        merchantDO.setCityCode(talentInfoDO.getCityCode());
        merchantDO.setCityName(talentInfoDO.getCity());
        merchantDO.setTitle(talentInfoDO.getServeDesc().toUpperCase());
        merchantDO.setName(talentInfoDO.getNickName());
        merchantDO.setMerchantPrincipalTel(talentInfoDO.getTelNum());
        // 达人轮播图
        merchantDO.setLoopImages(talentInfoDO.getPictures());
        // 标注为达人
        merchantDO.setOption(MerchantOption.TALENT.getOption());
        // 达人技能转换 默认全选
        if (!ParmCheckUtil.checkListNull(talentInfoDO.getServiceTypes())) {
            List<ServiceTypeOption> options = new ArrayList<ServiceTypeOption>();
            for (CertificatesDO certificatesDO : talentInfoDO.getServiceTypes()) {
                ServiceTypeOption typeOption = ServiceTypeOption.valueOfCode(String.valueOf(certificatesDO.getId()));
                // 预防 null Exception
                if (null != typeOption) {
                    options.add(typeOption);
                }
            }
            // 达人服务类型
            if (!ParmCheckUtil.checkListNull(options)) {
                merchantDO.setServiceType(
                        ServiceTypeOption.addOption(options.toArray(new ServiceTypeOption[options.size()])));
            } else {
                // 没有则全部
                merchantDO.setServiceType(ServiceTypeOption.addOption(ServiceTypeOption.values()));
            }
        } else {
            merchantDO.setServiceType(ServiceTypeOption.addOption(ServiceTypeOption.values()));
        }
        return merchantDO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈商品DO转DTO〉
     *
     * @param merchantDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MerchantDTO merchantDOToDTO(MerchantDO merchantDO) {
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(merchantDO.getId());
        merchantDTO.setDomainId(merchantDO.getDomainId());
        merchantDTO.setAddress(merchantDO.getAddress());
        merchantDTO.setTitle(merchantDO.getTitle());
        merchantDTO.setName(merchantDO.getName());
        merchantDTO.setServiceType(merchantDO.getServiceType());
        merchantDTO.setServiceFacility(merchantDO.getServiceFacility());
        merchantDTO.setServiceTime(merchantDO.getServiceTime());
        merchantDTO.setSalesQuantity(merchantDO.getSalesQuantity());
        merchantDTO.setAvgprice(merchantDO.getAvgprice());
        merchantDTO.setServiceTel(merchantDO.getServiceTel());
        merchantDTO.setMerchantPrincipalTel(merchantDO.getMerchantPrincipalTel());
        merchantDTO.setLogoImage(merchantDO.getLogo());
        merchantDTO.setBackgroundImage(merchantDO.getBackgroudImage());
        merchantDTO.setLoopmages(merchantDO.getLoopImages());
        merchantDTO.setCityCode(merchantDO.getCityCode());
        merchantDTO.setCityName(merchantDO.getCityName());
        merchantDTO.setCertificate(merchantDO.getCertificate());
        merchantDTO.setLon(merchantDO.getLon());
        merchantDTO.setLat(merchantDO.getLat());
        return merchantDTO;
    }
}
