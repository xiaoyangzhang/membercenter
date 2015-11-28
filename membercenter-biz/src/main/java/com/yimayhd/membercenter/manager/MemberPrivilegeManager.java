package com.yimayhd.membercenter.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dao.MemberPrivilegeDao;
import com.yimayhd.membercenter.dto.PrivilegeBatchUpdateDTO;
import com.yimayhd.membercenter.query.MemPrivilegePageQuery;

public class MemberPrivilegeManager {
	
	private static final Logger log = LoggerFactory.getLogger(MemberPrivilegeManager.class);
	
	@Autowired
	private MemberPrivilegeDao memberPrivilegeDao;

	public MemResult<MemberPrivilegeDO> savePrivilege(MemberPrivilegeDO memberPrivilegeDO) {

		MemResult<MemberPrivilegeDO> memResult = new MemResult<MemberPrivilegeDO>();
		
		if(null == memberPrivilegeDO){
			
			memResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResult;
		}
		
		memberPrivilegeDO = memberPrivilegeDao.insert2DB(memberPrivilegeDO);
		
		if(null == memberPrivilegeDO){
			
			memResult.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
			return memResult;
		}
		
		memResult.setValue(memberPrivilegeDO);
		
		return memResult;
	}

	public MemResult<MemberPrivilegeDO> updatePrivilege(MemberPrivilegeDO memberPrivilegeDO) {
		
		MemResult<MemberPrivilegeDO> memResult = new MemResult<MemberPrivilegeDO>();
		
		if(null == memberPrivilegeDO){
			
			memResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResult;
		}
		
		memberPrivilegeDO = memberPrivilegeDao.update2DB(memberPrivilegeDO);
		
		if(null == memberPrivilegeDO){
			
			memResult.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
			return memResult;
		}
		
		memResult.setValue(memberPrivilegeDO);
		
		return memResult;
	}

	public MemResult<MemberPrivilegeDO> getPrivilegeById(long id) {
		
		MemResult<MemberPrivilegeDO> memResult = new MemResult<MemberPrivilegeDO>();
		
		if(id <= 0){
			
			memResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return memResult;
		}

		MemberPrivilegeDO memberPrivilegeDO = memberPrivilegeDao.getPrivilege4DB(id);
		
		memResult.setValue(memberPrivilegeDO);
		
		return memResult;
	}

	public MemPageResult<MemberPrivilegeDO> pageQuery(MemPrivilegePageQuery memPrilvilegePageQuery) {
		
		MemPageResult<MemberPrivilegeDO> pageResult = new MemPageResult<MemberPrivilegeDO>();
		
		try {
			
			pageResult.setPageNo(memPrilvilegePageQuery.getPageNo());
			pageResult.setPageSize(memPrilvilegePageQuery.getPageSize());
			
			List<MemberPrivilegeDO> pageQueryList = memberPrivilegeDao
					.pageQuery(memPrilvilegePageQuery);
			pageResult.setList(pageQueryList);
			
			 if (memPrilvilegePageQuery.isNeedCount()) {
				 
	                int count = memberPrivilegeDao.queryCount(memPrilvilegePageQuery);
	                pageResult.setTotalCount(count);
	                pageResult.setHasNext(count > memPrilvilegePageQuery.getPageNo() * memPrilvilegePageQuery.getOldPageSize());
	                
	            } else if (memPrilvilegePageQuery.isHasNextMod()){
	            	
	                if (!CollectionUtils.isEmpty(pageQueryList) && pageQueryList.size() > memPrilvilegePageQuery.getOldPageSize()) {
	                	pageResult.setHasNext(true);
	                    pageQueryList.remove(pageQueryList.size() - 1);
	                }
	                
	            }
			
		} catch (Exception e) {
			
			log.error("MemberPrivilegeManager.pageQuery excrption memPrilvilegePageQuery : " + memPrilvilegePageQuery.toString(), e);
			
			pageResult.setReturnCode(MemberReturnCode.DB_READ_FAILED);
			
			return pageResult; 
		}
		
		return pageResult;
		
	}

	public MemResult<List<Long>> updateBatchUpOrDown(PrivilegeBatchUpdateDTO privilegeBatchUpdateDTO) {
		
		MemResult<List<Long>> baseResult = new MemResult<List<Long>>();
		
		List<Long> ids = privilegeBatchUpdateDTO.getIds();
		
		MemberPrivilegeDO memberPrivilegeDO = null;
		
		List<Long> checkErrorIds = new ArrayList<Long>();
		
		if(null != ids && ids.size() > 0){
			
			int updateSize = 0;
			
			for (Long privilegeId : ids) {
				
				if(null != privilegeId){
					
					memberPrivilegeDO = new MemberPrivilegeDO();
					
					memberPrivilegeDO.setId(privilegeId);
					memberPrivilegeDO.setStatus(privilegeBatchUpdateDTO.getStatus());
					
					int updateStatus = memberPrivilegeDao.updateStatus2DB(memberPrivilegeDO);
					
					if(updateStatus == 1){
						
						updateSize++;
					} else {
						
						checkErrorIds.add(privilegeId);
						
					}
				}
			}
			
			if(updateSize != privilegeBatchUpdateDTO.getIds().size()){
				
				baseResult.setReturnCode(MemberReturnCode.UPTATE_PRIVILEGE_STATUS_ERROR);
				baseResult.setValue(checkErrorIds);
			}
		} else {
			
			baseResult.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			
		}
		
		
		return baseResult;
	}

}
