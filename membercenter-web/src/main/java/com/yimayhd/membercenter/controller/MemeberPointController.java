
package com.yimayhd.membercenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yimay.integral.client.model.medi.PointDetailDTO;
import com.yimay.integral.client.model.result.point.CountResultDTO;
import com.yimay.integral.client.model.result.point.DetailResultDTO;
import com.yimayhd.membercenter.Constants;
import com.yimayhd.membercenter.Converter;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.biz.MemberPointBiz;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.utils.TimeElapseCaculate;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.PointDetailVO;

/**
 * 用户积分相关
 *
 */
@RestController
public class MemeberPointController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemeberPointController.class);
	private static final String TIME_ELAPSE_HEAD=Constants.TIME_ELAPSE_HEAD;
	
	@Resource
	private MemberPointBiz memberPointBiz;

	/**
	 * @Description获取会员总积分
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/point/memeberTotalPoint")
	public Response getMemeberTotalPoint(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:{}",JSON.toJSONString(memeberInfo));
	
		//查询积分
		MemResult<CountResultDTO> result = memberPointBiz.getMemeberTotalPoint();
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " sendPhoneVerifyCode:{}ms" ,TimeElapseCaculate.endSnapshort());
		}
		
		LOGGER.debug("countResultDTO:{}",JSON.toJSONString(result));
		
		// 查询出用户总积分
		if(!result.isSuccess()){
			LOGGER.error("error happen in pointService.queryMemberPoint,result={},memeberInfo={}",JSON.toJSONString(result),JSON.toJSONString(memeberInfo));
			return new Response().failure(result.getErrorMsg(),result.getErrorCode() + "");
		}
		
		Map<String,Object> viewMap = new HashMap<String,Object>();
		Long totalPoint = result.getValue().getRemainPoint();
		viewMap.put("totalPoint", totalPoint);
		
		return new Response().success(viewMap);
	}
	
	/**
	 * @Description分页获取会员积分信息
	 * @param memeberInfo
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/point/memberPointDetails")
	public Response getMemberPointDetailsByPage(MemeberBasicInfoVO memeberInfo, Integer pageNumber,Integer pageSize) {
		LOGGER.debug("memeberInfo:{}" ,JSON.toJSONString(memeberInfo));
		LOGGER.debug("pageNumber:{}", pageNumber);
		LOGGER.debug("pageSize:{}", pageSize);
	
		MemResult<DetailResultDTO<PointDetailDTO>>  detailResult = memberPointBiz.getMemberPointDetailsByPage(pageNumber,pageSize);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " queryPointChangeDetails:{}ms",TimeElapseCaculate.endSnapshort());
		}
		
		LOGGER.debug("detailResult:{}",detailResult);
		String message = "";
		if(!detailResult.isSuccess()){
			LOGGER.error("error happen in pointService.queryPointDetails,detailResult={},memeberInfo={},pageNumber={},pageSize={}",JSON.toJSONString(detailResult),JSON.toJSONString(memeberInfo),pageNumber,pageSize);
			message = "错误编码:" + detailResult.getErrorCode();
			return new Response().failure(message,detailResult.getErrorCode() + "");
		}
		
		List<PointDetailVO> pointDetailList =	Converter.convertToPointDetailVO(detailResult.getValue());
		Map<String,Object> viewMap = new HashMap<String,Object>();
		viewMap.put("pointDetails", pointDetailList);
		int totalPage = 0;
		if(detailResult.getValue() != null ){
			if(detailResult.getValue().getTotalCount() % pageSize != 0){
				totalPage = (detailResult.getValue().getTotalCount()/ pageSize) + 1;
			}else {
				totalPage = detailResult.getValue().getTotalCount() / pageSize;
			}
		}
		
		
		viewMap.put("totalPage", totalPage);
		
		return new Response().success(viewMap);
		
	}

	/**
	 * 
	 * @Title          toPointDetailsView 
	 * @Description    跳转到积分明细页面
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/point/toPointDetails")
	public ModelAndView toPointDetailsView(MemeberBasicInfoVO memeberInfo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("point/pointDetails");
		mv.addObject("phone",memeberInfo);
		return mv;
	}

}
