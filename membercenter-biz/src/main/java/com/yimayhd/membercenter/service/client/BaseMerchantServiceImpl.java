package com.yimayhd.membercenter.service.client;

import org.springframework.beans.factory.annotation.Autowired;

import com.yiholiday.fhtd.logger.annot.MethodLogger;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.BaseMerchantService;
import com.yimayhd.membercenter.manager.BaseMerchantManager;

/**
 * Created by Administrator on 2015/12/1.
 */
public class BaseMerchantServiceImpl implements BaseMerchantService {


    @Autowired
    private BaseMerchantManager baseMerchantManager;

    @Override
    @MethodLogger(isCatchException = false, isPrintArguments = true, isPrintResult = true)
    public MemResult<BaseMerchantDO> getMerchantByUserId(long userId) {
        MemResult<BaseMerchantDO> rs = new MemResult<BaseMerchantDO>();
        if(userId<=0){
            rs.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return rs;
        }
        return baseMerchantManager.getBaseMerchantByUserId(userId);
    }
}
