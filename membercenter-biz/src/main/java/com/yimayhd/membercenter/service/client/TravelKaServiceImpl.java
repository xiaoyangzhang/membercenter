package com.yimayhd.membercenter.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.fhtd.logger.annot.MethodLogger;
import com.yimayhd.membercenter.client.domain.TravelKaVO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.TravelKaService;
import com.yimayhd.membercenter.manager.MemberProfileManager;

/**
 * Created by Administrator on 2015/12/7.
 */
public class TravelKaServiceImpl implements TravelKaService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelKaServiceImpl.class);

    @Autowired
    private MemberProfileManager memberProfileManager;



    @Override
    @MethodLogger(isPrintArguments = true, isPrintResult = true)
    public MemResult<TravelKaVO> getTravelKaDetail(long theUserId) {
        MemResult<TravelKaVO> result = new MemResult<TravelKaVO>() ;
        result = memberProfileManager.getTravelKaVODetail(theUserId);
        return result;
    }

    @Override
    @MethodLogger(isPrintArguments = true, isPrintResult = false)
    public MemPageResult<TravelKaVO> getTravelKaListManagerPage(TravelkaPageQuery travelkaPageQuery) {
        return  memberProfileManager.getTravelKaListManagerPage(travelkaPageQuery);
    }
}
