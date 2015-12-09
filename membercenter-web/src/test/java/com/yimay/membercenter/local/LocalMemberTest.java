package com.yimay.membercenter.local;


import com.yimayhd.membercenter.api.TravelKaApi;
import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.BaseMerchantService;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantPageQueryVO;
import com.yimayhd.membercenter.client.vo.MerchantVO;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.TravelKa;
import com.yimayhd.membercenter.entity.TravelKaPageInfoList;
import com.yimayhd.user.client.domain.UserDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimay.membercenter.LocalBaseTest;
import com.yimayhd.membercenter.api.MemberApi;
import com.yimayhd.membercenter.entity.member.MemberDetail;
import com.yimayhd.membercenter.entity.member.MemberPurchauseDetail;

import java.util.List;

public class LocalMemberTest extends LocalBaseTest{
	@Autowired
	private MemberApi memberApi ;

	@Autowired
	private TravelKaApi travelKaApi;

	@Autowired
	private BaseMerchantService baseMerchantService;

	@Autowired
	private MerchantService merchantService;

	@Test
	public void test(){
		process();
	}
	
	private void process(){
		try{
			
			getMemberDetail();
			
//			getMemberPurchuseDetail();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.err.println();
	}
	
	private void getMemberDetail(){
		System.err.println();
		long userId = 401 ;
		int appId =1 ;
		int domainId = 1 ;
		int versionCode = 10 ;
		long deviceId= 10 ;
		MemberDetail memberDetail = memberApi.getMemberDetail(appId, domainId, deviceId, userId, versionCode);
		
		printResult(memberDetail, "getMemberDetail");
		System.err.println();
	}
	private void getMemberPurchuseDetail(){
//		System.err.println();
		long userId = 123456 ;
		int appId =1 ;
		int domainId = 1 ;
		int versionCode = 10 ;
		long deviceId= 10 ;
		MemberPurchauseDetail memberPurchauseDetail = memberApi.getMemberPurchuseDetail(appId, domainId, deviceId, userId, versionCode);
		
		printResult(memberPurchauseDetail, "getMemberPurchuseDetail");
		System.err.println();
	}

	@Test
	public void getTravelKaDetailTest(){
		TravelKa travelKa = travelKaApi.getTravelKaDetail(1, 2, 3, 4, 5, 401);
		System.out.println("aaaaaaaaaaaaaaaaa");
	}

	@Test
	public void getTravelKaListPageTest() {
		PageInfo pageInfo = new PageInfo();
		pageInfo.pageSize = 10;
		pageInfo.pageNo = 0;
		TravelKaPageInfoList  travelKaPageInfoList = travelKaApi.getTravelKaListPage(1, 2, 3, 4, 5, pageInfo, "NEWJOIN");
		System.out.println("bbbbbb");
	}

	@Test
	public void getMerchantByUserIdTest() {
		long userId = 1L;
		MemResult<BaseMerchantDO> memResult = baseMerchantService.getMerchantByUserId(userId);
		System.out.println("abc");
	}



	@Test
	public void testFindPageUsersByMerchant(){
		MerchantPageQueryVO merchantPageQueryVO = new MerchantPageQueryVO();
		merchantPageQueryVO.setPageNo(1);
		merchantPageQueryVO.setPageSize(10);
//		merchantPageQueryVO.setCity();
//		merchantPageQueryVO.setNickName();
		merchantPageQueryVO.setMerchantUserId(123456789L);
//		merchantPageQueryVO.setMobile();
		MemResult<List<UserDO>> memResult = merchantService.findPageUsersByMerchant(merchantPageQueryVO);
		printResult(memResult,"MemResult");
	}



	@Test
	public void testRegisterUser(){
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setMerchantUserId(1L);
		merchantVO.setMobile("13581937677");
		merchantVO.setOpenId("aaaaaaa");
		MemResult<UserDO> memResult = merchantService.registerUser(merchantVO);
		printResult(memResult,"MemResult");
	}

	@Test
	public void testFindUserByOpenIdAndMerchant(){
		MerchantVO merchantVO = new MerchantVO();
		merchantVO.setMerchantUserId(1L);
		merchantVO.setMobile("13581937677");
		merchantVO.setMerchantUserId(8790L);
		merchantVO.setOpenId("aaaaaaa");
		MemResult<UserDO> memResult = merchantService.findUserByOpenIdAndMerchant(merchantVO);
		printResult(memResult,"MemResult");
	}



}
