package com.yimayhd.membercenter.biz.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.yimay.integral.client.enums.PointType;
import com.yimay.integral.client.model.medi.PointDetailDTO;
import com.yimay.integral.client.model.param.point.CountReqDTO;
import com.yimay.integral.client.model.param.point.DetailReqDTO;
import com.yimay.integral.client.model.result.BaseResult;
import com.yimay.integral.client.model.result.point.CountResultDTO;
import com.yimay.integral.client.model.result.point.DetailResultDTO;
import com.yimay.integral.client.service.PointService;
import com.yimayhd.membercenter.biz.MemberPointBiz;
import com.yimayhd.membercenter.biz.MemberUserBiz;
import com.yimayhd.membercenter.client.domain.WxUserMerchantRelationDO;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.service.MerchantService;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.service.UserService;
import com.yimayhd.user.session.manager.SessionManager;

public class MemberPointBizImpl implements MemberPointBiz{
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberPointBizImpl.class);
	@Resource
	private SessionManager sessionManager;
	@Resource
	private UserService userService;
	@Resource
	private MerchantService merchantService;
	@Resource
	private PointService pointService;
	@Resource
	private MemberUserBiz memberUserBiz;
	
	
	@Override
	public MemResult<CountResultDTO> getMemeberTotalPoint(Long userId,Long merchantId) {
		MemResult<CountResultDTO>  result = new MemResult<CountResultDTO>();
		
		UserDO userDO = sessionManager.getUser();
		LOGGER.debug("userDO={}",userDO);
		if(userDO == null){
			LOGGER.error("userDO is null");
			
			result.setSuccess(false);
			result.setErrorMsg("userDO is null");
			
			return result;
		}

		if(merchantId == null){
			MemResult<WxUserMerchantRelationDO> wxResult = merchantService.findWxUserRelationByUserId(userId);
			if(wxResult.isSuccess() == false){
				LOGGER.error("error during merchantService.findWxUserRelationByUserId,wxResult={}",JSONObject.toJSONString(wxResult));
				result.setSuccess(false);
				result.setErrorCode(wxResult.getErrorCode());
				result.setErrorMsg(wxResult.getErrorMsg());
				return result;
			}
			
			merchantId = wxResult.getValue().getMerchantUserId();
		}
		
		// 获取可用总积分
		CountReqDTO pointQueryRequestDTO = new CountReqDTO();
		pointQueryRequestDTO.setMemberId(userId);
		pointQueryRequestDTO.setVendorId(merchantId);
		pointQueryRequestDTO.setIntegralType(PointType.POINT.getType());
		// 查询积分
		BaseResult<CountResultDTO> queryresult = pointService.queryMemberPoint(pointQueryRequestDTO);
		result.setSuccess(queryresult.isSuccess());
		result.setErrorMsg(queryresult.getResultMsg());
		result.setErrorCode(result.getErrorCode());
		result.setValue(queryresult.getValue());
		
		return result;
	}

	@Override
	public MemResult<DetailResultDTO<PointDetailDTO>> getMemberPointDetailsByPage(Long userId,Long merchantId,int pageNumber,int pageSize) {
		LOGGER.debug("pageNumber={},pageSize={}",pageNumber,pageSize);
		
		MemResult<DetailResultDTO<PointDetailDTO>> result = new MemResult<DetailResultDTO<PointDetailDTO>>();
		UserDO userDO = sessionManager.getUser();
		LOGGER.debug("userDO={}",userDO);
		if(userDO == null){
			LOGGER.error("userDO is null");
			
			result.setSuccess(false);
			result.setErrorMsg("userDO is null");
			
			return result;
		}

		if(merchantId == null){
			MemResult<WxUserMerchantRelationDO> wxResult = merchantService.findWxUserRelationByUserId(userId);
			if(wxResult.isSuccess() == false){
				LOGGER.error("error during merchantService.findWxUserRelationByUserId,wxResult={}",JSONObject.toJSONString(wxResult));
				result.setSuccess(false);
				result.setErrorCode(wxResult.getErrorCode());
				result.setErrorMsg(wxResult.getErrorMsg());
				return result;
			}
			
			merchantId = wxResult.getValue().getMerchantUserId();
		}
		
		// 获取可用总积分
		DetailReqDTO detailReqDTO = new DetailReqDTO();
		
		detailReqDTO.setMemberId(userId);
		detailReqDTO.setVendorId(merchantId);
		detailReqDTO.setIntegralType(PointType.POINT.getType());
		detailReqDTO.setPageNo(pageNumber);
		detailReqDTO.setPageSize(pageSize);
		
		BaseResult<DetailResultDTO<PointDetailDTO>>  detailResult = pointService.queryPointDetails(detailReqDTO);
		result.setSuccess(detailResult.isSuccess());
		result.setErrorMsg(detailResult.getResultMsg());
		
		return result;
	}
}
