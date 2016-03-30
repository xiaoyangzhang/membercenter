/*
 * FileName: TalentInfoDealService.java
 * Author:   liubb
 * Date:     2016年3月23日 上午10:07:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.service.back;

import java.util.List;

import com.yimayhd.membercenter.client.domain.CertificatesDO;
import com.yimayhd.membercenter.client.dto.AccountDTO;
import com.yimayhd.membercenter.client.dto.TalentInfoDTO;
import com.yimayhd.membercenter.client.query.InfoQueryDTO;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * 〈一句话功能简述〉<br> 
 * 〈 达人后台基本信息维护实现接口〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface TalentInfoDealService {
    
    /**
     * 
     * 功能描述: <br>
     * 〈获取达人服务类型〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<List<CertificatesDO>> queryTalentServiceType(); 
    
    /**
     * 
     * 功能描述: <br>
     * 〈获取达人认证信息〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<List<CertificatesDO>> queryTalentCertificates();
    
    /**
     * 
     * 功能描述: <br>
     * 〈更新达人基本信息〉
     *
     * @param talentInfoDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> updateTalentInfo(TalentInfoDTO talentInfoDO);
    
    /**
     * 
     * 功能描述: <br>
     * 〈查询达人基本信息 AND 关于我图文信息〉
     *
     * @param userId
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<TalentInfoDTO> queryTalentInfoByUserId(long userId, int domainId);
    
    /**
     * 
     * 功能描述: <br>
     * 〈查询账号信息〉
     *
     * @param infoQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<AccountDTO> queryMerchantAccountInfoById(InfoQueryDTO infoQueryDTO);
    
    
    /**
     * 
     * 功能描述: <br>
     * 〈更新账号信息〉
     *
     * @param accountDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    MemResult<Boolean> updateMerchantAccountInfo(AccountDTO accountDTO);
}
