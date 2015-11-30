package com.yimayhd.membercenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yimay.integral.client.enums.PointHandleStep;
import com.yimay.integral.client.model.medi.PointDetailDTO;
import com.yimay.integral.client.model.result.point.DetailResultDTO;
import com.yimayhd.membercenter.vo.PointDetailVO;
import com.yimayhd.membercenter.vo.UserVO;
import com.yimayhd.user.client.domain.UserDO;

public class Converter {
	private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);
	
	/**
	 * 转换PointDetailDTO 为 页面可直接使用的PointDetailVO
	 * @param dtoList
	 * @return
	 */
	public static  List<PointDetailVO> convertToPointDetailVO(DetailResultDTO<PointDetailDTO>  dtoResult){
		List<PointDetailVO> voList = new ArrayList<PointDetailVO>();
		if(dtoResult != null && dtoResult.getList().size() > 0){
			for(PointDetailDTO dto : dtoResult.getList()){
				PointDetailVO vo = new PointDetailVO();
				vo.setCreateDate(dto.getRecordTime());
				vo.setEndDate(dto.getEndDate());
				vo.setPoint(dto.getPoint() + "");
				vo.setType("");
				if(dto.getType() == PointHandleStep.ADD_POINT.getType() || dto.getType() == PointHandleStep.RETURN_POINT.getType()){
					vo.setType("+");
				}else if(dto.getType() == PointHandleStep.EXPENSE_POINT.getType()){
					vo.setType("-");
				}	
				//vo.setTransId(dto.getFromId() + "");
				//设置商户名
				vo.setSource(dtoResult.getMemberName());
				voList.add(vo);
				
			}
		}
		
		return voList;
	}
	
	public static UserDO contertToUserDO(UserVO userVO){
		UserDO userDO = new UserDO();
		userDO.setId(userVO.getUserId());
		userDO.setName(userVO.getName());
		userDO.setCityCode(userVO.getCityCode());
		userDO.setProvinceCode(userVO.getProvinceCode());
		userDO.setMobile(userVO.getPhone());
		String birthStr = userVO.getBirthYear() + "-" + userVO.getBirthMonth();

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		try {
			Date birthDate  = sdf.parse(birthStr);
			userDO.setBirthday(birthDate);
		} catch (ParseException e) {
			LOGGER.error("contertToUserDO" , e);
		} 
		
		return userDO;
	}
}
