/*
 * FileName: TalentQuery.java
 * Author:   liubb
 * Date:     2016年3月19日 上午11:13:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity.talent;

import java.io.Serializable;

import com.yimayhd.membercenter.entity.PageInfo;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈达人列表查询〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("达人列表查询")
public class TalentQuery implements Serializable {

    /**
     */
    private static final long serialVersionUID = 3565630820397407075L;
    
    @Description("0-全部  1-全程伴游 2-包车服务 4-咨询规划")
    public String tagId;
    
    @Description("0-升序 1-降序  默认降序")
    public String sort;
    
    @Description("达人搜索查询    搜索字段")
    public String searchWord;
    
    @Description("分页查询")
    public PageInfo pageInfo;

}
