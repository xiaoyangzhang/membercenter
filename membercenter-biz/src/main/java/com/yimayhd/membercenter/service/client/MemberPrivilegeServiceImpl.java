package com.yimayhd.membercenter.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MemberPrivilegeService;
import com.yimayhd.membercenter.dto.PrivilegeBatchUpdateDTO;
import com.yimayhd.membercenter.manager.MemberPrivilegeManager;
import com.yimayhd.membercenter.query.MemPrivilegePageQuery;

public class MemberPrivilegeServiceImpl implements MemberPrivilegeService {
	
	@Autowired
	private MemberPrivilegeManager privilegeManager;
	
	public MemResult<MemberPrivilegeDO> savePrivilege(MemberPrivilegeDO memberPrivilegeDO) {
		
		MemResult<MemberPrivilegeDO> memResult = new MemResult<MemberPrivilegeDO>();
		
		if(null == memberPrivilegeDO){
			
			memResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResult;
		}
		
		return privilegeManager.savePrivilege(memberPrivilegeDO);
	}

	public MemResult<MemberPrivilegeDO> updatePrivilege(MemberPrivilegeDO memberPrivilegeDO) {
		
		MemResult<MemberPrivilegeDO> memResult = new MemResult<MemberPrivilegeDO>();
		
		if(null == memberPrivilegeDO){
			
			memResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResult;
		}
		
		return privilegeManager.updatePrivilege(memberPrivilegeDO);
	}

	@Override
	public MemResult<MemberPrivilegeDO> getPrivilegeById(long id) {
		
		MemResult<MemberPrivilegeDO> memResult = new MemResult<MemberPrivilegeDO>();
		
		if(id <= 0){
			
			memResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResult;
		}
		
		return privilegeManager.getPrivilegeById(id);
	}

	@Override
	public MemPageResult<MemberPrivilegeDO> pageQuery(MemPrivilegePageQuery memPrilvilegePageQuery) {
		
		return privilegeManager.pageQuery(memPrilvilegePageQuery);
	}

	@Override
	public MemResult<List<Long>> updateBatchUpOrDown(PrivilegeBatchUpdateDTO PrivilegeBatchUpdateDTO) {
		
		return privilegeManager.updateBatchUpOrDown(PrivilegeBatchUpdateDTO);
	}

}
