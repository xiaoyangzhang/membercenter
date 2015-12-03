package com.yimayhd.membercenter.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yimayhd.ic.client.model.domain.item.ItemDO;
import com.yimayhd.ic.client.model.enums.ItemType;
import com.yimayhd.ic.client.model.result.ICResult;
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
		if( memberItemQueryDTO == null || CollectionUtils.isEmpty( memberItemQueryDTO.getItemIds() )){
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result ;
		}
		List<Long> itemIds = memberItemQueryDTO.getItemIds();
		ICResult<List<ItemDO>> itemResult = itemQueryService.getItemByIds(itemIds);
		
		if( itemResult == null || !itemResult.isSuccess() || CollectionUtils.isEmpty(itemResult.getModule())){
			logger.error("getItemByIds  itemIds={},  Result={}", JSON.toJSONString(itemIds), JSON.toJSONString(itemResult) );
			result.setReturnCode(MemberReturnCode.PAGE_QUERY_ITEM_FAILED);
			return result ;
		}
		List<ItemDO> itemDOs = itemResult.getModule();
		List<MemeberItem> items = new ArrayList<MemeberItem>() ;
		for(ItemDO itemDO : itemDOs ){
			MemeberItem item = new MemeberItem() ;
			item.itemId = itemDO.getId() ;
			ItemType itemType = ItemType.get(itemDO.getItemType()) ;
			item.itemType = itemType == null ? null : itemType.name() ;
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
