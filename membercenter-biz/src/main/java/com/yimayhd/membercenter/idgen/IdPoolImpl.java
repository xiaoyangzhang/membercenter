package com.yimayhd.membercenter.idgen;

import com.yimayhd.idgen.IDGenService;
import com.yimayhd.idgen.client.MemIDPool;

/**
 * 
 * @author wzf
 *
 */
public class IdPoolImpl extends MemIDPool implements IDPool {
    public IdPoolImpl(String configDomain, String configKey, int allocCount, IDGenService generator) {
        super(configDomain, configKey, allocCount, generator);
    }

    @Override
    public Long getNewId() {
		String borrowId=borrow();
		consume(borrowId);
		return Long.valueOf(borrowId);
    }
}
