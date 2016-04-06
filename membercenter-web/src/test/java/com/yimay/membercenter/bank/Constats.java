/*
 * FileName: Constats.java
 * Author:   liubb
 * Date:     2016年4月1日 下午5:44:45
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Constats {

    public static Map<String,String> provinceMap = new HashMap<String, String>();
    public static Map<String,String> bankMap = new HashMap<String, String>();

    static{
        provinceMap.put("1","北京市");
        provinceMap.put("2","天津市");
        provinceMap.put("3","河北省");
        provinceMap.put("4","山西省");
        provinceMap.put("5","内蒙古自治区");
        provinceMap.put("6","辽宁省");
        provinceMap.put("7","吉林省");
        provinceMap.put("8","黑龙江省");
        provinceMap.put("9","上海市");
        provinceMap.put("10","江苏省");
        provinceMap.put("11","浙江省");
        provinceMap.put("12","安徽省");
        provinceMap.put("13","福建省");
        provinceMap.put("14","江西省");
        provinceMap.put("15","山东省");
        provinceMap.put("16","河南省");
        provinceMap.put("17","湖北省");
        provinceMap.put("18","湖南省");
        provinceMap.put("19","广东省");
        provinceMap.put("20","广西壮族自治区");
        provinceMap.put("21","海南省");
        provinceMap.put("22","四川省");
        provinceMap.put("23","重庆市");
        provinceMap.put("24","贵州省");
        provinceMap.put("25","云南省");
        provinceMap.put("26","西藏自治区");
        provinceMap.put("27","陕西省");
        provinceMap.put("28","甘肃省");
        provinceMap.put("29","青海省");
        provinceMap.put("30","宁夏回族自治区");
        provinceMap.put("31","新疆维吾尔族自治区");
        provinceMap.put("32","台湾");
        provinceMap.put("33","香港");
        provinceMap.put("34","澳门");
        bankMap.put("1","中国工商银行");
        bankMap.put("2","中国农业银行");
        bankMap.put("3","中国银行");
        bankMap.put("4","中国建设银行");
        bankMap.put("5","国家开发银行");
        bankMap.put("6","中国进出口银行");
        bankMap.put("7","中国农业发展银行");
        bankMap.put("8","交通银行");
        bankMap.put("9","中国邮政储蓄银行");
        bankMap.put("10","中信银行");
        bankMap.put("11","中国光大银行");
        bankMap.put("12","华夏银行");
        bankMap.put("13","中国民生银行");
        bankMap.put("14","广东发展银行");
        bankMap.put("69","平安银行");
        bankMap.put("16","招商银行");
        bankMap.put("17","兴业银行");
        bankMap.put("18","上海浦东发展银行");
        bankMap.put("19","城市商业银行");
        bankMap.put("20","农村商业银行");
        bankMap.put("21","恒丰银行");
        bankMap.put("22","农村合作银行");
        bankMap.put("23","渤海银行");
        bankMap.put("24","徽商银行");
        bankMap.put("25","城市信用社");
        bankMap.put("26","农村信用联社");
//        bankMap.put("27","香港上海汇丰银行");
//        bankMap.put("28","东亚银行");
//        bankMap.put("29","南洋商业银行");
//        bankMap.put("30","恒生银行");
//        bankMap.put("31","中国银行（香港）");
//        bankMap.put("32","(香港地区)银行");
//        bankMap.put("33","集友银行");
//        bankMap.put("34","星展银行（香港）");
//        bankMap.put("35","永亨银行");
//        bankMap.put("36","美国花旗银行");
//        bankMap.put("37","美国银行");
//        bankMap.put("38","美国摩根大通银行");
//        bankMap.put("39","日本三菱东京日联银行");
//        bankMap.put("40","日本日联银行");
//        bankMap.put("41","日本三井住友银行");
//        bankMap.put("42","日本瑞穗实业银行");
//        bankMap.put("43","日本山口银行");
//        bankMap.put("44","韩国外换银行");
//        bankMap.put("45","韩国新韩银行");
//        bankMap.put("46","韩国友利银行");
//        bankMap.put("47","韩国产业银行");
//        bankMap.put("48","韩国中小企业银行");
//        bankMap.put("49","新加坡星展银行");
//        bankMap.put("50","奥地利中央合作银行");
//        bankMap.put("51","比利时联合银行");
//        bankMap.put("52","荷兰银行");
//        bankMap.put("53","荷兰商业银行");
//        bankMap.put("54","渣打银行");
//        bankMap.put("55","法国兴业银行");
//        bankMap.put("56","法国巴黎银行");
//        bankMap.put("57","法国东方汇理银行");
//        bankMap.put("58","德国德累斯登银行");
//        bankMap.put("59","德意志银行");
//        bankMap.put("60","德国商业银行");
//        bankMap.put("61","德国西德银行");
//        bankMap.put("62","德国巴伐利亚州银行");
//        bankMap.put("63","瑞士信贷银行");
//        bankMap.put("64","加拿大蒙特利尔银行");
//        bankMap.put("65","澳大利亚和新西兰银行集团");
//        bankMap.put("66","德富泰银行");
//        bankMap.put("67","厦门国际银行");
//        bankMap.put("68","法国巴黎银行（中国）");
//        bankMap.put("15","深圳发展银行");
//        bankMap.put("70","青岛国际银行");
//        bankMap.put("71","华一银行");
    }
}
