/*
 * FileName: ExaminQueryDTO.java
 * Author:   liubb
 * Date:     2016年3月26日 下午3:17:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.query.examine;

import com.yimayhd.membercenter.client.query.PageQuery;

/**
 * 〈一句话功能简述〉<br>
 * 〈商家审核查询〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExaminePageQueryDTO extends PageQuery {

    /**
     */
    private static final long serialVersionUID = -6238696333832952449L;

    //
    private int domainId;
    // 店铺名称
    private String merchantName;
    // 店铺ID
    private long sellerId;
    // 店铺类型
    private int type;
    // 店铺负责人姓名
    private String principleName;
    // 店铺负责人联系方式
    private long principleTel;
    //审核状态
    private int status;

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public long getPrincipleTel() {
        return principleTel;
    }

    public void setPrincipleTel(long principleTel) {
        this.principleTel = principleTel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
