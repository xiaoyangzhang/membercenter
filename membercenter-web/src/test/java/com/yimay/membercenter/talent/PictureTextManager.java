/*
 * FileName: PictureTextManager.java
 * Author:   liubb
 * Date:     2016年3月25日 下午3:30:44
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimay.membercenter.talent;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.commentcenter.client.result.PicTextResult;
import com.yimayhd.membercenter.client.domain.PictureTextDO;
import com.yimayhd.membercenter.client.dto.PictureTextDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.manager.talent.TalentBackInfoManager;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PictureTextManager extends BaseTest{

    private static final int domainId = 1200;
    
    @Autowired
    TalentBackInfoManager talentBackInfoManager;
    
    @Test
    public void savePictureText(){
        PictureTextDTO pictureTextDTO =  new PictureTextDTO();
        List<PictureTextDO> picTextList = new ArrayList<PictureTextDO>();
        PictureTextDO pictureTextDO = new PictureTextDO();
        pictureTextDO.setType(1);
        pictureTextDO.setValue("这里的山路十八弯");
        picTextList.add(pictureTextDO);
        pictureTextDTO.setPicTexts(picTextList);
        long outId = 10;
        MemResult<Boolean> result = talentBackInfoManager.savePictureText(domainId, outId, pictureTextDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
    
    
    @Test
    public void queryPictureText(){
        long outId = 10;
       MemResult<PicTextResult> result = talentBackInfoManager.queryPictureText(domainId, outId);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
    
    @Test
    public void updatePictureText(){
        PictureTextDTO pictureTextDTO =  new PictureTextDTO();
        List<PictureTextDO> picTextList = new ArrayList<PictureTextDO>();
        PictureTextDO pictureTextDO = new PictureTextDO();
        pictureTextDO.setType(1);
        pictureTextDO.setValue("这里的山路十八弯吗？");
        picTextList.add(pictureTextDO);
        pictureTextDTO.setPicTexts(picTextList);
        pictureTextDTO.setId(6);
        MemResult<Boolean> result = talentBackInfoManager.updatePictureText(10,1200,pictureTextDTO);
        System.out.println("----->");
        System.out.println("*****  " + JSONObject.toJSONString(result));
        System.out.println("----->");
    }
}
