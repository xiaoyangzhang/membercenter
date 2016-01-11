package com.yimayhd.membercenter.biz;

import com.yimay.integral.client.model.medi.PointDetailDTO;
import com.yimay.integral.client.model.result.point.CountResultDTO;
import com.yimay.integral.client.model.result.point.DetailResultDTO;
import com.yimayhd.membercenter.client.result.MemResult;

public interface MemberPointBiz {
	public MemResult<CountResultDTO> getMemeberTotalPoint(Long userId,Long merchantId);
	
	public MemResult<DetailResultDTO<PointDetailDTO>> getMemberPointDetailsByPage(Long userId,Long merchantId,int pageNumber,int pageSize);
}
