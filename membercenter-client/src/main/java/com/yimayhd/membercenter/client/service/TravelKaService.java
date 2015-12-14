package com.yimayhd.membercenter.client.service;

import com.yimayhd.membercenter.client.domain.TravelKaVO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.client.result.MemPageResult;
import com.yimayhd.membercenter.client.result.MemResult;

/**
 * Created by Administrator on 2015/12/7.
 */
public interface TravelKaService {

    /**
     * 查询大咖详情
     * @param theUserId
     * @return
     */
    public MemResult<TravelKaVO> getTravelKaDetail(long theUserId);

    /**
     * 列表显示大咖
     * @param travelkaPageQuery
     * @return
     */
    public MemPageResult<TravelKaVO> getTravelKaListManagerPage(TravelkaPageQuery travelkaPageQuery);


}
