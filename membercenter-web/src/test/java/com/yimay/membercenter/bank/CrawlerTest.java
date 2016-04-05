/*
 * FileName: Test.java
 * Author:   liubb
 * Date:     2016年4月1日 下午3:39:03
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimay.membercenter.talent.BaseTest;
import com.yimayhd.membercenter.mapper.BankDOMapper;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CrawlerTest extends BaseTest{

    @Autowired
    BankDOMapper bankDOMapper;
    
    private static final String CITY_URL = "http://www.lianhanghao.com/area.php?act=ajax&id=";

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static List<City> queryCityByProvince(String provinceId) {
        String cityCode = sendGet(CITY_URL + provinceId);
        // System.out.println(cityCode);
        CityList list = JSON.parseObject(cityCode, CityList.class);
        return list.getCity();
    }

    public static int queryTotalPage(String bankId, String provinceId, String cityId) {
        String result = "";
        try{
            result = sendGet("http://www.lianhanghao.com/index.php?bank=" + bankId + "&key=&province=" + provinceId
                + "&city=" + cityId + "&page=1");
        System.out.println("bank=" + bankId + "&key=&province=" + provinceId + "&city=" + cityId + "&page=1");
        if (result.indexOf("共") < 0) {
            String xml = result.substring(result.indexOf("<tbody>") + "<tbody>".length(), result.indexOf("</tbody>"));
            if (StringUtils.isNotBlank(xml) && xml.contains("</tr>") && xml.contains("</td>")) {
                return 1;
            }
            return 0;
        }
        int totalCount = Integer.valueOf(result.substring(result.indexOf(">共") + 2, result.indexOf("记录")));
        return totalCount % 20 == 0 ? totalCount / 20 : totalCount / 20 + 1;
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println(result);
    }
        return 0;
    }

    public static String queryBank(String bankId, String provinceId, String cityId, int page) {
        String result = sendGet("http://www.lianhanghao.com/index.php?bank=" + bankId + "&key=&province=" + provinceId
                + "&city=" + cityId + "&page=" + page);
        String xml = result.substring(result.indexOf("<tbody>") + "<tbody>".length(), result.indexOf("</tbody>"));
        return xml;
    }
    
    @Test
    public void insertBank(){
        //伤感  test不支持多线程~~
        for (Map.Entry<String, String> province : Constats.provinceMap.entrySet()) {
            ThreadRun thread = new ThreadRun(province.getKey(), province.getValue(), bankDOMapper);
            thread.insert();
        }
    }
    
}
