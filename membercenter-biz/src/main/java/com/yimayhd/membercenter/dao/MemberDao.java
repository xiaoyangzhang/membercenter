package com.yimayhd.membercenter.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.mapper.MemberDOMapper;

public class MemberDao {
	@Autowired
	private MemberDOMapper memberDOMapper;
	

	public MemberDO insert(MemberDO record){
		if( record == null ){
			return null;
		}
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		int count = memberDOMapper.insert(record);
		if( count == 1 ){
			return record ;
		}
		return null ;
	}

	public MemberDO selectById(long id){
		return memberDOMapper.selectById(id);
	}

	public MemberDO update(MemberDO record){
		if( record == null ){
			return null;
		}
		record.setGmtModified(new Date());
		int count = memberDOMapper.update(record);
		if( count == 1 ){
			return record ;
		}
		return null ;
	}
}
