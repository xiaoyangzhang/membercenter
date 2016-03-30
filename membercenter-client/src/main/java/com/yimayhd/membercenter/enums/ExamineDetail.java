/*
 * FileName: ExaminDetail.java
 * Author:   liubb
 * Date:     2016年3月24日 下午7:37:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ExamineDetail {

    //14
    LEGRAL_NAME("法人姓名", "1"),
    ADDRESS("营业地址", "2"),
    PRINCIPAL_CRAD("负责人证件类型", "3"),
    PRINCIPAL_CRAD_ID("负责人证件号码", "4"),
    PRINCIPAL_MAIL("负责人邮箱", "5"),
    FINANCE_OPEN_BANK_ID("财务开户银行ID", "6"),
    FINANCE_OPEN_BANK_NAME("财务开户银行", "7"),
    FINANCE_OPEN_NAME("财务开户名称", "8"),
    ACCOUNT_NUM("财务结算账号", "9"),
    ACCOUNT_BANK_PROVINCE("财务结算开户行省份", "10"),
    ACCOUNT_BANK_PROVINCE_CODE("财务结算开户行省份CODE", "11"),
    ACCOUNT_BANK_CITY("财务结算开户行城市", "12"),
    ACCOUNT_BANK_CITY_CODE("财务结算开户行城市CODE", "13"),
    ACCOUNT_BANK_NAME("财务结算开户行名称", "14"),
    
    //*******************账户信息*******************//
    PRODUCTER_NAME("产品联系人姓名", "15"),
    PRODUCTER_TEL("产品联系人手机", "16"),
    PRODUCTER_MAIL("产品联系人邮箱", "17"),
    FINANCE_NAME("财务联系人姓名", "18"),
    FINANCE_TEL("财务联系人手机", "19"),
    FINANCE_MAIL("财务联系人邮箱", "20"),
    
    
    //**********店铺名称************************//
    MERCHANT_NAME("店铺名称", "21");
    
    private String name;
    private String id;

    ExamineDetail(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
