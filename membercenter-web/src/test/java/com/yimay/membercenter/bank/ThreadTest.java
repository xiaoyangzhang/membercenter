/*
 * FileName: ThreadTest.java
 * Author:   liubb
 * Date:     2016年4月3日 上午11:42:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.bank;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ThreadTest extends Thread{

    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId());
    }
}
