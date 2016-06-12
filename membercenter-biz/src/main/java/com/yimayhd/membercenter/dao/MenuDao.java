package com.yimayhd.membercenter.dao;

import com.yimayhd.membercenter.client.domain.HaMenuDO;
import com.yimayhd.membercenter.client.query.MenuListQuery;
import com.yimayhd.membercenter.mapper.HaMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class MenuDao {
    @Autowired
    private HaMenuMapper haMenuMapper;


    public HaMenuDO insert(HaMenuDO haMenuDO) {
        if (haMenuDO == null) {
            return null;
        }
        Date now = new Date();
        haMenuDO.setGmtCreated(now);
        haMenuDO.setGmtModified(now);
        int result = haMenuMapper.add(haMenuDO);
        if (result == 1) {
            return haMenuDO;
        }
        return null;
    }

    public HaMenuDO update(HaMenuDO haMenuDO) {
        if (haMenuDO == null) {
            return null;
        }
        haMenuDO.setGmtModified(new Date());
        int count = haMenuMapper.modify(haMenuDO);
        if (count == 1) {
            return haMenuDO;
        }
        return null;
    }

    public HaMenuDO getById(long id) {
        return haMenuMapper.getById(id);
    }

    public void delete(long id) {
        haMenuMapper.delete(id);
    }

    public List<HaMenuDO> pageQuery(MenuListQuery menuListQuery) {
        return haMenuMapper.pageQuery(menuListQuery);
    }

    public Integer queryCount(MenuListQuery menuListQuery) {
        return haMenuMapper.queryCount(menuListQuery);
    }
}
