package test;


import com.yimayhd.membercenter.api.TravelKaApi;

import net.pocrd.core.ApiDocumentationHelper;
import net.pocrd.core.ApiManager;
import net.pocrd.define.ApiOpenState;
import net.pocrd.entity.ApiMethodInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ApiDefinitionTest {

    @Test
    public void testApi() {
        try {
            ApiManager manager = new ApiManager();
            ApiDocumentationHelper apiDoc = new ApiDocumentationHelper();
            List<ApiMethodInfo> apis = ApiManager.parseApi(TravelKaApi.class, new Object());
            apiDoc.getDocument(apis.toArray(new ApiMethodInfo[apis.size()]));
            manager.register(apis, ApiOpenState.OPEN_TO_CLIENT);
            System.out.println(apis.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Assert.fail("parse api error.");
        }
    }

}
