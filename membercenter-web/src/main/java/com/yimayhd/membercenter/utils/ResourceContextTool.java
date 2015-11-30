package com.yimayhd.membercenter.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceContextTool {
	public static final String DEFAULT_CONFIG_PATH = "velocityTool.properties";
	public static final String RESOURCE_PATH_PROP = "resource.path";
	private static Properties prop  = new Properties();
	
	static{
		InputStream is = ResourceContextTool.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_PATH);
		try {
			prop.load(is);
		} catch (IOException e) {
			//ignore
		}
	}
	
	public static String getResourcePath(){
		String val = prop.getProperty(RESOURCE_PATH_PROP);
		
		return val == null ? "" : val;
	}
	
	public static void main(String [] args){
		System.out.println(ResourceContextTool.getResourcePath());
	}
}
