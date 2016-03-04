package com.yimayhd.membercenter.manager;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.mapper.HaMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by czf on 2016/3/1.
 */
public class UserPermissionManager {

    @Autowired
    private HaMenuMapper haMenuMapper;

    public List<HaMenuDO> getMenuListByUserId(long id) {
        return haMenuMapper.getMenuListByUserId(id);
    }

    public List<HaMenuDO> getUrlListByUserId(long id) {
        return haMenuMapper.getUrlListByUserId(id);
    }

}
