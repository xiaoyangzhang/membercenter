package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.MemberDO;

public interface MemberDOMapper {
	int deleteById(long id);

	int insert(MemberDO record);

	MemberDO selectById(long id);

	int update(MemberDO record);
	
	public MemberDO selectByUserId(long userId);
}