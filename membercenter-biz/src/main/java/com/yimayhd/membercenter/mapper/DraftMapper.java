package com.yimayhd.membercenter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.membercenter.client.domain.draft.DraftDO;
import com.yimayhd.membercenter.client.domain.draft.DraftDetailDO;
import com.yimayhd.membercenter.client.query.DraftListQuery;
import com.yimayhd.membercenter.client.vo.DraftVO;

/**
 * 
 * 草稿数据库接口
 * @author liuxp
 *
 */
public interface DraftMapper {

	/**
	 * 保存草稿
	 * @param draftDO 草稿详细信息
	 * @return 保存结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	int saveDraft(DraftDO draftDO);
	
	/**
	 * 覆盖草稿
	 * @param draftDO 覆盖的草稿信息
	 * @return 更新结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	int coverDraft(DraftDO draftDO);
	
	/**
	 * 获得草稿箱列表
	 * @param draftListQuery 查询条件
	 * @return 列表结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	List<DraftDO> getDraftList(DraftListQuery draftListQuery);
	
	/**
	 * 获得草稿箱列表数量
	 * @param draftListQuery 查询条件
	 * @return 返回结果数量
	 * @author liuxp
	 * @createTime 2016年6月6日
	 */
	int getDraftsCount(DraftListQuery draftListQuery);
	
	/**
	 * 批量删除草稿
	 * @param ids 删除ids
	 * @return 删除结果
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	int deleteDrafts(@Param("ids") Long[] ids);
	
	/**
	 * 获得指定草稿内容
	 * @param id 草稿id
	 * @return 指定草稿详细内容
	 * @author liuxp
	 * @createTime 2016年6月3日
	 */
	DraftDetailDO getDraftDetail(@Param("id")Long id);
	
	/**
	 * 通过类型来查找草稿详细内容
	 * @param draftVO 草稿查询条件（类型）
	 * @return 草稿详细信息
	 * @author liuxp
	 * @createTime 2016年6月6日
	 */
	DraftDetailDO getDraftDetailByType(DraftVO draftVO);
}
