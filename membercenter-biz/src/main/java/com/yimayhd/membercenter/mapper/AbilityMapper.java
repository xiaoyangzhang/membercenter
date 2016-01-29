package com.yimayhd.membercenter.mapper;

import java.util.List;

import com.yimayhd.membercenter.client.domain.AbilityDO;

public interface AbilityMapper {
	public AbilityDO getById(long id);

	public List<AbilityDO> getAll();
}
