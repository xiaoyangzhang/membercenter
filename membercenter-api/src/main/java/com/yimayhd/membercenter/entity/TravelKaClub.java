package com.yimayhd.membercenter.entity;


import net.pocrd.annotation.Description;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
@Description("大咖俱乐部信息")
public class TravelKaClub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Description("是否是部长 0-否 1-是")
    public int isMinister; // 是否是 部长 0-否 1-是

    @Description("直播数")
    public int liveCount; // 直播数

    @Description("动态数")
    public int informationsCount; // 动态数

    @Description("俱乐部集合")
    public List<KaClub> kaClubs; //俱乐部


}
