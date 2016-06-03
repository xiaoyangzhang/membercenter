package com.yimayhd.membercenter.service.client;

import java.util.List;

import com.yimayhd.membercenter.client.domain.draft.DraftDO;
import com.yimayhd.membercenter.client.domain.draft.DraftDetailDO;
import com.yimayhd.membercenter.client.dto.DraftDTO;
import com.yimayhd.membercenter.client.query.DraftListQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.DraftManagerService;

public class DraftManagerServiceImpl implements DraftManagerService {

	/**
	 * 保存草稿
	 * @param draftVO 新建的vo
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<Boolean> saveDraft(DraftDO draftDO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存草稿
	 * @param ids 指定的ids
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<Boolean> deleteDrafts(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存草稿
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<Boolean> coverDraft(DraftDO draftDO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获得草稿箱信息列表
	 * @param draftListQuery 查询条件入力
	 * @return 查询列表信息
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemPageResult<DraftDTO> getDraftList(DraftListQuery draftListQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获得草稿详细信息
	 * @param id 草稿主键
	 * @return 草稿详细信息
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	@Override
	public MemResult<DraftDetailDO> getDraftDetail(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
