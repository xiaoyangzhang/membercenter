package com.yimayhd.membercenter.manager;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.domain.MemberDurationDO;
import com.yimayhd.membercenter.client.domain.MemberFirehoseDO;
import com.yimayhd.membercenter.client.domain.MemberPrivilegeDO;
import com.yimayhd.membercenter.client.domain.MemberRecordDO;
import com.yimayhd.membercenter.client.dto.MemberBuyDTO;
import com.yimayhd.membercenter.client.enums.MemberPrivilegeStatus;
import com.yimayhd.membercenter.client.enums.MemberRecordOutType;
import com.yimayhd.membercenter.client.enums.MemberStatus;
import com.yimayhd.membercenter.client.query.MemPrivilegePageQuery;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.converter.MemberConverter;
import com.yimayhd.membercenter.converter.PrivilegeConverter;
import com.yimayhd.membercenter.dao.MemberDao;
import com.yimayhd.membercenter.dao.MemberPrivilegeDao;
import com.yimayhd.membercenter.dao.MemberRecordDao;
import com.yimayhd.membercenter.dto.MemberDiscountQueryDTO;
import com.yimayhd.membercenter.dto.MemberItemQueryDTO;
import com.yimayhd.membercenter.entity.PrivilegeInfo;
import com.yimayhd.membercenter.entity.PrivilegeInfoPageList;
import com.yimayhd.membercenter.entity.member.Member;
import com.yimayhd.membercenter.entity.member.MemberDetail;
import com.yimayhd.membercenter.entity.member.MemberPurchauseDetail;
import com.yimayhd.membercenter.entity.member.MemeberDiscount;
import com.yimayhd.membercenter.entity.member.MemeberItem;
import com.yimayhd.membercenter.manager.helper.MemberHelper;
import com.yimayhd.membercenter.manager.helper.MemberRecordHelper;
import com.yimayhd.membercenter.mapper.MemberDurationDOMapper;
import com.yimayhd.membercenter.mapper.MemberFirehoseDOMapper;
import com.yimayhd.membercenter.repo.ItemRepo;
import com.yimayhd.membercenter.repo.OrderRepo;
import com.yimayhd.membercenter.repo.ResourceRepo;
import com.yimayhd.membercenter.util.DateUtil;

/**
 * Created by Administrator on 2015/11/21.
 */

public class MemberManager {
    private final static Logger logger = LoggerFactory.getLogger(MemberManager.class);
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberRecordDao memberRecordDao ;
    @Autowired
    private MemberPrivilegeDao memberPrivilegeDao;
    @Autowired
    private OrderRepo orderRepo ;
    @Autowired
    private ItemRepo itemRepo ;
    @Autowired
    private ResourceRepo resourceRepo ;
    
    
    public MemResult<MemberDO> getMemberById(long id) {
    	MemberDO memberDO = memberDao.selectById(id);
    	MemResult<MemberDO> result = new MemResult<MemberDO>();
    	result.setValue(memberDO);
		return result;
	}
    

    /**
     * 完成会员购买,在数据库中创建或者更新会员记录
     * @return
     */
	public MemResult<Boolean> finishMemberPay(MemberBuyDTO memberBuyDTO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if (memberBuyDTO == null || memberBuyDTO.getBuyerId() <= 0 || StringUtils.isBlank(memberBuyDTO.getOuterId())
				|| MemberRecordOutType.get(memberBuyDTO.getOuterType()) == null || memberBuyDTO.getPeriod() <= 0
				|| memberBuyDTO.getSellerId() <= 0) {
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result ;
		}
		int outerType = memberBuyDTO.getOuterType() ;
		String outerId = memberBuyDTO.getOuterId() ;
		MemberRecordDO memberRecordDO = memberRecordDao.getMemberRecordByOutInfo(outerId, outerType);
		if( memberRecordDO != null ){
			result.setValue(true);
			return result ;
		}
		long userId = memberBuyDTO.getBuyerId() ;
		
		memberRecordDO = MemberRecordHelper.createMemberRecord(memberBuyDTO);
		
		MemberDO memberDO = memberDao.selectByUserId(userId);
		if( memberDO == null ){
			memberDO = MemberHelper.createMember(memberBuyDTO);
		}else{
			int period = memberBuyDTO.getPeriod();
			Calendar calendar = Calendar.getInstance() ;
			calendar.add(Calendar.DATE, period);
			Date start = DateUtil.getDateStart(new Date()) ;
			Date end = DateUtil.getDateEnd(calendar.getTime()) ;
			memberDO.setStartTime(start);
			memberDO.setEndTime(end);
		}
		memberDO.setStatus(MemberStatus.ACTIVE.getStatus());
		
		MemResultSupport createOrUpdateResult = memberDao.createOrUpdateMember(memberDO, memberRecordDO);
		if( createOrUpdateResult == null ||  !createOrUpdateResult.isSuccess() ){
			logger.error("createOrUpdateMember failed!  member={}, memberRecord={}, result={}", JSON.toJSONString(memberDO), JSON.toJSONString(memberRecordDO), JSON.toJSONString(createOrUpdateResult));
			if( createOrUpdateResult == null ){
				result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
			}else{
				result.setReturnCode(createOrUpdateResult.getReturnCode());
			}
		}else{
			result.setValue(true);
		}
		
		return result;
	}
	
	public MemResult<Boolean> overdueMember(long id) {
		MemberDO memberDO = memberDao.selectById(id);
		MemResult<Boolean> result = memberDao.overdueMember(memberDO);
		return result ;
	}
	
	
	public MemResult<MemberDetail> getMemberDetail(long userId){
		MemResult<MemberDetail> result = new MemResult<MemberDetail>();
		MemberDO memberDO = memberDao.selectByUserId(userId);
		if( memberDO == null ){
			result.setReturnCode(MemberReturnCode.MEMBER_NOT_FOUND);
			return result ;
		}
		
		MemberDetail detail = new MemberDetail() ;
		Member member = MemberConverter.do2Member(memberDO);
		detail.member = member ;
		
		
		MemPrivilegePageQuery pageQuery = new MemPrivilegePageQuery() ;
		pageQuery.setStatus(MemberPrivilegeStatus.ONLINE.getStatus());
		List<MemberPrivilegeDO> privilegeDOs = memberPrivilegeDao.pageQuery(pageQuery);
		List<PrivilegeInfo> privilegeInfos = PrivilegeConverter.dos2PrivilegeInfos(privilegeDOs);
		detail.privilegeInfos = privilegeInfos  ;
		
		//FIXME 目前不支持分页，后期完善
		int pageNo = 1 ;
		int pageSize =20 ;
		MemberDiscountQueryDTO memberDiscountQueryDTO = new MemberDiscountQueryDTO() ;
		memberDiscountQueryDTO.setBuyerId(userId);
		memberDiscountQueryDTO.setPageNo(pageNo);
		memberDiscountQueryDTO.setPageSize(pageSize);
		MemResult<List<MemeberDiscount>> discountResult = orderRepo.queryMemberDiscounts(memberDiscountQueryDTO);
		if( discountResult != null && discountResult.isSuccess() ){
			detail.memeberDiscounts = discountResult.getValue();
		}
		result.setValue(detail);
		return result ;
	}
	
	public MemResult<MemberPurchauseDetail> getMemberPurchuseDetail(long userId){
		MemResult<MemberPurchauseDetail> result = new MemResult<MemberPurchauseDetail>();
		MemberPurchauseDetail detail = new MemberPurchauseDetail() ;
		
		List<Long> itemIds = resourceRepo.getMemberItemIds();
		if( CollectionUtils.isEmpty(itemIds) ){
			logger.error("getMemberItemIds no itemId return!");
		}else{
			MemberItemQueryDTO memberItemQueryDTO = new MemberItemQueryDTO() ;
			memberItemQueryDTO.setItemIds(itemIds);
			MemResult<List<MemeberItem>> itemResult = itemRepo.queryMemberItems(memberItemQueryDTO);
			if( itemResult != null && itemResult.isSuccess() ){
				detail.memeberItems = itemResult.getValue();
			}
		}
		//FIXME 目前不支持分页，后期完善
		int pageNo = 1 ;
		int pageSize =20 ;
		MemPrivilegePageQuery pageQuery = new MemPrivilegePageQuery() ;
		pageQuery.setStatus(MemberPrivilegeStatus.ONLINE.getStatus());
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		List<MemberPrivilegeDO> privilegeDOs = memberPrivilegeDao.pageQuery(pageQuery);
		List<PrivilegeInfo> privilegeInfos = PrivilegeConverter.dos2PrivilegeInfos(privilegeDOs);
		detail.privilegeInfos = privilegeInfos  ;
		
		
		result.setValue(detail);
		return result;
	}

}
