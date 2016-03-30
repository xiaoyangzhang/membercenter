/*
 * FileName: TalentBackInfoManager.java
 * Author:   liubb
 * Date:     2016年3月23日 下午4:06:53
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.manager.talent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.commentcenter.client.domain.PicTextDO;
import com.yimayhd.commentcenter.client.dto.ComentDEditTO;
import com.yimayhd.commentcenter.client.dto.ComentDTO;
import com.yimayhd.commentcenter.client.dto.ComentQueryDTO;
import com.yimayhd.commentcenter.client.enums.PictureText;
import com.yimayhd.commentcenter.client.result.PicTextResult;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.PictureTextDO;
import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.membercenter.client.dto.PictureTextDTO;
import com.yimayhd.membercenter.client.dto.TalentInfoDTO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.converter.TalentConverter;
import com.yimayhd.membercenter.converter.UserConverter;
import com.yimayhd.membercenter.repo.CommentRepo;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.dto.MerchantDTO;
import com.yimayhd.user.client.dto.MerchantUserDTO;

/**
 * 〈一句话功能简述〉<br>
 * 〈达人基本信息后台manager〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TalentBackInfoManager {

    private static final Logger logger = LoggerFactory.getLogger(TalentBackInfoManager.class);

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MerchantRepo merchantRepo;

    @Autowired
    TalentExamineManager examineManager;

    /**
     * 
     * 功能描述: <br>
     * 〈保存达人基本信息 form后台〉
     *
     * @param talentInfoDO
     * @param picTextDO
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> updateTalentBackInfo(TalentInfoDO talentInfoDO, PictureTextDTO pictureTextDTO,
            int domainId) {
        logger.debug(" user:{}, picText:{}, domainId:{} saveTalentBackInfo begin --->",
                JSONObject.toJSONString(talentInfoDO), JSONObject.toJSONString(pictureTextDTO), domainId);
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        // userDO转换
        UserDO userDO = UserConverter.talentInfoConverterToUserDO(talentInfoDO);
        // 保存user信息
        MemResult<Boolean> userResult = userRepo.updateUserDO(userDO);
        // 判断是否成功
        if (userResult.isSuccess()) {
            logger.info("saveTalentBackInfo userId:{} updateUserDO success", talentInfoDO.getId());
            // merchantDO转换
            MerchantDO merchantDO = MerchantConverter.talentInfoConverterToMerchant(talentInfoDO, domainId);
            // 判断是否已经存在
            MemResult<MerchantUserDTO> merchantResult = merchantRepo.queryMerchantBySellerId(talentInfoDO.getId(),
                    domainId);
            // 如果存在 则update 反之则insert
            if (merchantResult.isSuccess()) {
                logger.info("saveTalentBackInfo userId:{} queryMerchantDO success", talentInfoDO.getId());
                // 店铺更新ID
                merchantDO.setId(merchantResult.getValue().getMerchantDO().getId());
                MerchantDTO merchantDTO = MerchantConverter.merchantDOToDTO(merchantDO);
                // 更新店铺信息
                MemResult<Boolean> merchantUpdateResult = merchantRepo.updateMerchantInfo(merchantDTO);
                if (merchantUpdateResult.isSuccess()) {
                    logger.info("saveTalentBackInfo userId:{} updateMerchantInfo success", talentInfoDO.getId());
                    MemResult<PicTextResult> pictureText = queryPictureText(domainId, talentInfoDO.getId());
                    if (pictureText.isSuccess()) {
                        pictureTextDTO.setId(pictureText.getValue().getId());
                        // 更新图文信息
                        return updatePictureText(pictureTextDTO);
                    } else {
                        // 保存图文信息
                        return savePictureText(domainId, talentInfoDO.getId(), pictureTextDTO);
                    }
                } else {
                    baseResult.setReturnCode(merchantUpdateResult.getReturnCode());
                    logger.info("saveTalentBackInfo userId:{} updateMerchantInfo error", talentInfoDO.getId());
                    return baseResult;
                }
            } else if (merchantResult.getErrorCode() == MemberReturnCode.MEMBER_NOT_FOUND_C) {
                // 未查询到店铺信息 新入住
                logger.info("saveTalentBackInfo userId:{} queryMerchantDO return null", talentInfoDO.getId());
                merchantDO.setSellerId(talentInfoDO.getId());
                // insert 店铺信息
                MemResult<MerchantDO> merchantSaveResult = merchantRepo.saveMerchant(merchantDO);
                if (merchantSaveResult.isSuccess()) {
                    logger.info("saveTalentBackInfo userId:{} saveMerchant success", talentInfoDO.getId());
                    // 保存图文信息
                    return savePictureText(domainId, talentInfoDO.getId(), pictureTextDTO);
                } else {
                    baseResult.setReturnCode(merchantSaveResult.getReturnCode());
                    logger.info("saveTalentBackInfo userId:{} saveMerchant error", talentInfoDO.getId());
                    return baseResult;
                }
            } else {
                baseResult.setReturnCode(merchantResult.getReturnCode());
                logger.info("saveTalentBackInfo userId:{} getMerchantBySellerId error", talentInfoDO.getId());
                return baseResult;
            }
        } else {
            baseResult.setReturnCode(userResult.getReturnCode());
            logger.info("saveTalentBackInfo userId:{} updateUserDO error", talentInfoDO.getId());
            return baseResult;
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新图文信息〉
     *
     * @param pictureTextDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> updatePictureText(PictureTextDTO pictureTextDTO) {
        ComentDEditTO comentDEditTO = new ComentDEditTO();
        comentDEditTO.setId(pictureTextDTO.getId());
        comentDEditTO.setPicTextDOList(picTextConverter(pictureTextDTO.getPicTexts()));
        MemResult<Boolean> picTextResult = commentRepo.updatePictureText(comentDEditTO);
        logger.info("updatePictureText picTextId:{} return:{}", pictureTextDTO.getId(),
                JSONObject.toJSONString(picTextResult));
        return picTextResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈保存图文信息〉
     *
     * @param domainId
     * @param outId
     * @param pictureTextDTO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> savePictureText(int domainId, long outId, PictureTextDTO pictureTextDTO) {
        ComentDTO comentDTO = new ComentDTO();
        comentDTO.setDomain(domainId);
        comentDTO.setOutId(outId);
        comentDTO.setOutType(PictureText.EXPERT.name());
        comentDTO.setPicTextDOList(picTextConverter(pictureTextDTO.getPicTexts()));
        // insert 图文详情
        MemResult<Boolean> picTextResult = commentRepo.savePictureText(comentDTO);
        logger.info("savePictureText domainId:{}, outId:{} return:{}", domainId, outId,
                JSONObject.toJSONString(picTextResult));
        return picTextResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈查询图文详情〉
     *
     * @param domainId
     * @param outId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<PicTextResult> queryPictureText(int domainId, long outId) {
        ComentQueryDTO comentQueryDTO = new ComentQueryDTO();
        comentQueryDTO.setDomain(domainId);
        comentQueryDTO.setOutId(outId);
        comentQueryDTO.setOutType(PictureText.EXPERT.name());
        // 查询图文详情
        MemResult<PicTextResult> picText = commentRepo.queryPictureText(comentQueryDTO);
        if (picText.isSuccess()) {
            logger.info("queryPictureText par:{} error, return:{} ", JSONObject.toJSONString(comentQueryDTO),
                    picText.getErrorMsg());
        }
        return picText;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈达人首页反写〉
     *
     * @param userId
     * @param domainId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<TalentInfoDTO> queryTalentBackInfo(long sellerId, int domainId) {
        MemResult<TalentInfoDTO> baseResult = new MemResult<TalentInfoDTO>();
        TalentInfoDTO talentInfoDTO = new TalentInfoDTO();
        talentInfoDTO.setDomainId(domainId);
        // 查询是否已经入住
        MemResult<MerchantUserDTO> merchantResult = merchantRepo.queryMerchantBySellerId(sellerId,
                domainId);
        if (merchantResult.isSuccess()) {
            logger.info("queryTalentBackInfo userId:{} queryMerchantBySellerId success ", sellerId);
            // 查询全部信息
            TalentInfoDO talentInfoDO = TalentConverter.merchantToTalent(merchantResult.getValue().getMerchantDO(),
                    merchantResult.getValue().getUserDO());
            // 基本信息
            talentInfoDTO.setTalentInfoDO(talentInfoDO);
            MemResult<PicTextResult> picText = queryPictureText(domainId, talentInfoDO.getId());
            if (picText.isSuccess()) {
                logger.info("queryTalentBackInfo userId:{} queryPictureText success ", sellerId);
                PictureTextDTO pictureTextDTO = new PictureTextDTO();
                pictureTextDTO.setId(picText.getValue().getId());
                pictureTextDTO.setPicTexts(pictureTextConverter(picText.getValue().getList()));
                // 关于我 达人详情
                talentInfoDTO.setPictureTextDTO(pictureTextDTO);
            }
        } else {
            // 只查询用户信息
            UserDO userDO = userRepo.getUserDOById(sellerId);
            if (null != userDO) {
                logger.info("queryTalentBackInfo userId:{} queryUserDO success ", sellerId);
                // 数据格式转换
                TalentInfoDO talentInfoDO = UserConverter.userDOConverterToTalentInfo(userDO);
                talentInfoDTO.setTalentInfoDO(talentInfoDO);
            } else {
                // 无该用户
                baseResult.setReturnCode(MemberReturnCode.USER_NOT_FOUND);
                logger.info("queryTalentBackInfo userId:{} queryUserDO return null ", sellerId);
                return baseResult;
            }
        }
        // // 判断负责人手机号是否为空
        // if (StringUtils.isBlank(talentInfoDTO.getTalentInfoDO().getTelNum())) {
        // ExamineDO examineDO = ExamineConverter.examineQueryToDO(infoQueryDTO);
        // // 查询审核信息中手机号
        // MemResult<ExamineDO> examineResult = examineManager.queryMerchantExaminInfoById(examineDO);
        // if (examineResult.isSuccess()) {
        // talentInfoDTO.getTalentInfoDO().setTelNum(String.valueOf(examineResult.getValue().getTelNum()));
        // }
        // logger.info("queryTalentBackInfo userId:{} queryMerchantExaminInfoById return：{} ",
        // examineResult.getValue().getTelNum());
        // }
        baseResult.setValue(talentInfoDTO);
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈图文详情数据类型转换〉
     *
     * @param domainId
     * @param outId
     * @param picTextDOs
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static List<PicTextDO> picTextConverter(List<PictureTextDO> pictureTextDOs) {
        List<PicTextDO> picTextList = new ArrayList<PicTextDO>();
        for (PictureTextDO picText : pictureTextDOs) {
            PicTextDO picTextDO = new PicTextDO();
            picTextDO.setType(picText.getType());
            picTextDO.setValue(picText.getValue());
            picTextList.add(picTextDO);
        }
        return picTextList;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈图文详情数据类型转换〉
     *
     * @param picTextDOs
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static List<PictureTextDO> pictureTextConverter(List<PicTextDO> picTextDOs) {
        List<PictureTextDO> pictureTextList = new ArrayList<PictureTextDO>();
        for (PicTextDO picText : picTextDOs) {
            PictureTextDO pictureTextDO = new PictureTextDO();
            pictureTextDO.setType(picText.getType());
            pictureTextDO.setValue(picText.getValue());
            pictureTextList.add(pictureTextDO);
        }
        return pictureTextList;
    }
}
