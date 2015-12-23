package com.yimayhd.membercenter.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Frank.zhang
 *        2014-05-29
 *
 */
public class ConfigUtil {
    private ConfigUtil(){

    }
	  public static final Properties PROP;
	    static {
	    	PROP = new Properties();
	    	try {
	    		PROP.load(new FileInputStream(ConfigUtil.class.getResource("/").getPath()+"../config.properties"));
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}
	    }
	    
	    public static String getProperty(String property) {
			PROP.put("api.token.aes", "eqHSs48SCL2VoGsW1lWvDWKQ8Vu71UZJyS7Dbf/e4zo=");
	    	return PROP.getProperty(property);
	    }
}
