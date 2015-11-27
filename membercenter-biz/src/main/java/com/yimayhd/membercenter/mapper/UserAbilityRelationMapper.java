package com.yimayhd.membercenter.mapper;



import com.yimayhd.membercenter.client.domain.UserAbilityRelationDO;

import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public interface UserAbilityRelationMapper {

    public List<UserAbilityRelationDO> getByUserId(long userId);

}
