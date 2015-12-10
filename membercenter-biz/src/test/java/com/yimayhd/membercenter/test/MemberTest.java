package com.yimayhd.membercenter.test;

import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.membercenter.client.vo.MerchantPageQueryVO;
import com.yimayhd.user.client.domain.UserDO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class MemberTest{
	public static ClassPathXmlApplicationContext context ;
	@Before
	public void setUp() throws Exception {
		String springName = "spring";
		String[] ctx = {
				"META-INF/"+springName+"/application-common.xml",
				"META-INF/"+springName+"/application-consumer.xml",
				"META-INF/"+springName+"/application-export-api.xml",
				"META-INF/"+springName+"/application-export-client.xml",
				"META-INF/"+springName+"/application-manager.xml",
				"META-INF/"+springName+"/application-mq.xml",
				"META-INF/"+springName+"/application-persistance.xml",
				"META-INF/"+springName+"/application-repo.xml",
				"META-INF/"+springName+"/application-service.xml"
		} ;
		context = new ClassPathXmlApplicationContext(ctx);
	}



	@Autowired
	private MerchantService merchantService;
	
	@Test
	public void testFindPageUsersByMerchant(){
		MerchantPageQueryVO merchantPageQueryVO = new MerchantPageQueryVO();
		merchantPageQueryVO.setPageSize(10);
		merchantPageQueryVO.setPageNo(1);
//		merchantPageQueryVO.setCity();
//		merchantPageQueryVO.setNickName();
//		merchantPageQueryVO.setMerchantId();
		merchantPageQueryVO.setMerchantUserId(1L);
//		merchantPageQueryVO.setMobile();
		MemPageResult<UserDO> memResult = merchantService.findPageUsersByMerchant(merchantPageQueryVO);
		JSON.toJSONString(memResult);
	}
	
}
