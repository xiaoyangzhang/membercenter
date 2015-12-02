package com.yimayhd.membercenter.mapper;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.MemberDO;

public interface MemberDOMapper {
	int deleteById(long id);

	int insert(MemberDO record);

	MemberDO selectById(@Param("id")long id);

	int update(MemberDO record);
	
	public MemberDO selectByUserId(long userId);
}