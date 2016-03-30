/*
 * FileName: AccountDTO.java
 * Author:   liubb
 * Date:     2016年3月28日 下午4:12:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.dto;

import com.yimayhd.membercenter.client.query.InfoQueryDTO;

/**
 * 〈一句话功能简述〉<br> 
 * 〈账户信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AccountDTO extends InfoQueryDTO{

    /**
     */
    private static final long serialVersionUID = 831448783974295920L;
    
    /*********************收款信息**********************/
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
    
    /*********************联系人**********************/
    //产品联系人姓名
    private String producterName;
    //产品联系人手机
    private String producterTel;
    //产品联系人邮箱
    private String producterMail;
    //财务联系人姓名
    private String financeName;
    //财务联系人手机
    private String financeTel;
    //财务联系人邮箱
    private String financeMail;
    
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
    public String getProducterName() {
        return producterName;
    }
    public void setProducterName(String producterName) {
        this.producterName = producterName;
    }
    public String getProducterTel() {
        return producterTel;
    }
    public void setProducterTel(String producterTel) {
        this.producterTel = producterTel;
    }
    public String getProducterMail() {
        return producterMail;
    }
    public void setProducterMail(String producterMail) {
        this.producterMail = producterMail;
    }
    public String getFinanceName() {
        return financeName;
    }
    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }
    public String getFinanceTel() {
        return financeTel;
    }
    public void setFinanceTel(String financeTel) {
        this.financeTel = financeTel;
    }
    public String getFinanceMail() {
        return financeMail;
    }
    public void setFinanceMail(String financeMail) {
        this.financeMail = financeMail;
    }
}
