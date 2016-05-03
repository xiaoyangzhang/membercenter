/*
 * FileName: Certificates.java
 * Author:   liubb
 * Date:     2016年3月15日 下午3:53:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.client.domain;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈技能属性〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CertificatesDO implements Serializable {

    /**
     */
    private static final long serialVersionUID = -3513320751393566144L;
    //技能ID
    private int id;
    //技能类型
    private int type;
    //技能名称
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
