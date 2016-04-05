package com.yimayhd.membercenter.mapper;

import java.util.List;

import com.yimayhd.membercenter.client.domain.BankDO;

public interface BankDOMapper {

    int insert(BankDO record);
    
    int insertBatch(List<BankDO> list);
    
    List<BankDO> selectBankNameAndId();

}