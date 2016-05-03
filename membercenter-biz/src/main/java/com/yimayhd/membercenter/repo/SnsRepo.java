package com.yimayhd.membercenter.repo;

import com.yimayhd.membercenter.conventer.TravelKaConverter;
import com.yimayhd.membercenter.entity.TravelKaClub;
import com.yimayhd.snscenter.client.dto.UserInfoDTO;
import com.yimayhd.snscenter.client.result.BaseResult;
import com.yimayhd.snscenter.client.result.ClubInfoListDTO;
import com.yimayhd.snscenter.client.service.SnsCenterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/12/2.
 */
public class SnsRepo {
//    private static final Logger logger = LoggerFactory.getLogger("SnsRepo");

    @Autowired
    private SnsCenterService snsCenterService;

//    public TravelKaClub getTravelKaClub(long userId) {
//        TravelKaClub travelKaClub = new TravelKaClub();
//        if(userId<=0){
//            return null;
//        }
//        BaseResult<ClubInfoListDTO> baseResult = snsCenterService.getUserClubByUserId(userId);
//
//        if (baseResult == null || baseResult.getValue() == null ){
//            return null;
//        }
//        ClubInfoListDTO clubInfoListDTO = baseResult.getValue();
//        travelKaClub = TravelKaConverter.convertTravelKaClub(clubInfoListDTO);
//        return travelKaClub;
//    }
    
    public TravelKaClub getTravelKaClub(long userId,int domainId) {
        TravelKaClub travelKaClub = new TravelKaClub();
        if(userId<=0){
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(userId);
        userInfoDTO.setDomainId(domainId);
        BaseResult<ClubInfoListDTO> baseResult = snsCenterService.getUserClubByUser(userInfoDTO);
        
        if (baseResult == null || baseResult.getValue() == null ){
            return null;
        }
        ClubInfoListDTO clubInfoListDTO = baseResult.getValue();
        travelKaClub = TravelKaConverter.convertTravelKaClub(clubInfoListDTO);
        return travelKaClub;
    }
}
