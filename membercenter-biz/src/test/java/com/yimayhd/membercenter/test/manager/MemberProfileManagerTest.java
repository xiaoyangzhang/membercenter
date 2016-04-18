package com.yimayhd.membercenter.test.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.manager.MemberProfileManager;
import com.yimayhd.membercenter.test.BaseTest;

public class MemberProfileManagerTest extends BaseTest {
	@Autowired
	private MemberProfileManager memberProfileManager;

	@Test
	public void getTravelKaDetailTest() {
		System.out.println(JSONObject.toJSONString(memberProfileManager.getTravelKaDetail(10,1000)));
		System.out.println("-------------------------------------------------");
	}
}
