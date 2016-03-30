/*
 * FileName: TalentInfoDTO.java
 * Author:   liubb
 * Date:     2016年3月23日 下午3:59:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;

import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈后台达人基本信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentInfoDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = -5151652251927184745L;
    
    private int domainId;

    private TalentInfoDO talentInfoDO;//达人基本信息
    
    private PictureTextDTO pictureTextDTO;// 关于我

    public TalentInfoDO getTalentInfoDO() {
        return talentInfoDO;
    }

    public void setTalentInfoDO(TalentInfoDO talentInfoDO) {
        this.talentInfoDO = talentInfoDO;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public PictureTextDTO getPictureTextDTO() {
        return pictureTextDTO;
    }

    public void setPictureTextDTO(PictureTextDTO pictureTextDTO) {
        this.pictureTextDTO = pictureTextDTO;
    }
}
