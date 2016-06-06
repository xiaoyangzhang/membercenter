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
public enum PictureUrl {

    LEGRAL_CARD_UP("法人身份证正面", "1", 1), 
    LEGRAL_CARD_DOWN("法人身份证反面", "2", 1), 
    BUSINESS_LICENSE("营业执照副本正面", "3",1), 
    ORG_CARD("组织机构代码证正面", "4", 1), 
    AFFAIRS_CARD("税务登记证正面", "5", 1), 
    OPEN_CARD("开户许可证正面", "6", 1), 
    TRAVING_CARD("旅行社业务经营许可证正面", "7", 1), 
    TOUCH_PROVE("联系人变更证明", "8", 1), 
    PRINCIPAL_CARD_UP("负责人身份证正面", "9", 2), 
    PRINCIPAL_CARD_DOWN("负责人身份证反面", "10", 2), 
    COOPERATION1("合作合同1", "11", 2), 
    COOPERATION2("合作合同2", "12", 2), 
    COOPERATION3("合作合同3", "13", 2), 
    COOPERATION4("合作合同4", "14", 2), 
    COOPERATION5("合作合同5", "15", 2), 
    TRAVEL_AGENCY_AUTHORIZATION("旅行社授权书", "16", 1), 
    TRAVEL_AGENCY_INSURANCE("旅行社责任险证明正面", "17", 1),
    INTERMEDIARY_LICENSE("因私出入境中介机构经营许可证正面","18",1),
    SEA_TRANSPORTATION_LICENSE("水路运输许可证正面","19",1),
    TRAVEL_AGENCY_BRANCH_AGREEMENT("旅行社分社补充协议","20",1),
    SCENIC_TICKET_AUTHORIZATION("景点门票授权书或合作协议","21",1),
    HOTEL_GOODS_AUTHORIZATION("酒店商品授权书","22",1),
    SPECIAL_SALE_LICENSE("特种经营许可证副本复印件","23",1),
    SPECIAL_SALE_AUTHORIZATION("旅游局特许经营授权书","24",1),
    WILDLIFE_SALE("野生动物经营利用许可证","25",1),
    WATER_WILDLIFE_SALE("水生野生动物经营利用许可证","26",1),
    AMUSEMENTPARK_REPORT("游乐特种设备（备案）登记证明和游乐设备最新的定期检测报告","27",1),
    SCENIC_PRICE_REGISTER("景区门票价格在物价局的备案登记证明","28",1),
    SCENIE_TICKET_UP_SCANNING("景区门票扫描件正面","29",1),
    SCENIE_TICKET_DOWN_SCANNING("景区门票扫描件反面","30",1),
    SCENIC_GOODS_AUTHORIZATION("景区商品授权书","31",1),
    SCENIC_QUALITY_LEVEL("景区质量等级证书","32",1),
    RELATION_BETWEEN_HOTEL_AND_GROUP("酒店与集团的所属关系说明","33",1),
    CARD_IN_HAND("手持身份证","34",1);
     
    private static Map<Integer, List<String>> hashMap = new HashMap<Integer, List<String>>();

    static {
        List<String> list = null;
        for (PictureUrl pictureUrl : PictureUrl.values()) {
            if (hashMap.containsKey(pictureUrl.getNumber())) {
                list = hashMap.get(pictureUrl.getNumber());
            } else {
                list = new ArrayList<String>();
            }
            list.add(pictureUrl.getId());
            hashMap.put(pictureUrl.getNumber(), list);
        }
    }

    private String name;
    private String id;
    private int number;

    PictureUrl(String name, String id, int number) {
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

    public static PictureUrl getPictureUrlById(String id) {
        for (PictureUrl pictureUrl : PictureUrl.values()) {
            if (pictureUrl.getId().equals(id)) {
                return pictureUrl;
            }
        }
        return null;
    }

    public static int queryNumber(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            PictureUrl pictureUrl = getPictureUrlById(entry.getKey());
            if (null != pictureUrl) {
                return pictureUrl.getNumber();
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
