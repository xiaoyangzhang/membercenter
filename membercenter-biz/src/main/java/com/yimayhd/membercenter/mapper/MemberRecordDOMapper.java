package com.yimayhd.membercenter.mapper;

import com.yimayhd.membercenter.client.domain.MemberRecordDO;

public interface MemberRecordDOMapper {
	int deleteById(long id);

	int insert(MemberRecordDO record);

	MemberRecordDO selectById(long id);

	int update(MemberRecordDO record);
}