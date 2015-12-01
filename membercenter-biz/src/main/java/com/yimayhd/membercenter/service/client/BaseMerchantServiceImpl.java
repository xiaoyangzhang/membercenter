package com.yimayhd.membercenter.service.client;

import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.annot.MethodLogger;
import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.BaseMerchantService;
import com.yimayhd.membercenter.manager.BaseMerchantManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/12/1.
 */
public class BaseMerchantServiceImpl implements BaseMerchantService {


    @Autowired
    private BaseMerchantManager baseMerchantManager;

    @Override
    @MethodLogger(isHttpApi = false, isCatchException = false, isPrintArguments = true, isPrintResult = true)
    public MemResult<BaseMerchantDO> getMerchantByUserId(long userId) {
        MemResult<BaseMerchantDO> rs = new MemResult<BaseMerchantDO>();
        if(userId<=0){
            rs.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
            return rs;
        }
        return baseMerchantManager.getBaseMerchantByUserId(userId);
    }
}
