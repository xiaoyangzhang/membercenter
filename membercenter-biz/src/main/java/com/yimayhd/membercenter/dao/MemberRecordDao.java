package com.yimayhd.membercenter.dao;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.MemberRecordDO;
import com.yimayhd.membercenter.mapper.MemberRecordDOMapper;

public class MemberRecordDao {
	@Autowired
	private MemberRecordDOMapper memberRecordDOMapper;
	

	public MemberRecordDO insert(MemberRecordDO record){
		if( record == null ){
			return null;
		}
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		int count = memberRecordDOMapper.insert(record);
		if( count == 1 ){
			return record ;
		}
		return null ;
	}

	public MemberRecordDO selectById(long id){
		return memberRecordDOMapper.selectById(id);
	}

	public MemberRecordDO update(MemberRecordDO record){
		if( record == null ){
			return null;
		}
		record.setGmtModified(new Date());
		int count = memberRecordDOMapper.update(record);
		if( count == 1 ){
			return record ;
		}
		return null ;
	}
	
	public MemberRecordDO getMemberRecordByOutInfo(String outerId, int outerType){
		if( StringUtils.isBlank(outerId) || outerType <= 0 ){
			return null;
		}
		return memberRecordDOMapper.getMemberRecordByOutInfo(outerId, outerType);
	}
	
//	public MemberRecordDO queryMemberRecords(MemberRecordQuery memberRecordQuery){
//		if( memberRecordQuery == null || StringUtils.isBlank(memberRecordQuery.getOuterId()) || memberRecordQuery.getOuterType() <= 0 ){
//			return null;
//		}
//		return memberRecordDOMapper.getMemberRecordByOutInfo(outerId, outerType);
//	}
}
