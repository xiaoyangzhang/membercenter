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
import com.yimay.integral.client.enums.PointType;
import com.yimay.integral.client.model.medi.PointDetailDTO;
import com.yimay.integral.client.model.param.point.CountReqDTO;
import com.yimay.integral.client.model.param.point.DetailReqDTO;
import com.yimay.integral.client.model.result.BaseResult;
import com.yimay.integral.client.model.result.point.CountResultDTO;
import com.yimay.integral.client.model.result.point.DetailResultDTO;
import com.yimay.integral.client.service.PointService;
import com.yimayhd.membercenter.Constants;
import com.yimayhd.membercenter.Converter;
import com.yimayhd.membercenter.Response;
import com.yimayhd.membercenter.utils.TimeElapseCaculate;
import com.yimayhd.membercenter.vo.MemeberBasicInfoVO;
import com.yimayhd.membercenter.vo.PointDetailVO;
import com.yimayhd.user.session.manager.SessionUtils;

/**
 * 用户积分相关
 *
 */
@RestController
public class MemeberPointController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemeberPointController.class);
	private static final String TIME_ELAPSE_HEAD=Constants.TIME_ELAPSE_HEAD;
	
	 @Resource
	 private PointService pointService;

	/**
	 * @Description获取会员总积分
	 * @param memeberInfo
	 * @return
	 */
	@RequestMapping(value = "/point/memeberTotalPoint")
	public Response getMemeberTotalPoint(MemeberBasicInfoVO memeberInfo) {
		LOGGER.debug("memeberInfo:{}",JSON.toJSONString(memeberInfo));
		
		MemeberBasicInfoVO sessionInfo = (MemeberBasicInfoVO) SessionUtils.getSession().getAttribute(Constants.MEMBER_USER_INFO);

		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		
		// 获取可用总积分
		CountReqDTO pointQueryRequestDTO = new CountReqDTO();
//		pointQueryRequestDTO.setMemberId(11L);
//		pointQueryRequestDTO.setVendorId(1L);
		pointQueryRequestDTO.setMemberId(sessionInfo.getUserId());
		pointQueryRequestDTO.setVendorId(sessionInfo.getMerchantId());
		pointQueryRequestDTO.setIntegralType(PointType.POINT.getType());
		
		BaseResult<CountResultDTO> result = pointService.queryMemberPoint(pointQueryRequestDTO);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " sendPhoneVerifyCode:{}ms" ,TimeElapseCaculate.endSnapshort());
		}
		
		LOGGER.debug("countResultDTO:{}",JSON.toJSONString(result));
		
		// 查询出用户总积分
		if(!result.isSuccess()){
			LOGGER.error("error happen in pointService.queryMemberPoint,result={},memeberInfo={}",JSON.toJSONString(result),JSON.toJSONString(memeberInfo));
			return new Response().failure(result.getResultMsg(),result.getErrorCode());
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
		
		boolean succeeded = true;
		String message = "";
		
		MemeberBasicInfoVO sessionInfo = (MemeberBasicInfoVO) SessionUtils.getSession().getAttribute(Constants.MEMBER_USER_INFO);
		//FIXME
		if(sessionInfo == null){
			//session失效，
		}
		
		if (!succeeded) {
			return new Response().failure(message);
		}
		
		if(LOGGER.isDebugEnabled()){
			TimeElapseCaculate.startSnapshort();
		}
		// 获取可用总积分
		DetailReqDTO detailReqDTO = new DetailReqDTO();
//		detailReqDTO.setMemberId(11L);
//		detailReqDTO.setVendorId(1L);
//		detailReqDTO.setPageNo(1);
//		detailReqDTO.setPageSize(10);
		
		detailReqDTO.setMemberId(sessionInfo.getUserId());
		detailReqDTO.setVendorId(sessionInfo.getMerchantId());
		detailReqDTO.setIntegralType(PointType.POINT.getType());
		detailReqDTO.setPageNo(pageNumber);
		detailReqDTO.setPageSize(pageSize);
		
		BaseResult<DetailResultDTO<PointDetailDTO>>  detailResult = pointService.queryPointDetails(detailReqDTO);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(TIME_ELAPSE_HEAD + " queryPointChangeDetails:{}ms",TimeElapseCaculate.endSnapshort());
		}
		
		LOGGER.debug("detailResult:{}",detailResult);
		
		if(!detailResult.isSuccess()){
			LOGGER.error("error happen in pointService.queryPointDetails,detailResult={},memeberInfo={},pageNumber={},pageSize={}",JSON.toJSONString(detailResult),JSON.toJSONString(memeberInfo),pageNumber,pageSize);
			message = "错误编码:" + detailResult.getErrorCode();
			return new Response().failure(message,detailResult.getErrorCode());
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
