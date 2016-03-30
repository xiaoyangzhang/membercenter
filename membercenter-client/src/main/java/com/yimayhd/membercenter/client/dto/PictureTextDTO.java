/*
 * FileName: PictureTextDTO.java
 * Author:   liubb
 * Date:     2016年3月24日 上午10:23:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.membercenter.client.domain.PictureTextDO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图文详情〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PictureTextDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = -1380208367091099969L;
    //图文详情ID
    private long id;
    //图文详情列表
    private List<PictureTextDO> picTexts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PictureTextDO> getPicTexts() {
        return picTexts;
    }

    public void setPicTexts(List<PictureTextDO> picTexts) {
        this.picTexts = picTexts;
    }

}
