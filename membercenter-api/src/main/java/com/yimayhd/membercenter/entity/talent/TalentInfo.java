/*
 * FileName: TalentInfo.java
 * Author:   liubb
 * Date:     2016年3月15日 下午3:32:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.talent;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.membercenter.entity.Certificates;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈达人首页信息〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("达人首页信息")
public class TalentInfo implements Serializable {

    /**
     */
    private static final long serialVersionUID = 742928421239682532L;
    
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
    
    @Description("达人手机号")
    public String telNum;
    
    @Description(" true-大V认证  false-非大V认证")
    public boolean type;
    
    @Description("达人首页轮播图")
    public List<String> pictures;
    
    @Description("达人技能类型")
    public int certificateType;
    
    @Description("达人认证")
    public List<Certificates> certificates;
    
    @Description("关注数量")
    public long followCount;
    
    @Description("粉丝数量")
    public long fansCount;
    
    @Description("ugc数量")
    public long ugcCount;
    
    @Description("NONE-未关注/FOLLOW-单向关注/BIFOLLOW-双向关注")
    public String attentionType;
    
}
