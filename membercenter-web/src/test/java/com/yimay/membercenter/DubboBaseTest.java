package com.yimay.membercenter;


import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/META-INF/spring/application-annot.xml",
        "file:src/main/webapp/META-INF/spring/application-biz.xml",
        "file:src/main/webapp/META-INF/spring/application-common.xml",
        "file:src/main/webapp/META-INF/spring/application-consumer.xml",
        "file:src/main/webapp/META-INF/spring/application-export-api.xml",
        "file:src/main/webapp/META-INF/spring/application-export-client.xml",
        "file:src/main/webapp/META-INF/spring/application-manager.xml",
        "file:src/main/webapp/META-INF/spring/application-mq.xml",
        "file:src/main/webapp/META-INF/spring/application-persistance.xml",
        "file:src/main/webapp/META-INF/spring/application-repo.xml",
        "file:src/main/webapp/META-INF/spring/application-service.xml",
        "file:src/main/webapp/META-INF/spring/application-session.xml"
})
public class DubboBaseTest {
    public void printResult(Object result, String method) {
        String json = JSON.toJSONString(result);
        System.err.println("**************************   " + method + " start");
        System.err.println(json);
        System.err.println("**************************   " + method + " end\n\n");
    }
}
