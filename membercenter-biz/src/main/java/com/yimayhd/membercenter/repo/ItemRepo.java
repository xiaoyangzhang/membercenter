package com.yimayhd.membercenter.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yimayhd.ic.client.model.domain.item.ItemDO;
import com.yimayhd.ic.client.model.param.item.ItemQryDTO;
import com.yimayhd.ic.client.model.result.item.ItemPageResult;
import com.yimayhd.ic.client.service.item.ItemQueryService;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dto.MemberItemQueryDTO;
import com.yimayhd.membercenter.entity.member.MemeberItem;

public class ItemRepo {
	private static final Logger logger = LoggerFactory.getLogger("OrderRepo");
	@Autowired
	private ItemQueryService itemQueryService ;
	
	public MemResult<List<MemeberItem>> queryMemberItems(MemberItemQueryDTO memberItemQueryDTO){
		MemResult<List<MemeberItem>> result = new MemResult<List<MemeberItem>>();
		if( memberItemQueryDTO == null ){
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result ;
		}
		ItemQryDTO itemQryDTO = new ItemQryDTO() ;
		itemQryDTO.setPageNo(memberItemQueryDTO.getPageNo());
		itemQryDTO.setPageSize(memberItemQueryDTO.getPageSize());
		//FIXME 缺少通过会员信息
		ItemPageResult itemPageResult = itemQueryService.getItem(itemQryDTO);
		
		if( itemPageResult == null || !itemPageResult.isSuccess() ){
			logger.error("getItem  ItemQryDTO={},  Result={}", JSON.toJSONString(itemQryDTO), JSON.toJSONString(itemPageResult) );
			result.setReturnCode(MemberReturnCode.PAGE_QUERY_ITEM_FAILED);
			return result ;
		}
		List<ItemDO> itemDOs = itemPageResult.getItemDOList();
		if( itemDOs == null ){
			return result;
		}
		List<MemeberItem> items = new ArrayList<MemeberItem>() ;
		for(ItemDO itemDO : itemDOs ){
			MemeberItem item = new MemeberItem() ;
			item.itemId = itemDO.getId() ;
			item.itemPics = itemDO.getPicUrls() ;
			item.itemTitle = itemDO.getTitle() ;
			item.originalPrice = itemDO.getOriginalPrice() ;
			item.price = itemDO.getPrice() ;
			items.add(item);
		}
		result.setValue(items);
		return result ;
	}
}
