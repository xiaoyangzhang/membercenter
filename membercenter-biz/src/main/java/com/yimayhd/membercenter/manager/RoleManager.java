package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.dao.RoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xushubing on 2016/6/8.
 */
public class RoleManager {
    private final static Logger logger = LoggerFactory.getLogger(RoleManager.class);
    @Autowired
    private RoleDao roleDao;
}
