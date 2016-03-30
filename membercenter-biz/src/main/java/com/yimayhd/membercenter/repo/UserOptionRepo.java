/*
 * FileName: UserOptionRepo.java
 * Author:   liubb
 * Date:     2016年3月30日 下午5:47:58
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.user.client.enums.UserOptions;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.member.UserOptionService;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户类型REPO〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UserOptionRepo {

    private static final Logger logger = LoggerFactory.getLogger(UserOptionRepo.class);

    @Autowired
    UserOptionService userOptionService;

    /**
     * 
     * 功能描述: <br>
     * 〈新增用户option〉
     *
     * @param userId
     * @param userOptionsList
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> addUserOption(long userId, List<UserOptions> userOptionsList) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            BaseResult<Boolean> optionResult = userOptionService.addUserOption(userId, userOptionsList);
            if (optionResult.isSuccess()) {
                return baseResult;
            }
            baseResult.setErrorCode(optionResult.getErrorCode());
            baseResult.setErrorMsg(optionResult.getResultMsg());
            baseResult.setSuccess(false);
            logger.debug("getMerchantList par:{} return error:{}", userId, JSONObject.toJSONString(optionResult));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("savePictureText userId:{} return error:{}", userId, e);
        }
        return baseResult;
    }
}
