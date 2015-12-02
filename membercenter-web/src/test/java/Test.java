import java.util.Date;

import com.yimayhd.membercenter.util.DateUtil;

public class Test {

	public static void main(String[] args) {
			Date d = DateUtil.getDateEnd(new Date());
			String str = DateUtil.format(d, DateUtil.DATE_TIME_MS_FORMAT);
			System.err.println(str);
	}

}
