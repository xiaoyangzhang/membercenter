/*
 * FileName: TalentUserInfo.java
 * Author:   liubb
 * Date:     2016年3月15日 下午3:26:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.talent;

import java.io.Serializable;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈达人列表信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("达人列表信息")
public class TalentUserInfo implements Serializable {

    /**
     */
    private static final long serialVersionUID = 7167002601669489334L;
    
    @Description("用户ID")
    public long userId;
    
    @Description("用户头像")
    public String avatar; // 用户头像

    @Description("昵称")
    public String nickName; // 昵称

    @Description("性别 1-未确认 2-男  3-女")
    public String gender; // 性别  1-男 2-女
    
    @Description("服务描述")
    public String serveDesc;
    
    @Description("服务次数")
    public long serveCount;
    
    @Description("所在城市")
    public String city;
    
    @Description("城市code")
    public String cityCode;
    
    @Description(" true-大V认证  false-非大V认证")
    public boolean type;

}
