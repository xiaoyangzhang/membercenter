package com.yimayhd.membercenter.mapper;

import java.util.List;

import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.query.MemPrivilegePageQuery;

public interface MemberPrivilegeDOMapper {
    int deleteByPrimaryKey(long id);

    int insert(MemberPrivilegeDO record);

    MemberPrivilegeDO selectByPrimaryKey(long id);

    int updateByPrimaryKey(MemberPrivilegeDO record);

	List<MemberPrivilegeDO> pageQuery(MemPrivilegePageQuery memPrilvilegePageQuery);

	int queryCount(MemPrivilegePageQuery memPrilvilegePageQuery);

	int updateStatus(MemberPrivilegeDO memberPrivilegeDO);
}