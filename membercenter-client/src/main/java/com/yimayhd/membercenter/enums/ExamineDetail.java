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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    LEGRAL_NAME("法人姓名", "1", 1),
    ADDRESS("营业地址", "2", 1),
    PRINCIPAL_CRAD("负责人证件类型", "3", 2),
    PRINCIPAL_CRAD_ID("负责人证件号码", "4", 2),
    PRINCIPAL_MAIL("负责人邮箱", "5", 2),
    FINANCE_OPEN_BANK_ID("财务开户银行ID", "6", 2),
    FINANCE_OPEN_BANK_NAME("财务开户银行", "7", 2),
    FINANCE_OPEN_NAME("财务开户名称", "8", 2),
    ACCOUNT_NUM("财务结算账号", "9", 2),
    ACCOUNT_BANK_PROVINCE("财务结算开户行省份", "10", 2),
    ACCOUNT_BANK_PROVINCE_CODE("财务结算开户行省份CODE", "11", 2),
    ACCOUNT_BANK_CITY("财务结算开户行城市", "12", 2),
    ACCOUNT_BANK_CITY_CODE("财务结算开户行城市CODE", "13", 2),
    ACCOUNT_BANK_NAME("财务结算开户行名称", "14", 2),
   // SHOP_TYPE("店铺性质","22",2),
    LEGAL_CARD_NUMBER("法人身份证号","22",1),
    SALE_LICENSE_NUMBER("营业执照号","23",1),
    TAX_REGISTER_NUMBER("税务登记号","24",1),
    //*******************账户信息*******************//
    PRODUCTER_NAME("产品联系人姓名", "15", 3),
    PRODUCTER_TEL("产品联系人手机", "16", 3),
    PRODUCTER_MAIL("产品联系人邮箱", "17", 3),
    FINANCE_NAME("财务联系人姓名", "18", 3),
    FINANCE_TEL("财务联系人手机", "19", 3),
    FINANCE_MAIL("财务联系人邮箱", "20", 3),
    
    
    //**********店铺名称************************//
    MERCHANT_NAME("店铺名称", "21", 1),
    ACCOUNT_TYPE("账户类型","25",2),
    OPENER_CARD("开户人身份证","26",2),
    SETTLEMENT_CARD("结算联行号","27",2),
    OPENER_TEL("开户人手机号","28",2);
    private String name;
    private String id;
    private int number;

    ExamineDetail(String name, String id, int number) {
        this.name = name;
        this.id = id;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    public int getNumber() {
        return number;
    }
    
    private static Map<Integer, List<String>> hashMap = new HashMap<Integer, List<String>>();

    static {
        List<String> list = null;
        for (ExamineDetail examineDetail : ExamineDetail.values()) {
            if (hashMap.containsKey(examineDetail.getNumber())) {
                list = hashMap.get(examineDetail.getNumber());
            } else {
                list = new ArrayList<String>();
            }
            list.add(examineDetail.getId());
            hashMap.put(examineDetail.getNumber(), list);
        }
    }
    
    public static ExamineDetail getPictureUrlById(String id) {
        for (ExamineDetail examineDetail : ExamineDetail.values()) {
            if (examineDetail.getId().equals(id)) {
                return examineDetail;
            }
        }
        return null;
    }

    public static int queryNumber(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            ExamineDetail examineDetail = getPictureUrlById(entry.getKey());
            if (null != examineDetail) {
                return examineDetail.getNumber();
            }
        }
        return 0;
    }

    
    public static Map<String, String> queryFullMap(Map<String, String> map) {
        int number = queryNumber(map);
        if(0 < number){
            for (String id : hashMap.get(number)) {
                if(!map.containsKey(id)){
                    map.put(id, "");
                }
            }
        }
        return map;
    }
}
