package test;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.yimayhd.membercenter.api.MemberApi;
import com.yimayhd.membercenter.api.MerchantApi;
import com.yimayhd.membercenter.api.PrivilegeApi;
import com.yimayhd.membercenter.api.TravelKaApi;
import com.yimayhd.membercenter.api.talent.TalentMemberApi;

import net.pocrd.core.ApiDocumentationHelper;
import net.pocrd.core.ApiManager;
import net.pocrd.define.ApiOpenState;
import net.pocrd.entity.ApiMethodInfo;

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
            
            apis = ApiManager.parseApi(MemberApi.class, new Object());
            apiDoc.getDocument(apis.toArray(new ApiMethodInfo[apis.size()]));
            manager.register(apis, ApiOpenState.OPEN_TO_CLIENT);
            System.out.println(apis.size());
            
            apis = ApiManager.parseApi(PrivilegeApi.class, new Object());
            apiDoc.getDocument(apis.toArray(new ApiMethodInfo[apis.size()]));
            manager.register(apis, ApiOpenState.OPEN_TO_CLIENT);
            System.out.println(apis.size());
            
            
            apis = ApiManager.parseApi(TalentMemberApi.class, new Object());
            apiDoc.getDocument(apis.toArray(new ApiMethodInfo[apis.size()]));
            manager.register(apis, ApiOpenState.OPEN_TO_CLIENT);
            System.out.println(apis.size());
            apis = ApiManager.parseApi(MerchantApi.class, new Object());
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
