package com.yimayhd.membercenter.test.draft;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.yimayhd.ic.client.model.enums.ItemType;
import com.yimayhd.membercenter.client.dto.DraftDTO;
import com.yimayhd.membercenter.client.dto.DraftDetailDTO;
import com.yimayhd.membercenter.client.query.DraftListQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.enums.DraftEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.domain.draft.DraftDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.DraftManagerService;
import com.yimayhd.membercenter.manager.DraftManager;
import com.yimayhd.membercenter.mapper.MemberPrivilegeDOMapper;
import com.yimayhd.membercenter.service.client.DraftManagerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:application.xml",
		"classpath:application-persistance.xml", 
		"classpath:application-manager.xml",
		"classpath:application-session.xml",
		 "classpath:application-mq.xml",
		"classpath:application-repo.xml", 
		"classpath:application-common.xml",
		 "classpath:application-consumer.xml",
		 "classpath:application-export-client.xml",
		 "classpath:application-service.xml",
		 
		"classpath:application-export-api.xml"
		})
//@TransactionConfiguration(transactionManager = "transactionManager")
public class DraftManagerServiceTest {

	@Resource
	private DraftManagerServiceImpl draftManagerService;
	
//	@Test
	public void saveDraft(){
		
		DraftDO draftDO = new DraftDO();
		draftDO.setAccountId(125L);
		draftDO.setDomainId(100);
		draftDO.setDraftName("TEST7");
		draftDO.setMainType(DraftEnum.ITEM.getValue());
		draftDO.setSubType(ItemType.LINE.getValue());
		draftDO.setJSONStr("{id:1}");
		MemResult<Boolean> result = draftManagerService.saveDraft(draftDO);
		System.out.println(result.getValue());
	}

//	@Test
	public void converDraft(){

		DraftDO draftDO = new DraftDO();
		draftDO.setId(1L);
		draftDO.setJSONStr("{id:2}");
		MemResult<Boolean> result = draftManagerService.coverDraft(draftDO);
		System.out.println(result.getValue());
	}

//	@Test
	public void getDraftDetail(){

		MemResult<DraftDetailDTO> result = draftManagerService.getDraftDetail(1l);
		System.out.println(result.getValue());
	}

//	@Test
	public void getDraftList(){
		DraftListQuery draftListQuery = new DraftListQuery();
		draftListQuery.setAccountId(123L);
//		draftListQuery.setMainType(DraftEnum.ITEM.getValue());
//		draftListQuery.setSubType(ItemType.LINE.getValue());
		draftListQuery.setMainType(0);
		draftListQuery.setSubType(0);
		draftListQuery.setDomainId(100);
		draftListQuery.setPageNo(0);
		draftListQuery.setPageSize(10);
		MemPageResult<DraftDTO> result = draftManagerService.getDraftList(draftListQuery);
		System.out.println(result.getList());
	}

//	@Test
	public void deleteDraft() {
		List<Long> ids = new ArrayList<>();
		ids.add(7L);
		MemResult<Boolean> result = draftManagerService.deleteDrafts(ids);
		System.out.println(result.getValue());
	}
}
