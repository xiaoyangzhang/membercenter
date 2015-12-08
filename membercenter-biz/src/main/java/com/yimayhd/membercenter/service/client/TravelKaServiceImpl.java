package com.yimayhd.membercenter.service.client;

import com.yimayhd.membercenter.client.domain.TravelKaVO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.TravelKaService;
import com.yimayhd.membercenter.manager.MemberProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/12/7.
 */
public class TravelKaServiceImpl implements TravelKaService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelKaServiceImpl.class);

    @Autowired
    private MemberProfileManager memberProfileManager;



    @Override
    public MemResult<TravelKaVO> getTravelKaDetail(long theUserId) {
        memberProfileManager.getTravelKaVODetail(theUserId);
        return null;
    }

    @Override
    public MemPageResult<TravelKaVO> getTravelKaListPage(TravelkaPageQuery travelkaPageQuery) {
        return null;
    }
}
