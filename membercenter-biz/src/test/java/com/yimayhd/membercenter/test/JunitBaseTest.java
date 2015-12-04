package com.yimayhd.membercenter.test;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类描述：junit测试基础类，引入Spring配置文件，以便注入。
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class JunitBaseTest {
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

    public static void  printStr(Object object) {
        try {
            System.out.println("========================================\nbegin");
            if (object != null) {
                System.out.println(JSON.toJSONString(object));
            } else {
                System.out.println("object is null! ");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("end\n========================================");

        }
    }
}

