/*
 * FileName: MerchantQueryDTO.java
 * Author:   liubb
 * Date:     2016年3月21日 下午8:16:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.query;

/**
 * 〈一句话功能简述〉<br> 
 * 〈店铺查询〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MerchantQueryDTO extends PageQuery {

    /**
     */
    private static final long serialVersionUID = 6918696762434859834L;
    
    private long domainId;
    //店铺类型
    private String merchantType;

    public long getDomainId() {
        return domainId;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

}
