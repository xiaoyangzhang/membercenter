/*
 * FileName: TalentConverter.java
 * Author:   liubb
 * Date:     2016年3月19日 下午12:42:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.yimayhd.commentcenter.client.enums.IconType;
import com.yimayhd.membercenter.client.domain.CertificatesDO;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.query.talent.TalentQueryDTO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.entity.Certificates;
import com.yimayhd.membercenter.entity.talent.TalentQuery;
import com.yimayhd.membercenter.util.ParmCheckUtil;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.dto.MerchantUserDTO;
import com.yimayhd.user.client.enums.CertificateOption;
import com.yimayhd.user.client.enums.SequenceEnum;
import com.yimayhd.user.client.enums.ServiceTypeOption;
import com.yimayhd.user.client.enums.UserOptions;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentConverter {

    /**
     * 
     * 功能描述: <br>
     * 〈商家 用户信息组装达人信息〉
     *
     * @param merchantDO
     * @param userDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static TalentInfoDO merchantToTalent(MerchantDO merchantDO, UserDO userDO, int type) {
        TalentInfoDO talentInfoDO = new TalentInfoDO();
        talentInfoDO.setId(userDO.getId());
        talentInfoDO.setAvatar(userDO.getAvatar());
        talentInfoDO.setGender(userDO.getGender());
        talentInfoDO.setNickName(userDO.getNickname());
        // 图片数据处理 轮播图
        talentInfoDO.setPictures(merchantDO.getLoopImages());
        talentInfoDO.setServeDesc(merchantDO.getTitle());
        talentInfoDO.setServeCount(merchantDO.getSalesQuantity());
        talentInfoDO.setCity(userDO.getCity());
        talentInfoDO.setCityCode(userDO.getCityCode());
        talentInfoDO.setTelNum(merchantDO.getMerchantPrincipalTel());
        // 是否是认证用户
        talentInfoDO.setType(UserOptions.CERTIFICATED.has(userDO.getOptions()));
        List<CertificatesDO> certificates = new ArrayList<CertificatesDO>();
        // 需要根据达人与店铺区分具体技能
        if (IconType.EXPERT.getType() == type) {
            // 达人技能
            List<CertificateOption> certificateOptions = CertificateOption
                    .getContainedOptions(merchantDO.getCertificate());
            // type > 0 获取详情信息
            if (!ParmCheckUtil.checkListNull(certificateOptions)) {
                for (CertificateOption option : certificateOptions) {
                    CertificatesDO certificatesDO = new CertificatesDO();
                    certificatesDO.setId(Integer.valueOf(option.getCode()));
                    certificatesDO.setType(type);
                    certificatesDO.setName(option.getDesc());
                    certificates.add(certificatesDO);
                }
            }
        } else if (IconType.MUSTSHOP.getType() == type) {
            // 店铺服务类型
            List<ServiceTypeOption> facilityOptions = ServiceTypeOption
                    .getContainedOptions(merchantDO.getServiceType());
            // type > 0 获取详情信息
            if (!ParmCheckUtil.checkListNull(facilityOptions)) {
                for (ServiceTypeOption option : facilityOptions) {
                    CertificatesDO certificatesDO = new CertificatesDO();
                    certificatesDO.setId(Integer.valueOf(option.getCode()));
                    certificatesDO.setType(type);
                    certificatesDO.setName(option.getDesc());
                    certificates.add(certificatesDO);
                }

            }
        }
        talentInfoDO.setCertificates(certificates);
        return talentInfoDO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈详情〉
     *
     * @param merchantDO
     * @param userDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static TalentInfoDO merchantToTalent(MerchantDO merchantDO, UserDO userDO) {
        return merchantToTalent(merchantDO, userDO, 0);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈商家list数据转换〉
     *
     * @param pageResult
     * @param result
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static MemPageResult<TalentInfoDO> merchantListToTalentList(MemPageResult<TalentInfoDO> pageResult,
            MemPageResult<MerchantUserDTO> result) {
        // 开始遍历
        if (null != result && result.isSuccess()) {
            List<TalentInfoDO> talentList = new ArrayList<TalentInfoDO>();
            for (MerchantUserDTO merchantUser : result.getList()) {
                // 判断是否为空
                if (null != merchantUser.getMerchantDO() && null != merchantUser.getUserDO()) {
                    talentList.add(merchantToTalent(merchantUser.getMerchantDO(), merchantUser.getUserDO()));
                }
            }
            pageResult.setList(talentList);
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
     * 〈达人信息查询API对象转换〉
     *
     * @param talentQuery
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static TalentQueryDTO talentQuery(TalentQuery talentQuery, long domainId) {
        TalentQueryDTO talentQueryDTO = new TalentQueryDTO();
        talentQueryDTO.setDomainId(domainId);
        talentQueryDTO.setSearchWord(talentQuery.searchWord);
        if (StringUtils.isBlank(talentQuery.sort)
                || talentQuery.sort.equals(String.valueOf(SequenceEnum.ASC.getType()))) {
            talentQueryDTO.setSortType(false);
        } else {
            talentQueryDTO.setSortType(true);
        }
        talentQueryDTO.setTagId(talentQuery.tagId);
        talentQueryDTO.setPageNo(talentQuery.pageInfo.pageNo);
        talentQueryDTO.setPageSize(talentQuery.pageInfo.pageSize);
        return talentQueryDTO;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈技能对象转换〉
     *
     * @param certificatesDOs
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static List<Certificates> certificateConvert(List<CertificatesDO> certificatesDOs) {
        if (ParmCheckUtil.checkListNull(certificatesDOs)) {
            return null;
        }
        List<Certificates> list = new ArrayList<Certificates>();
        for (CertificatesDO certificatesDO : certificatesDOs) {
            Certificates certificates = new Certificates();
            certificates.id = certificatesDO.getId();
            certificates.name = certificatesDO.getName();
            // certificates.type = certificatesDO.getType();
            list.add(certificates);
        }
        return list;
    }
}
