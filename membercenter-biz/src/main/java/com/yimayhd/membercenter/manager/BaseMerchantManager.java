package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.dao.BaseMerchartDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/12/1.
 */
public class BaseMerchantManager {
    private final static Logger log = LoggerFactory.getLogger(BaseMerchantManager.class);

    @Autowired
    private BaseMerchartDao baseMerchartDao;


    public MemResult<BaseMerchantDO> getBaseMerchantByUserId(long userId){
        MemResult<BaseMerchantDO> result = new MemResult<BaseMerchantDO>();
        BaseMerchantDO baseMerchantDO = baseMerchartDao.selectByUserId(userId);
        if(baseMerchantDO == null){
            result.setReturnCode(MemberReturnCode.MERCHANT_NOT_FOUND_ERROR);
            return result;
        }
        result.setValue(baseMerchantDO);
        return result;
    }
}
