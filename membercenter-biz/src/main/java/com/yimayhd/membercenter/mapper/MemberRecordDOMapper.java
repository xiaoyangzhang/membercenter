package com.yimayhd.membercenter.mapper;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.MemberRecordDO;

public interface MemberRecordDOMapper {
	int deleteById(long id);

	int insert(MemberRecordDO record);

	MemberRecordDO selectById(long id);

	int update(MemberRecordDO record);
	
	public MemberRecordDO getMemberRecordByOutInfo(@Param("outerId")String outerId, @Param("outerType")int outerType);
	
}