package com.yimayhd.membercenter.errorcode;

import net.pocrd.entity.AbstractReturnCode;

public class PrivilegeApiCode extends AbstractReturnCode{

	
	 protected PrivilegeApiCode(int code, AbstractReturnCode display){
	        super(code, display);
	    }

    protected PrivilegeApiCode(String desc, int code){
        super(desc, code);
    }

    public final static int        SERVER_ERROR_C = 16000500;
    public final static AbstractReturnCode SERVER_ERROR   = new TravelKaApiCode( "服务器出错", SERVER_ERROR_C);

}
