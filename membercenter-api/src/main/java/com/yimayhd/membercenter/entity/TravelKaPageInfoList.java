package com.yimayhd.membercenter.entity;


import net.pocrd.annotation.Description;

import java.io.Serializable;
import java.util.List;

/**
 * 旅游咖列表
 * Created by Administrator on 2015/11/14.
 */
@Description("旅游咖列表")
public class TravelKaPageInfoList implements Serializable {

    @Override
    public String toString() {
        return "TravelKaPageInfoList [pageNo=" + pageNo + ", hasNext=" + hasNext
                + ", travelKaItemPageInfoList=" + travelKaItemPageInfoList + "]";
    }

    private static final long serialVersionUID = -5881984604188418160L;

    @Description("当前页码")
    public int pageNo;

    @Description("是否有下一页")
    public boolean hasNext;

    @Description("活动列表")
    public List<TravelKaItem> travelKaItemPageInfoList;
}
