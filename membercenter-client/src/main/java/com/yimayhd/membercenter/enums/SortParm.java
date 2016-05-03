/*
 * FileName: SortParm.java
 * Author:   liubb
 * Date:     2016年3月19日 下午12:20:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.enums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum SortParm {

   SERVE_COUNT("服务次数",1);
   
   private String desc;
   private int status;

   SortParm(String desc, int status) {
       this.desc = desc;
       this.status = status;
   }

   public String getDesc() {
       return desc;
   }

   public int getStatus() {
       return status;
   }
}
