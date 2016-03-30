/*
 * FileName: PictureUrl.java
 * Author:   liubb
 * Date:     16年3月24日 下午5:21:17
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
public enum PictureUrl {
    
    LEGRAL_CARD_UP("法人身份证正面", "1"),
    LEGRAL_CARD_DOWN("法人身份证反面", "2"),
    BUSINESS_LICENSE("营业执照副本正面", "3"),
    ORG_CARD("组织机构代码证正面", "4"),
    AFFAIRS_CARD("税务登记证正面", "5"),
    OPEN_CARD("开户许可证正面", "6"),
    TRAVING_CARD("旅行社业务经营许可证正面", "7"),
    TOUCH_PROVE("联系人变更证明", "8"),
    PRINCIPAL_CARD_UP("负责人身份证正面", "9"),
    PRINCIPAL_CARD_DOWN("负责人身份证反面", "10"),
    COOPERATION1("合作合同1", "11"),
    COOPERATION2("合作合同2", "12"),
    COOPERATION3("合作合同3", "13"),
    COOPERATION4("合作合同4", "14"),
    COOPERATION5("合作合同5", "15"),
    TRAVEL_AGENCY_AUTHORIZATION("旅行社授权书", "16"),
    TRAVEL_AGENCY_INSURANCE("旅行社责任险证明正面", "17")
    
    
    ;
    
    private String name;
    private String id;

    PictureUrl(String name, String id) {
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
