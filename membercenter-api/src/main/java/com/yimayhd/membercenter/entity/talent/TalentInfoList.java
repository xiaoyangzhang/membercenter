/*
 * FileName: TalentInfoList.java
 * Author:   liubb
 * Date:     2016年3月15日 下午4:06:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.talent;

import java.io.Serializable;
import java.util.List;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈达人列表〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("达人列表")
public class TalentInfoList implements Serializable{

    /**
     */
    private static final long serialVersionUID = 6181994177174373184L;
    
    @Description("当前页码")
    public int pageNo;

    @Description("是否有下一页")
    public boolean hasNext;

    @Description("达人信息列表")
    public List<TalentUserInfo> talentList;

}
