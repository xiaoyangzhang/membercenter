/*
 * FileName: MapUnionUtil.java
 * Author:   liubb
 * Date:     2016年4月13日 下午3:43:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.util;

import java.util.Date;
import java.util.Map;

import com.yimayhd.fhtd.utils.PicFeatureUtil;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.enums.ExamineDetail;
import com.yimayhd.membercenter.enums.PictureUrl;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MapUnionUtil {

    /**
     *
     * 功能描述: <br>
     * 〈对象取并集〉 分页提交新增 还好代码灵活
     * 
     * @param examineMater
     * @param examineSlave
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ExamineDO unionAll(ExamineDO examineMater, ExamineDO examineSlave) {
        examineMater.setId(examineSlave.getId());
        // 图片
        Map<String, String> pictureMasterMap = PicFeatureUtil.fromString(examineMater.getPicturesUrl());
        // 图片
        Map<String, String> pictureSlaveMap = PicFeatureUtil.fromString(examineSlave.getPicturesUrl());
        // 新覆盖旧
        pictureSlaveMap.putAll(PictureUrl.queryFullMap(pictureMasterMap));
        // pictureSlaveMap.putAll(pictureMasterMap);
        examineMater.setPicturesUrl(PicFeatureUtil.toString(pictureSlaveMap));

        // 信息明细
        Map<String, String> featureMasterMap = PicFeatureUtil.fromString(examineMater.getFeature());
        // 信息明细
        Map<String, String> featureSlaveMap = PicFeatureUtil.fromString(examineSlave.getFeature());
        // featureSlaveMap.putAll(featureMasterMap);
        featureSlaveMap.putAll(ExamineDetail.queryFullMap(featureMasterMap));
        examineMater.setFeature(PicFeatureUtil.toString(featureSlaveMap));

        // 达人技能
        Map<String, String> certificateMasterMap = PicFeatureUtil.fromString(examineMater.getCertificate());
        // 达人技能
        Map<String, String> certificateSlaveMap = PicFeatureUtil.fromString(examineSlave.getCertificate());
        certificateSlaveMap.putAll(certificateMasterMap);
        examineMater.setCertificate(PicFeatureUtil.toString(certificateSlaveMap));

        examineMater.setGmtModified(new Date());
        return examineMater;
    }

}
