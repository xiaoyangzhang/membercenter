package com.yimayhd.membercenter.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.mapper.MemberPrivilegeDOMapper;
import com.yimayhd.membercenter.query.MemPrivilegePageQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-persistance.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class MemPrivilegeTest {

	@Resource
	MemberPrivilegeDOMapper memberPrivilegeDOMapper;
	
	@Test
	public void insert(){
		
		MemberPrivilegeDO record = new MemberPrivilegeDO();
		
		record.setDomainId(1);
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		record.setTitle("aaaaaa");
		
		int insertStatus = memberPrivilegeDOMapper.insert(record );
		
		System.out.println(insertStatus);
	}
	
	@Test
	public void update(){
		MemberPrivilegeDO privilegeDO = memberPrivilegeDOMapper.selectByPrimaryKey(1);
		
		System.out.println(privilegeDO.toString());
		
		privilegeDO.setGmtModified(new Date());
		
		int updateByPrimaryKey = memberPrivilegeDOMapper.updateByPrimaryKey(privilegeDO);
		
		System.out.println("update+" + updateByPrimaryKey);
		
	}

	
	@Test
	public void pageQuery(){
		
		MemPrivilegePageQuery memPrilvilegePageQuery = new MemPrivilegePageQuery();
		
		memPrilvilegePageQuery.setStatus(10);
		
		List<MemberPrivilegeDO> pageQuery = memberPrivilegeDOMapper.pageQuery(memPrilvilegePageQuery );
		
		int queryCount = memberPrivilegeDOMapper.queryCount(memPrilvilegePageQuery);
		
		System.out.println(queryCount+pageQuery.toString());
		
	}
}
