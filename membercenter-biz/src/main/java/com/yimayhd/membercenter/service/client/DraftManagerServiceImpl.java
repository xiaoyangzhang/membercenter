package com.yimayhd.membercenter.service.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.draft.DraftDO;
import com.yimayhd.membercenter.client.dto.DraftDetailDTO;
import com.yimayhd.membercenter.client.query.DraftListQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.DraftManagerService;
import com.yimayhd.membercenter.client.dto.DraftDTO;
import com.yimayhd.membercenter.manager.DraftManager;

public class DraftManagerServiceImpl implements DraftManagerService {

	/**
	 * 草稿箱manager
	 */
	@Autowired
	private DraftManager draftManager;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(DraftManagerServiceImpl.class);

	
	/**
	 * 保存草稿
	 * @param draftVO 新建的vo
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<Long> saveDraft(DraftDO draftDO) {
        LOGGER.info("saveDraft draftDO= {}", draftDO);
        MemResult<Long> result = new MemResult<Long>();
        try {
        	result = draftManager.saveDraft(draftDO);
		} catch (Exception e) {
            LOGGER.error("draftManagerServiceImpl.saveDraft occur error:{}", e);
            result.setValue(null);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
        
		return result;
	}

	/**
	 * 保存草稿
	 * @param ids 指定的ids
	 * @param accountId 用户id
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<Boolean> deleteDrafts(List<Long> ids, Long accountId) {
        LOGGER.info("deleteDrafts ids= {}", ids);
        MemResult<Boolean> result = new MemResult<Boolean>();
        try {
        	result = draftManager.deleteDrafts(ids, accountId);
		} catch (Exception e) {
            LOGGER.error("draftManagerServiceImpl.deleteDrafts occur error:{}", e);
            result.setValue(false);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
        
		return result;
	}

	/**
	 * 保存草稿
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<Boolean> coverDraft(DraftDO draftDO) {
        LOGGER.info("coverDraft draftDO= {}", draftDO);
        MemResult<Boolean> result = new MemResult<Boolean>();
        try {
        	result = draftManager.coverDraft(draftDO);
		} catch (Exception e) {
            LOGGER.error("draftManagerServiceImpl.coverDraft occur error:{}", e);
            result.setValue(false);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 获得草稿箱信息列表
	 * @param draftListQuery 查询条件入力
	 * @return 查询列表信息
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemPageResult<com.yimayhd.membercenter.client.dto.DraftDTO> getDraftList(DraftListQuery draftListQuery) {
        LOGGER.info("getDraftList draftListQuery= {}", draftListQuery);
        MemPageResult<com.yimayhd.membercenter.client.dto.DraftDTO> result = new MemPageResult<com.yimayhd.membercenter.client.dto.DraftDTO>();
        try {
        	result = draftManager.getDraftsList(draftListQuery);
		} catch (Exception e) {
            LOGGER.error("draftManagerServiceImpl.getDraftList occur error:{}", e);
            result.setSuccess(false);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 获得草稿详细信息
	 * @param id 草稿主键
	 * @return 草稿详细信息
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<DraftDetailDTO> getDraftDetail(Long id) {
		LOGGER.info("getDraftDetail id= {}", id);
		MemResult<DraftDetailDTO> result = new MemResult<DraftDetailDTO>();
        try {
        	result = draftManager.getDetailById(id);
		} catch (Exception e) {
            LOGGER.error("draftManagerServiceImpl.getDraftDetail occur error:{}", e);
            result.setSuccess(false);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 通过类型和商户id获得草稿箱信息
	 * @param draftDTO 入力参数
	 * @return 草稿详细结果
	 * @author liuxp
	 * @createTime 2016年6月6日
	 */
	@Override
	public MemResult<DraftDetailDTO> getDraftDetailByType(DraftDTO draftDTO) {
		LOGGER.info("getDraftDetail draftDTO= {}", draftDTO);
		MemResult<DraftDetailDTO> result = new MemResult<DraftDetailDTO>();
        try {
        	result = draftManager.getDetailByType(draftDTO);
		} catch (Exception e) {
            LOGGER.error("draftManagerServiceImpl.getDraftDetailByType occur error:{}", e);
            result.setSuccess(false);
            result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result;
	}
}
