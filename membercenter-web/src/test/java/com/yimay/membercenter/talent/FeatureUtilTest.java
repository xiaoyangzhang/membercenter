/*
 * FileName: PicFeatureUtilTest.java
 * Author:   liubb
 * Date:     2016年4月13日 下午2:55:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FeatureUtilTest {

    private static final String SP = ";";
    private static final String SSP = ":";

    private static final String R_SP = "#3A";
    private static final String R_SSP = "#3B";

    /**
     * 通过Map转换成String
     *
     * @param attrs
     * @return
     */
    public static final String toString(Map<String, String> attrs) {
        StringBuilder sb = new StringBuilder();
        if (null != attrs && !attrs.isEmpty()) {
            sb.append(SP);
            for (String key : attrs.keySet()) {
                String val = attrs.get(key);
                if (StringUtils.isNotEmpty(val)) {
                    sb.append(encode(key)).append(SSP).append(encode(val)).append(SP);
                }else{
                    sb.append(encode(key)).append(SSP).append(SP);
                }
            }
        }
        return sb.toString();
    }

    public static final String toString(String key, String val) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(val)) {
            sb.append(SP);
            sb.append(encode(key)).append(SSP).append(encode(val));
            sb.append(SP);
        }
        return sb.toString();
    }

    /**
     * 通过字符串解析成attributes
     *
     * @param str
     * @return
     */
    public static final Map<String, String> fromString(String str) {
        Map<String, String> attrs = new HashMap<String, String>();
        if (StringUtils.isNotBlank(str)) {
            String[] arr = str.split(SP);
            if (null != arr) {
                for (String kv : arr) {
                    if (StringUtils.isNotBlank(kv)) {
                        String[] ar = kv.split(SSP);
                        if (null != ar) {
                            String key = decode(ar[0]);
                            if (ar.length == 2) {
                                String val = decode(ar[1]);
                                if (StringUtils.isNotEmpty(val)) {
                                    attrs.put(key, val);
                                }
                            } else {
                                attrs.put(key, "");
                            }

                        }
                    }
                }
            }
        }
        return attrs;
    }

    private static String encode(String val) {
        return StringUtils.replace(StringUtils.replace(val, SP, R_SP), SSP, R_SSP);
    }

    private static String decode(String val) {
        return StringUtils.replace(StringUtils.replace(val, R_SP, SP), R_SSP, SSP);
    }

    public static void main(String[] args) {
        Map<String, String> attrs = new HashMap<String, String>();
        attrs.put("1", "");
        attrs.put("2", "test2");
        System.out.println(toString(attrs));
        System.out.println(fromString(toString(attrs)));
        
        Map<String, String> attrs1 = new HashMap<String, String>();
        attrs1.put("1", "test1");
        attrs1.put("2", "");
        attrs.putAll(attrs1);
        System.out.println(toString(attrs));
    }
}
