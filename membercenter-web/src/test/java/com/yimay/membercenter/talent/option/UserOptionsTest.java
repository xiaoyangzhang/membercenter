/*
 * FileName: UserOptionsTest.java
 * Author:   liubb
 * Date:     2016年4月26日 下午3:03:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent.option;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.user.client.enums.UserOptions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum UserOptionsTest {

    MEMBER_VIP(1,"vip会员"),
    TRAVEL_KA(2,"大咖"),
    CLUB_MASTER_USER(3,"部长"),
    USER_TALENT(4,"达人"),
    COMMERCIAL_TENANT(5,"商户-大V"),
    CERTIFICATED(6,"商户-小V");
    
    
    private long option;//第option位，比如3，就是第三位，100 = 4，
    private String desc;

    UserOptionsTest(long option, String desc) {
        this.option = option;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public long getOption() {
        return option;
    }


    public static UserOptionsTest getByName(String name) {
        if (name == null) {
            return null;
        }
        for (UserOptionsTest useroption : values()) {
            if (useroption.name().equals(name)) {
                return useroption;
            }
        }
        return null;
    }

    /**
     * 判断当前options包含哪些枚举
     * @param options
     * @return
     */
    public static List<UserOptionsTest> getContainedOptions(long options){
        List<UserOptionsTest> optionList = new ArrayList<UserOptionsTest>();

        for(UserOptionsTest userOptions : UserOptionsTest.values()){
            long option = userOptions.getOption();
            if((option & options) == option){
                optionList.add(userOptions);
            }
        }

        return optionList;
    }

    public boolean has(long option) {
        long a = getLong();
        return (a & option) == a;
    }

    public long getLong() {
        return 1 << (option - 1);
    }
    
    public static void main(String [] args){
        System.out.println(UserOptionsTest.USER_TALENT.has(40));
        System.out.println(UserOptionsTest.USER_TALENT.getLong());
        System.out.println(UserOptionsTest.CERTIFICATED.getLong());
    }
}
