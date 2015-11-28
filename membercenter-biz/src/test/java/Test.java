import com.yimayhd.membercenter.api.TravelKaApi;
import com.yimayhd.membercenter.entity.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-repo.xml", "classpath:application-manager.xml", "classpath:application-service.xml",
        "classpath:application-consumer.xml","classpath:application-repo.xml","classpath:application-export-api.xml",
        "classpath:application-export-client.xml","classpath:application-annot.xml","classpath:application-common.xml",
        "classpath:application-persistance.xml","classpath:application-mq.xml"})
public class Test {
    @Autowired
    private TravelKaApi travelKaApi;

    @org.junit.Test
    public void getTravelKaDetailTest(){
        TravelKa travelKa = travelKaApi.getTravelKaDetail(1,2,3,4,5,701);
        System.out.println("travelka id :"+travelKa.id);
        System.out.println("travelka backgroundImg :"+travelKa.backgroundImg);
        System.out.println("travelka serviceContent :"+travelKa.serviceContent);
        UserInfo userInfo = travelKa.userInfo;
        System.out.println("userInfo name :"+userInfo.name);
        System.out.println("userInfo nickname :"+userInfo.nickname);
        System.out.println("userInfo age :"+userInfo.age);
        System.out.println("userInfo gender :"+userInfo.gender);
        System.out.println("userInfo avatar :"+userInfo.avatar);
        List<Ability> abilityList = travelKa.abilities;
        System.out.println("abilityList:"+abilityList);
        System.out.println(" is travelKa "+travelKa.isTravelKa);
        System.out.println("1111111111111111");

    }

    @org.junit.Test
    public void TravelKaPageInfoListTest() {
        PageInfo pageInfo = new PageInfo();
        pageInfo.pageNo = 1;
        pageInfo.pageSize = 10;
        TravelKaPageInfoList travelKaPageInfoList = travelKaApi.getTravelKaListPage(1, 2, 3, 4, 5, pageInfo, "POPULARITY");
        System.out.println("list size = "+travelKaPageInfoList.travelKaItemPageInfoList.size());
        System.out.println("2222222");
    }
}
