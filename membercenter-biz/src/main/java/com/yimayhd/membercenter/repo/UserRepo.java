package com.yimayhd.membercenter.repo;

import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class UserRepo {

    @Autowired
    private UserService userService;

    public UserDO getUserDOById(long userId) {
        return userService.getUserDOById(userId);
    }

    public List<UserDO> getUserList(List<Long> userIds){
        return  userService.getUserInfoList(userIds);
    }

}
