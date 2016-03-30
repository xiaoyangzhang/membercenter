/*
 * FileName: Certificates.java
 * Author:   liubb
 * Date:     2016年3月15日 下午3:53:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.entity;

import java.io.Serializable;

import net.pocrd.annotation.Description;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Description("技能")
public class Certificates implements Serializable {

    /**
     */
    private static final long serialVersionUID = -9043894850824266768L;
    
    @Description("技能ID")
    public int id;
    
    @Description("技能名称")
    public String name;

}
