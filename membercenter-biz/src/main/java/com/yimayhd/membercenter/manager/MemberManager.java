package com.yimayhd.membercenter.manager;


import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Member;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.domain.MemberDurationDO;
import com.yimayhd.membercenter.client.domain.MemberFirehoseDO;
import com.yimayhd.membercenter.client.domain.MemberRecordDO;
import com.yimayhd.membercenter.client.dto.MemberBuyDTO;
import com.yimayhd.membercenter.client.enums.MemberRecordOutType;
import com.yimayhd.membercenter.client.enums.MemberStatus;
import com.yimayhd.membercenter.client.enums.MemberType;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.dao.MemberDao;
import com.yimayhd.membercenter.dao.MemberRecordDao;
import com.yimayhd.membercenter.manager.helper.MemberHelper;
import com.yimayhd.membercenter.manager.helper.MemberRecordHelper;
import com.yimayhd.membercenter.mapper.MemberDurationDOMapper;
import com.yimayhd.membercenter.mapper.MemberFirehoseDOMapper;

/**
 * Created by Administrator on 2015/11/21.
 */

public class MemberManager {
    private final static Logger log = LoggerFactory.getLogger(MemberManager.class);
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberRecordDao memberRecordDao ;
    
    @Autowired
    private MemberDurationDOMapper memberDurationDOMapper;

    @Autowired
    private MemberFirehoseDOMapper memberFirehoseDOMapper;
    
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
			memberDO.setStartTime(new Date());
			memberDO.setEndTime(calendar.getTime());
		}
		memberDO.setStatus(MemberStatus.ACTIVE.getStatus());
		
		MemResultSupport createOrUpdateResult = memberDao.createOrUpdateMember(memberDO, memberRecordDO);
		if( createOrUpdateResult == null ||  !createOrUpdateResult.isSuccess() ){
			log.error("createOrUpdateMember failed!  member={}, memberRecord={}, result={}", JSON.toJSONString(memberDO), JSON.toJSONString(memberRecordDO), JSON.toJSONString(createOrUpdateResult));
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
	
	

	@Deprecated
    public MemberDurationDO AddMemberDuration(MemberDurationDO memberDurationDO){
        if(memberDurationDO == null){
            return null;
        }
        try {
            memberDurationDO = memberDurationDOMapper.insert(memberDurationDO);
        } catch (Exception e) {
            log.error(" MemberManager method AddMemberDuration error ",e);
        }
        return memberDurationDO;
    }
	
	@Deprecated
    public MemberFirehoseDO AddMemberFirehose(MemberFirehoseDO memberFirehoseDO){
    	
        if(memberFirehoseDO == null){
            return null;
        }
        try {
            memberFirehoseDO = memberFirehoseDOMapper.insert(memberFirehoseDO);
        } catch (Exception e) {
            log.error(" MemberManager method AddMemberFirehose error ",e);
        }
        
        return memberFirehoseDO;
    }


}
