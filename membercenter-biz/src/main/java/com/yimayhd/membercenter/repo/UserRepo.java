package com.yimayhd.membercenter.repo;

import java.util.List;

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

    @Autowired
    private UserService userService;

    public UserDO getUserDOById(long userId) {
        try{
            return userService.getUserDOById(userId);
        }catch(Exception e){
            logger.error("userId: {} query userDO error, mes is {}.", userId, e);
        }
        return null;
    }

    public List<UserDO> getUserList(List<Long> userIds){
        return  userService.getUserInfoList(userIds);
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
    public MemResult<Boolean> updateUserDO(UserDO userDO){
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
            logger.error("updateUserDO par:{} return error:{}", JSONObject.toJSONString(userDO),
                    e);
        }
        return baseResult;
    }
}
