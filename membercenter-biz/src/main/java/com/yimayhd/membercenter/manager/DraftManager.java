package com.yimayhd.membercenter.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.draft.DraftDO;
import com.yimayhd.membercenter.client.domain.draft.DraftDetailDO;
import com.yimayhd.membercenter.client.dto.DraftDTO;
import com.yimayhd.membercenter.client.dto.DraftDetailDTO;
import com.yimayhd.membercenter.client.query.DraftListQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.vo.DraftVO;
import com.yimayhd.membercenter.conventer.DraftConverter;
import com.yimayhd.membercenter.mapper.DraftMapper;

/**
 * 草稿管理
 * @author liuxp
 *
 */
public class DraftManager {
	
    private final static Logger logger = LoggerFactory.getLogger(MemberManager.class);
    
    /**
     * 草稿箱数据接口
     */
    @Autowired
    private DraftMapper draftMapper;
    
    /**
     * 存储草稿
     * @param draftDO 数据do
     * @return 返回结果
     * @author liuxp
     * @createTime 2016年6月6日
     */
    public MemResult<Boolean> saveDraft(DraftDO draftDO) {
    	MemResult<Boolean> result = new MemResult<Boolean>(false);
    	if(draftDO.getAccountId()==null||StringUtils.isEmpty(draftDO.getDraftName())||StringUtils.isEmpty(draftDO.getJSONStr())||!(draftDO.getMainType()>0)||!(draftDO.getSubType()>0)||!(draftDO.getDomainId()>0)) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
    		return result;
    	}
    	draftDO.setGmtCreated(new Date());
    	draftDO.setGmtModified(new Date());
    	try {
			int count = draftMapper.checkNameExisit(draftDO);
			result.setValue(true);
			if(count>0) {
				result.setReturnCode(MemberReturnCode.DRAFTNAME_EXISTS_FAILED);
				result.setSuccess(true);
			} else {
				draftMapper.saveDraft(draftDO);
			}
		} catch (Exception e) {
			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
		}
    	return result;
    }
    
    /**
     * 删除草稿
     * @param ids 删除的ids
     * @return 返回结果
     * @author liuxp
     * @createTime 2016年6月6日
     */
    public MemResult<Boolean> deleteDrafts(List<Long> ids) {
    	
    	MemResult<Boolean> result = new MemResult<Boolean>(false);
    	int size = ids.size();
		if(null==ids||size==0) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
    		return result;
    		
    	} else {
    		Long[] param = new Long[size];
    		for (int i=0;i<size;i++) {
    			Long tempLong = ids.get(i);
				if(null==tempLong) {
					result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
		    		return result;
				} else {
					param[i] = tempLong;
				}
			}
    		
    		try {
        		draftMapper.deleteDrafts(param);
        		result.setValue(true);
    		} catch (Exception e) {
    			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
    		}
    	}
    	
    	return result;
    }
    
    /**
     * 覆盖草稿
     * @param draftDO 覆盖的信息
     * @return 返回结果
     * @author liuxp
     * @createTime 2016年6月6日
     */
    public MemResult<Boolean> coverDraft(DraftDO draftDO) {
    	MemResult<Boolean> result = new MemResult<Boolean>(false);
    	if(draftDO.getId()==null||StringUtils.isEmpty(draftDO.getJSONStr())) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
    		return result;
    	}
    	draftDO.setGmtModified(new Date());
    	try {
    		draftMapper.coverDraft(draftDO);
    		result.setValue(true);
		} catch (Exception e) {
			result.setReturnCode(MemberReturnCode.DB_WRITE_FAILED);
		}
    	return result;
    }
    
    /**
     * 获得草稿箱列表接口
     * @param draftListQuery 查询条件
     * @return 草稿箱列表
     * @author liuxp
     * @createTime 2016年6月6日
     */
    public MemPageResult<DraftDTO> getDraftsList(DraftListQuery draftListQuery) {
    	
    	MemPageResult<DraftDTO> result = new MemPageResult<DraftDTO>();
        result.setPageNo(draftListQuery.getPageNo());
        result.setPageSize(draftListQuery.getPageSize());
    	result.setSuccess(false);
        result.setHasNext(false);
        List<DraftDO> draftDOs;
        int count = 0;
        try {
			count = draftMapper.getDraftsCount(draftListQuery);
			if(count>0) {
				draftDOs = draftMapper.getDraftList(draftListQuery);
			} else {
				result.setTotalCount(count);
				result.setSuccess(true);
				return result;
			}
		} catch (Exception e) {
			logger.error("draftListQuery " + draftListQuery.toString(), e);
			result.setReturnCode(MemberReturnCode.DB_READ_FAILED);
			return result;
		} 
        
        if(CollectionUtils.isEmpty(draftDOs)) {
        	result.setTotalCount(count);
        	result.setSuccess(true);
            return result;
        }
        
        try {
        	List<DraftDTO> draftDTOs = DraftConverter.converterDraftList(draftDOs);
        	result.setList(draftDTOs);
        	result.setSuccess(true);
        	result.setHasNext(count-draftListQuery.getPageNo()*draftListQuery.getPageSize()>0);
		} catch (Exception e) {
			logger.error("draftListQuery " + draftListQuery.toString(), e);

			//TODO
			result.setReturnCode(MemberReturnCode.DB_READ_FAILED);
			return result;
		}
        return result;
    }
    
    /**
     * 通过id查找草稿详细信息
     * @param id 草稿id
     * @return 草稿详细信息
     * @author liuxp
     * @createTime 2016年6月6日
     */
    public MemResult<DraftDetailDTO> getDetailById(Long id) {
		MemResult<DraftDetailDTO> result = new MemResult<DraftDetailDTO>();
		result.setSuccess(false);
		DraftDO draftDO;
		try {
			draftDO = draftMapper.getDraftDetail(id);
			
		} catch (Exception e) {
			logger.error("getDetailById id " + id, e);
			result.setReturnCode(MemberReturnCode.DB_READ_FAILED);
			return result;
		} 
		
		if(null==draftDO) {
			logger.info("getDetailById id= {} find no data", id);
			return result;
		} else {
			DraftDetailDTO draftDetailDTO = new DraftDetailDTO();
			draftDetailDTO.setId(draftDO.getId());
			draftDetailDTO.setJSONStr(draftDO.getJSONStr());
			result.setSuccess(true);
			result.setValue(draftDetailDTO);
		}
		return result;
    }
    
    /**
     * 通过类型获得草稿详细信息
     * @param draftVO 查询条件
     * @return 草稿详细信息
     * @author liuxp
     * @createTime 2016年6月6日
     */
    public MemResult<DraftDetailDTO> getDetailByType(DraftVO draftVO) {
    	MemResult<DraftDetailDTO> result = new MemResult<DraftDetailDTO>();
		result.setSuccess(false);
		
		if(!(draftVO.getMainType()>0)||!(draftVO.getSubType()>0)||draftVO.getAccountId()==null) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
    		return result;
		}
		DraftDO draftDO;

		try {
			draftDO = draftMapper.getDraftDetailByType(draftVO);
			
		} catch (Exception e) {
			logger.error("getDetailByType draftVO " + draftVO, e);
			result.setReturnCode(MemberReturnCode.DB_READ_FAILED);
			return result;
		} 
		
		if(null==draftDO) {
			logger.info("getDetailByType draftVO= {} find no data", draftVO);
			return result;
		} else {
			DraftDetailDTO draftDetailDTO = new DraftDetailDTO();
			draftDetailDTO.setId(draftDO.getId());
			draftDetailDTO.setJSONStr(draftDO.getJSONStr());
			result.setSuccess(true);
			result.setValue(draftDetailDTO);
		}
		return result;
    }
}
