/*
 * FileName: ExamineQueryDTO.java
 * Author:   liubb
 * Date:     2016年3月26日 下午7:15:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.query;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈基本信息查询〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class InfoQueryDTO implements Serializable {

    /**
     */
    private static final long serialVersionUID = -9063194530108515060L;

    //
    private int domainId;
    // 店铺ID
    private long sellerId;
    // 店铺类型
    private int type;
    public int getDomainId() {
        return domainId;
    }
    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }
    public long getSellerId() {
        return sellerId;
    }
    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
}
