package com.yimayhd.membercenter.errorcode;

import net.pocrd.entity.AbstractReturnCode;

/**
 * Created by Administrator on 2015/11/25.
 */
public class TravelKaApiCode extends AbstractReturnCode {
// 16000000 ~ 17000000
    protected TravelKaApiCode(int code, AbstractReturnCode display){
        super(code, display);
    }

    protected TravelKaApiCode(String desc, int code){
        super(desc, code);
    }

    public final static int        C_INTERNAL_SERVER_ERROR = 16001000;
    public final static AbstractReturnCode INTERNAL_SERVER_ERROR   = new TravelKaApiCode( "服务器内部错误", C_INTERNAL_SERVER_ERROR);



}
