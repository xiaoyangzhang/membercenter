package com.yimayhd.membercenter.dao;

import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.mapper.BaseMerchantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/12/1.
 */
public class BaseMerchartDao {

    private static final Logger logger = LoggerFactory.getLogger("BaseMerchartDao");

    @Autowired
    private BaseMerchantMapper baseMerchantMapper;

    public BaseMerchantDO selectByUserId(long userId) {
        return baseMerchantMapper.getByUserId(userId);
    }

}
