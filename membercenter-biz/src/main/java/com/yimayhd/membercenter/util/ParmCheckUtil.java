/*
 * FileName: ParmCheckUtil.java
 * Author:   liubb
 * Date:     2016年3月23日 上午10:28:58
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.dto.ExamineDealDTO;
import com.yimayhd.membercenter.client.dto.ExamineInfoDTO;
import com.yimayhd.membercenter.client.dto.PictureTextDTO;
import com.yimayhd.membercenter.client.dto.TalentInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.entity.talent.TalentQuery;
import com.yimayhd.membercenter.enums.ExamineType;
import com.yimayhd.user.client.enums.ServiceTypeOption;

/**
 * 〈一句话功能简述〉<br>
 * 〈参数校验〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ParmCheckUtil {

    private static final int MIN_CODE = 0;

    /**
     * 
     * 功能描述: <br>
     * 〈检查UpdateTalentInfo参数〉
     *
     * @param talentInfoDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkUpdateTalentInfoNull(TalentInfoDTO talentInfoDTO) {
        // 参数校验
        if (MIN_CODE >= talentInfoDTO.getDomainId() || null == talentInfoDTO.getTalentInfoDO()
                || null == talentInfoDTO.getPictureTextDTO()) {
            return true;
        }
        TalentInfoDO talentInfoDO = talentInfoDTO.getTalentInfoDO();
        PictureTextDTO pictureTextDTO = talentInfoDTO.getPictureTextDTO();
        if (MIN_CODE >= talentInfoDO.getId() || StringUtils.isBlank(talentInfoDO.getAvatar())
                || StringUtils.isBlank(talentInfoDO.getNickName()) || StringUtils.isBlank(talentInfoDO.getReallyName())
                || StringUtils.isBlank(talentInfoDO.getCity()) || null == talentInfoDO.getBirthday()
                || ParmCheckUtil.checkListNull(pictureTextDTO.getPicTexts())
                || ParmCheckUtil.checkListNull(talentInfoDO.getPictures())
                || StringUtils.isBlank(talentInfoDO.getServeDesc())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈userId AND domainId 校验〉
     *
     * @param userId
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkUserIdAndDomainId(long userId, int domainId) {
        if (MIN_CODE >= userId || MIN_CODE >= domainId) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈检查list是否为空〉
     *
     * @param list
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> boolean checkListNull(List<T> list) {
        if (null == list || list.isEmpty() || 0 == list.size()) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈checkQueryMerchantList〉 || (StringUtils.isBlank(talentQuery.tagId) &&
     * StringUtils.isBlank(talentQuery.searchWord))
     * 
     * @param talentQuery
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkQueryMerchantList(TalentQuery talentQuery) {
        if (null == talentQuery || null == talentQuery.pageInfo || (StringUtils.isNotBlank(talentQuery.tagId)
                && null == ServiceTypeOption.valueOfCode(talentQuery.tagId))) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈检查审核信息〉
     *
     * @param examinDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkExamineDTO(ExamineInfoDTO examineInfoDTO) {
        if (MIN_CODE >= examineInfoDTO.getSellerId() || !ExamineType.has(examineInfoDTO.getType())
                || MIN_CODE >= examineInfoDTO.getDomainId()) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈审核处理参数校验〉
     *
     * @param examineDealDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkExamineDealDTO(ExamineDealDTO examineDealDTO) {
        if ((MIN_CODE >= examineDealDTO.getId() && (MIN_CODE >= examineDealDTO.getSellerId() || !ExamineType.has(examineDealDTO.getType())
                || MIN_CODE >= examineDealDTO.getDomainId())) || MIN_CODE >= examineDealDTO.getReviewerId()
                || StringUtils.isBlank(examineDealDTO.getExamineMes())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈查询参数校验〉
     *
     * @param examineQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkQueryDTO(InfoQueryDTO examineQueryDTO) {
        if (MIN_CODE >= examineQueryDTO.getSellerId()
                || (examineQueryDTO.getType() > 0 && !ExamineType.has(examineQueryDTO.getType()))
                || MIN_CODE >= examineQueryDTO.getDomainId()) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param accountDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkAccountInfoDTO(AccountDTO accountDTO) {
        if (MIN_CODE >= accountDTO.getSellerId() || !ExamineType.has(accountDTO.getType())
                || MIN_CODE >= accountDTO.getDomainId() || StringUtils.isBlank(accountDTO.getProducterName())
                || StringUtils.isBlank(accountDTO.getProducterTel())
                || StringUtils.isBlank(accountDTO.getProducterMail())
                || StringUtils.isBlank(accountDTO.getFinanceName()) || StringUtils.isBlank(accountDTO.getFinanceTel())
                || StringUtils.isBlank(accountDTO.getFinanceMail())) {
            return true;
        }
        return false;
    }
}
