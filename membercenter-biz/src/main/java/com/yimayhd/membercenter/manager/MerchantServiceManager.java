package com.yimayhd.membercenter.manager;


import com.alibaba.dubbo.common.utils.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;


import com.yimayhd.membercenter.client.domain.BaseMerchantDO;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;
import com.yimayhd.membercenter.mapper.BaseMerchantMapper;
import com.yimayhd.membercenter.mapper.WxUserMerchantRelationMapper;
import com.yimayhd.membercenter.repo.UserRepo;
import com.yimayhd.membercenter.service.BussinessException;
import com.yimayhd.user.client.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by root on 15-11-25.
 */
public class MerchantServiceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantServiceManager.class);

    @Autowired
    private BaseMerchantMapper baseMerchantMapper;

    @Autowired
    private WxUserMerchantRelationMapper wxUserMerchantRelationMapper;

    @Autowired
    private UserRepo userRepo;


    public BaseMerchantDO findBaseMerchantDOById(Long id) {
        return baseMerchantMapper.getById(id);
    }

    public Long saveUserMerchantRelation(WxUserMerchantRelationDO wxUserMerchantRelationDO) {
        return wxUserMerchantRelationMapper.insert(wxUserMerchantRelationDO);
    }

    public UserDO findMerchantUserDO(WxUserMerchantRelationDO wxUserMerchantRelationDO) {
        List<WxUserMerchantRelationDO> queryResultList = this.findByCondition(wxUserMerchantRelationDO);

        if (CollectionUtils.isEmpty(queryResultList)) {
            return null;
        }

        if (queryResultList.size() > 1) {
            LOGGER.info("has many WxUserMerchantRelationDO in db for wxUserMerchantRelationDO={}",
                    wxUserMerchantRelationDO);
        }

        WxUserMerchantRelationDO wxUserMerchantRelationResult = queryResultList.get(0);
        Long userId = wxUserMerchantRelationResult.getUserId();
        return userRepo.getUserDOById(userId);
    }


    /**
     * 查询满足条件的WxUserMerchantRelationDO
     * @param wxUserMerchantRelationDO  WxUserMerchantRelationDO
     * @return  List<WxUserMerchantRelationDO>
     */
    public List<WxUserMerchantRelationDO> findByCondition(WxUserMerchantRelationDO wxUserMerchantRelationDO) {
        return wxUserMerchantRelationMapper.findByCondition(wxUserMerchantRelationDO);
    }


    public BaseMerchantDO getBaseMerchantByMerchantUserId(long userId){
        return baseMerchantMapper.getByUserId(userId);
    }
}
