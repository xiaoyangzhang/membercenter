package com.yimayhd.membercenter.mapper;


import com.yimayhd.membercenter.client.domain.MemberProfileDO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.user.client.query.UserPageQuery;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface MemberProfileDOMapper {

    public MemberProfileDO getById(long id);

    public MemberProfileDO getByUserId(long UserId);

    List<MemberProfileDO> pageQuery(TravelkaPageQuery travelkaPageQuery);

    int queryCount(TravelkaPageQuery travelkaPageQuery);

}
