package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.query.MemPrivilegePageQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dto.PrivilegeBatchUpdateDTO;

/**
 * 会员特权接口
 * @author Administrator
 *
 */
public interface MemberPrivilegeService {

	public MemResult<MemberPrivilegeDO> savePrivilege(MemberPrivilegeDO memberPrivilegeDO);
	
	public MemResult<MemberPrivilegeDO> updatePrivilege(MemberPrivilegeDO memberPrivilegeDO);
	
	public MemResult<MemberPrivilegeDO> getPrivilegeById(long id);
	
	public MemPageResult<MemberPrivilegeDO> pageQuery(MemPrivilegePageQuery memPrilvilegePageQuery);
	
	public MemResult<List<Long>> updateBatchUpOrDown(PrivilegeBatchUpdateDTO PrivilegeBatchUpdateDTO);
}
