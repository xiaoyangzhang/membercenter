package com.yimayhd.membercenter.mapper;


import com.yimayhd.membercenter.client.domain.TerminalDeviceDO;

public interface TerminalDeviceDOMapper {

    int deleteByPrimaryKey(long id);

    int insert(TerminalDeviceDO record);

    TerminalDeviceDO selectByPrimaryKey(long id);

    TerminalDeviceDO getByDeviceCode(String deviceCode);

    int updateByPrimaryKeySelective(TerminalDeviceDO record);

    int updateByPrimaryKey(TerminalDeviceDO record);
}