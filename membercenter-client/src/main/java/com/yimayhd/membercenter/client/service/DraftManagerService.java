package com.yimayhd.membercenter.client.service;

import java.util.List;

import com.yimayhd.membercenter.client.domain.draft.DraftDetailDO;
import com.yimayhd.membercenter.client.query.DraftListQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.vo.DraftVO;

/**
 * 草稿箱接口
 * @author liuxp
 *
 */
public interface DraftManagerService {
	
	/**
	 * 保存草稿
	 * @param draftVO 新建的vo
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	public MemResult<Boolean> saveDraft(DraftVO draftVO);
	
	/**
	 * 保存草稿
	 * @param ids 指定的ids
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	public MemResult<Boolean> deleteDrafts(List<Long> ids);
	
	/**
	 * 保存草稿
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	public MemResult<Boolean> coverDraft(DraftVO draftVO);
	
	/**
	 * 获得草稿箱信息列表
	 * @param draftListQuery 查询条件入力
	 * @return 查询列表信息
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	public MemPageResult<DraftVO> getDraftList(DraftListQuery draftListQuery);
	
	/**
	 * 获得草稿详细信息
	 * @param id 草稿主键
	 * @return 草稿详细信息
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	public MemResult<DraftDetailDO> getDraftDetail(Long id);
}
