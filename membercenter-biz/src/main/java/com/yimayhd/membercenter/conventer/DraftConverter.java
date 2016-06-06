package com.yimayhd.membercenter.conventer;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.ic.client.model.enums.ItemType;
import com.yimayhd.membercenter.client.domain.draft.DraftDO;
import com.yimayhd.membercenter.client.dto.DraftDTO;
import com.yimayhd.membercenter.enums.DraftEnum;

/**
 * 邮箱信息转化类
 * @author liuxp
 *
 */
public class DraftConverter {

	/**
	 * 转换草稿箱列表处理
	 * @param draftDOs 草稿箱列表DO
	 * @return 转换格式后的草稿箱列表DTO
	 * @author liuxp
	 * @throws Exception 
	 * @createTime 2016年6月6日
	 */
	public static List<DraftDTO> converterDraftList(List<DraftDO> draftDOs) throws Exception {
		List<DraftDTO> result = new ArrayList<>();
		for (DraftDO draftDO : draftDOs) {
			DraftDTO draftDTO = new DraftDTO();
			draftDTO.setDraftName(draftDO.getDraftName());
			draftDTO.setGmtCreated(draftDO.getGmtCreated());
			draftDTO.setId(draftDO.getId());
			int mainType = draftDO.getMainType();
			int subType = draftDO.getSubType();
			
			if(mainType==DraftEnum.ITEM.getValue()) {
				draftDTO.setSubTypeName(ItemType.get(subType).getText());
				result.add(draftDTO);
				continue;
			} else if(mainType==DraftEnum.RESOURCE.getValue()) {
				
				//TODO
			} else {
				throw new Exception("DraftConverter.converterDraftList data error");
			}
		}
		return result;
	}
}
