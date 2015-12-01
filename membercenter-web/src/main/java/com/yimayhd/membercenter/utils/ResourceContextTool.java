package com.yimayhd.membercenter.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceContextTool {
	public static final String DEFAULT_CONFIG_PATH = "velocityTool.properties";
	public static final String STATIC_RESOURCE_PATH_PROP = "static.resource.path";
	public static final String CONTEXT_PATH = "context.path";
	
	private static Properties prop  = new Properties();
	
	static{
		InputStream is = ResourceContextTool.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_PATH);
		try {
			prop.load(is);
		} catch (IOException e) {
			//ignore
		}
	}
	
	public static String getStaticResourcePath(){
		String val = prop.getProperty(STATIC_RESOURCE_PATH_PROP);
		
		return val == null ? "" : val;
	}
	
	public static String getContextPath(){
		String val = prop.getProperty(CONTEXT_PATH);
		return val == null ? "" : val;
	}
	
	public static void main(String [] args){
		System.out.println(ResourceContextTool.getStaticResourcePath());
	}
}
