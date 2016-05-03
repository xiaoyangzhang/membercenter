/*
 * FileName: ThreadRun.java
 * Author:   liubb
 * Date:     2016年4月3日 上午10:39:21
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yimayhd.membercenter.client.domain.BankDO;
import com.yimayhd.membercenter.mapper.BankDOMapper;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ThreadRun {

    private String provinceId;

    private String provinceName;

    private BankDOMapper bankDOMapper;

    public ThreadRun(String provinceId, String provinceName, BankDOMapper bankDOMapper) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.bankDOMapper = bankDOMapper;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void insert() {
        int totalPage = 0;
        String xml = "";
        Pattern pattern = null;
        Matcher matcher = null;
        List<BankDO> bankList = null;
        List<String> mac = null;
        List<City> citys = CrawlerTest.queryCityByProvince(provinceId);
        // 遍历银行
        for (Map.Entry<String, String> bank : Constats.bankMap.entrySet()) {
            // for (Map.Entry<String, String> province : Constats.provinceMap.entrySet()) {
            for (City city : citys) {
                totalPage = CrawlerTest.queryTotalPage(bank.getKey(), provinceId, city.getId());
                for (int i = 0; i < totalPage; i++) {
                    try {
                        bankList = new ArrayList<BankDO>();
                        xml = CrawlerTest.queryBank(bank.getKey(), provinceId, city.getId(), i + 1);
                        pattern = Pattern.compile("<tr>(.*?)</tr>");
                        matcher = pattern.matcher(xml.trim());
                        List<String> list = new ArrayList<String>();
                        while (matcher.find()) {
                            list.add(matcher.group());
                        }
                        // System.out.println(list.size());
                        pattern = Pattern.compile("<td( .*?)?>(.*?)</td>");
                        for (String string : list) {
                            BankDO bankInfo = new BankDO();
                            bankInfo.setBankName(bank.getValue());
                            bankInfo.setBankId(Integer.valueOf(bank.getKey()));
                            bankInfo.setCityName(city.getName());
                            bankInfo.setProvinceName(provinceName);
                            matcher = pattern.matcher(string);
                            mac = new ArrayList<String>();
                            while (matcher.find()) {
                                mac.add(matcher.group(2));
                            }
                            // bankInfo.setId(mac.get(0));
                            bankInfo.setBranchCode(mac.get(1));
                            bankInfo.setBranchName(mac.get(2));
                            bankInfo.setBranchTel(mac.get(3));
                            bankInfo.setBranchAddress(mac.get(4));
                            bankInfo.setGmtCreated(new Date());
                            bankInfo.setGmtModified(new Date());
                            // System.out.println(JSONObject.toJSONString(bankInfo));
                            bankList.add(bankInfo);
                        }
                        bankDOMapper.insertBatch(bankList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(provinceId);
                        System.out.println(city.getName());
                        System.out.println(i);
                    }
                }
                // }
            }
        }
    }

}
