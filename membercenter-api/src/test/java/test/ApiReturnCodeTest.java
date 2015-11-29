package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;

public class ApiReturnCodeTest {
	private static final String[] EXCLUDE_FILEDS = {"serialVersionUID"};
	private static final int min = 16000000;
	private static final int max = 17000000 ;

	@Test
	public void testReturnCode(){
		try{
			
			List<Integer> codes = new ArrayList<Integer>() ;
			
			MemberReturnCode instance = new MemberReturnCode(0, "xx");
			Class<?> clazz = instance.getClass();
			Field[] fs = clazz.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field field = fs[i];
				field.setAccessible(true);

				Class<?> type = field.getType();
				String filedName = field.getName();
				boolean exclude = excule(filedName);
				if( exclude ){
					continue ;
				}
				Object value = field.get(instance);
				if( type.getName().equals("int") ){
					int code = Integer.parseInt(value.toString());
					if( codes.contains(code) ){
						String error = "Code Repeated! code="+code ;
						System.err.println(error);
						Assert.fail(error);
						return ;
					}else if( code < min || code > max ){
						String error = "Code="+code+" not in [ "+min+" , "+ max+")  " ;
						System.err.println(error);
						Assert.fail(error);
						return ;
					}
					codes.add(code) ;
				}
			}
			System.err.println("********************************************");
			System.err.println("finish check！ code is ok");
			System.err.println("********************************************");
			System.err.println("codes:"+JSON.toJSONString(codes));
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	
	
	private boolean excule(String filedName){
		for( String exclude : EXCLUDE_FILEDS ){
			if( !StringUtils.isBlank(exclude) && exclude.equalsIgnoreCase(filedName) ){
				return true;
			}
		}
		return false;
	}
	

}
