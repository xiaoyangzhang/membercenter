package com.yimayhd.membercenter.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//<import resource="../META-INF/spring/application-persistance.xml" />
//<import resource="../META-INF/spring/application-manager.xml" />
//<import resource="../META-INF/spring/application-service.xml" />
//<import resource="../META-INF/spring/application-mq.xml" />
//<import resource="../META-INF/spring/application-repo.xml" />
//<import resource="../META-INF/spring/application-common.xml" />
//<import resource="../META-INF/spring/application-consumer.xml" />
//<import resource="../META-INF/spring/application-export-client.xml" />
//<import resource="../META-INF/spring/application-export-api.xml"/>
//<import resource="../META-INF/spring/application-annot.xml" />
//<import resource="../META-INF/spring/application-biz.xml" />
//<import resource="../META-INF/spring/application-session.xml" />


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
		//"classpath:application-export-api.xml",
		"classpath:application-annot.xml","classpath:application-service.xml"
})
public class BaseTest {
	@Test
	public void helloWorldTest(){
		
	}
}
