/*
 * FileName: PicTextDO.java
 * Author:   liubb
 * Date:     2016年3月23日 下午4:00:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PictureTextDO implements Serializable {

    /**
     */
    private static final long serialVersionUID = 5386851071439172121L;
    
    private int type;//类型
    
    private String value;//属性值

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
