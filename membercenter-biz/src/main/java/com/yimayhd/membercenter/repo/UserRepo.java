package com.yimayhd.membercenter.repo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.result.BaseResult;
import com.yimayhd.user.client.service.UserService;

/**
 * Created by Administrator on 2015/11/26.
 */
public class UserRepo {

    private static final Logger logger = LoggerFactory.getLogger(UserRepo.class);
    
    private static final String MOBILE = "+86";

    @Autowired
    private UserService userService;

    public UserDO getUserDOById(long userId) {
        try {
            return userService.getUserDOById(userId);
        } catch (Exception e) {
            logger.error("userId: {} query userDO error, mes is {}.", userId, e);
        }
        return null;
    }

    public List<UserDO> getUserList(List<Long> userIds) {
        return userService.getUserInfoList(userIds);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新用户基本信息〉
     *
     * @param userDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> updateUserDO(UserDO userDO) {
        MemResult<Boolean> baseResult = new MemResult<Boolean>();
        try {
            BaseResult<Boolean> result = userService.updateUserDO(userDO);
            if (result.isSuccess()) {
                baseResult.setValue(Boolean.TRUE);
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.info("updateUserDO par:{} return error:{}", JSONObject.toJSONString(userDO),
                    JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("updateUserDO par:{} return error:{}", JSONObject.toJSONString(userDO), e);
        }
        return baseResult;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈根据userId查询手机号〉
     *
     * @param userId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<String> queryUserMobile(long userId) {
        MemResult<String> baseResult = new MemResult<String>();
        try {
            BaseResult<String> result = userService.findMobileByUserId(userId);
            if (result.isSuccess() && StringUtils.isNoneBlank(result.getValue())) {
                String mobileNo = result.getValue();
                if(mobileNo.startsWith(MOBILE)){
                    mobileNo = mobileNo.replace(MOBILE, "");
                }
                baseResult.setValue(mobileNo);
                return baseResult;
            }
            baseResult.setErrorCode(result.getErrorCode());
            baseResult.setErrorMsg(result.getResultMsg());
            baseResult.setSuccess(false);
            logger.info("queryUserMobile par:{} return error:{}", userId, JSONObject.toJSONString(result));
        } catch (Exception e) {
            baseResult.setReturnCode(MemberReturnCode.DUBBO_ERROR);
            logger.error("queryUserMobile par:{} return error:{}", userId, e);
        }
        return baseResult;
    }
}
