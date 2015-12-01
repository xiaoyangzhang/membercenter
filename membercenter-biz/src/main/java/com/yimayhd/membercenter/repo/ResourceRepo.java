package com.yimayhd.membercenter.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.yimayhd.resourcecenter.entity.Booth;
import com.yimayhd.resourcecenter.entity.Showcase;
import com.yimayhd.resourcecenter.model.result.RcResult;
import com.yimayhd.resourcecenter.service.BoothClientServer;

public class ResourceRepo {
	private static final Logger logger = LoggerFactory.getLogger("ResourceRepo");
	@Autowired
	private BoothClientServer boothClientServer ;
	/**
	 * 景区筛选时用到的主题列表code
	 */
	private static final String BOOTH_CODE_SCENIC_SUBJECT = "MEMBER_ITEMS";
	
	public List<Long> getMemberItemIds(){
		final String boothCode = BOOTH_CODE_SCENIC_SUBJECT  ;
		RcResult<Booth> result = boothClientServer.getBooth(boothCode);
		if( result == null || !result.isSuccess() || result.getT() == null || CollectionUtils.isEmpty(result.getT().getShowcases())  ){
			logger.error("getBooth   boothCode={},  Result={}", JSON.toJSONString(boothCode), JSON.toJSONString(result));
			return null;
		}
		List<Showcase> showcases = result.getT().getShowcases();
		
		List<Long> itemIds = new ArrayList<Long>();
		for( Showcase showcase : showcases ){
			String content = showcase.getOperationContent() ;
			if( !StringUtils.isBlank(content) ){
				try{
					long itemId = Long.parseLong(content);
					itemIds.add(itemId) ;
				}catch(Exception e){
					logger.error("Long.parseLong(content) failed!  content="+content, e);
				}
			}
		}
		return itemIds;
	}
}
