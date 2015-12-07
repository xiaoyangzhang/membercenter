package com.yimayhd.membercenter.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger("BaseMerchantServiceImpl");

    @Autowired
    private BaseMerchantManager baseMerchantManager;

    @Override
    @MethodLogger(isPrintArguments = true, isPrintResult = true)
    public MemResult<BaseMerchantDO> getMerchantByUserId(long userId) {
    	MemResult<BaseMerchantDO> rs = new MemResult<BaseMerchantDO>();
    	try{
    		if(userId<=0){
    			rs.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
    			return rs;
    		}
    		rs =  baseMerchantManager.getBaseMerchantByUserId(userId);
    	}catch(Exception e){
    		logger.error("getBaseMerchantByUserId failed!  userId={}", userId, e);
    		rs.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
    	}
    	return rs ;
    }
}
