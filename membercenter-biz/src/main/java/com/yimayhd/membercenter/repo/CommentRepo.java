/*
 * FileName: CommentRepo.java
 * Author:   liubb
 * Date:     2016年3月23日 上午11:06:32
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.commentcenter.client.domain.ComentDO;
import com.yimayhd.commentcenter.client.dto.ComentDEditTO;
import com.yimayhd.commentcenter.client.dto.ComentDTO;
import com.yimayhd.commentcenter.client.dto.ComentQueryDTO;
import com.yimayhd.commentcenter.client.result.BaseResult;
import com.yimayhd.commentcenter.client.result.PicTextResult;
import com.yimayhd.commentcenter.client.service.ComPictureTextService;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.util.ParmCheckUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈commentCenter REPO〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommentRepo {

    private static final Logger logger = LoggerFactory.getLogger(CommentRepo.class);

    @Autowired
    ComPictureTextService comPictureTextService;

    /**
     * 
     * 功能描述: <br>
     * 〈保存图文详情〉
     *
     * @param comentDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> savePictureText(ComentDTO comentDTO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            BaseResult<ComentDO> result = comPictureTextService.savePictureText(comentDTO);
            if (result.isSuccess()) {
                baseResult.setValue(Boolean.TRUE);
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.debug("savePictureText par:{} return error:{}", JSONObject.toJSONString(comentDTO),
                    JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("savePictureText par:{} return error:{}", JSONObject.toJSONString(comentDTO),
                    e);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈获取图文详情〉
     *
     * @param comentQueryDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<PicTextResult> queryPictureText(ComentQueryDTO comentQueryDTO){
        MemResult<PicTextResult> baseResult = new MemResult<PicTextResult>();
        try{
            BaseResult<PicTextResult> result = comPictureTextService.getPictureText(comentQueryDTO);
            if (result.isSuccess() && null != result.getValue() && !ParmCheckUtil.checkListNull(result.getValue().getList())) {
                baseResult.setValue(result.getValue());
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.debug("getPictureText par:{} return error:{}", JSONObject.toJSONString(comentQueryDTO),
                    JSONObject.toJSONString(result));
        }catch(Exception e){
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("getPictureText par:{} return error:{}", JSONObject.toJSONString(comentQueryDTO),
                    e);
        }
        return baseResult;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈更新图文详情〉
     *
     * @param comentDEditTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> updatePictureText(ComentDEditTO comentDEditTO){
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            BaseResult<ComentDO> result = comPictureTextService.updatePictureText(comentDEditTO);
            if (result.isSuccess()) {
                baseResult.setValue(Boolean.TRUE);
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.debug("updatePictureText par:{} return error:{}", JSONObject.toJSONString(comentDEditTO),
                    JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("updatePictureText par:{} return error:{}", JSONObject.toJSONString(comentDEditTO),
                    e);
        }
        return baseResult;
    }
}
