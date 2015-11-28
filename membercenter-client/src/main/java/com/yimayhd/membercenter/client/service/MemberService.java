package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.result.MemResult;

public interface MemberService {
	/**
	 * 通过id查询会员信息
	 * @param id
	 * @return
	 */
	public MemResult<MemberDO> getMemberById(long id);
	
	/**
	 * 强制将会员过期
	 * @param id
	 * @return
	 */
	public MemResult<Boolean> overdueMember(long id) ;
}
