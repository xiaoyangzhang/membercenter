package com.yimayhd.membercenter.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.yimayhd.membercenter.MemberReturnCode;
import com.yimayhd.membercenter.client.domain.MemberDO;
import com.yimayhd.membercenter.client.domain.MemberRecordDO;
import com.yimayhd.membercenter.client.enums.MemberStatus;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.client.result.MemResultSupport;
import com.yimayhd.membercenter.idgen.IDPool;
import com.yimayhd.membercenter.mapper.MemberDOMapper;
import com.yimayhd.membercenter.mq.MsgSender;

public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger("MemberDao");
	
	@Autowired
	private MemberDOMapper memberDOMapper;
	@Autowired
	private MemberRecordDao memberRecordDao;
	@Autowired
	private MsgSender msgSender;
	@Autowired
	private TransactionTemplate transactionTemplate ;
	@Autowired
	private IDPool memberIdpool ;

	public MemberDO insert(MemberDO record){
		if( record == null ){
			return null;
		}
		record.setGmtCreated(new Date());
		record.setGmtModified(new Date());
		int count = memberDOMapper.insert(record);
		if( count == 1 ){
			return record ;
		}
		return null ;
	}

	public MemberDO selectById(long id){
		return memberDOMapper.selectById(id);
	}
	
	public MemberDO selectByUserId(long userId){
		return memberDOMapper.selectByUserId(userId);
	}
	
	public MemResultSupport createOrUpdateMember(final MemberDO memberDO, final MemberRecordDO memberRecordDO){
		MemResultSupport result = new MemResultSupport() ;
		if( memberDO == null || memberRecordDO == null ){
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result ;
		}
		boolean isNewMember = false ;
		if( memberDO.getId() <= 0 ){
			long id = memberIdpool.getNewId();
			memberDO.setId(id);
			isNewMember = true;
		}
		final boolean isNew = isNewMember ;
		
		MemberTopic topic = MemberTopic.MEMBER_TAKE_EFFECT ;
		TransactionSendResult sendResult = msgSender.sendMessage(memberDO, topic.getTopic(), topic.getTags(), new LocalTransactionExecuter() {
			@Override
			public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
				Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {
					@Override
					public Boolean doInTransaction(TransactionStatus status) {
						try {
							MemberDO dbMember = null;
							if( isNew ){
								dbMember = insert(memberDO);
							}else{
								dbMember = update(memberDO);
							}
							if( dbMember == null ){
								status.setRollbackOnly(); 
								return false;
							}
							MemberRecordDO recordInsertResult = memberRecordDao.insert(memberRecordDO);
							if( recordInsertResult == null ){
								status.setRollbackOnly(); 
								return false;
							}
							return true;
						} catch (Exception e) {
							status.setRollbackOnly(); 
							logger.error("db error!  memberDO={}, memberRecordDO={} ", JSON.toJSONString(memberDO), JSON.toJSONString(memberRecordDO), e);
							return false;
						}
						
					}
					
				});
				if( dbResult != null && dbResult ){
					return LocalTransactionState.COMMIT_MESSAGE ;
				}else{
					return LocalTransactionState.ROLLBACK_MESSAGE ;
				}
			}
		} );
		if( sendResult == null || sendResult.getLocalTransactionState() != LocalTransactionState.COMMIT_MESSAGE ){
			logger.error("send msg failed! topic={}, msg={},  result={}", topic, JSON.toJSONString(memberDO), JSON.toJSONString(sendResult));
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}
		return result ;
	}
	
	
	public MemResult<Boolean> overdueMember(final MemberDO memberDO) {
		MemResult<Boolean> result = new MemResult<Boolean>();
		if( memberDO == null ){
			result.setReturnCode(MemberReturnCode.PARAMTER_ERROR);
			return result ;
		}
		memberDO.setStatus(MemberStatus.EXPIRED.getStatus());
		MemberTopic topic = MemberTopic.MEMBER_OVERDUE ;
		TransactionSendResult sendResult = msgSender.sendMessage(memberDO, topic.getTopic(), topic.getTags(), new LocalTransactionExecuter() {
			@Override
			public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
				Boolean dbResult = transactionTemplate.execute(new TransactionCallback<Boolean>() {
					@Override
					public Boolean doInTransaction(TransactionStatus status) {
						try {
							MemberDO updateResult = update(memberDO);
							if( updateResult == null){
								status.setRollbackOnly(); 
								return false;
							}
							return true;
						} catch (Exception e) {
							status.setRollbackOnly(); 
							logger.error("db error!  memberDO={}", JSON.toJSONString(memberDO), e);
							return false;
						}
						
					}
					
				});
				if( dbResult != null && dbResult ){
					return LocalTransactionState.COMMIT_MESSAGE ;
				}else{
					return LocalTransactionState.ROLLBACK_MESSAGE ;
				}
			}
		} );
		if( sendResult == null || sendResult.getLocalTransactionState() != LocalTransactionState.COMMIT_MESSAGE ){
			logger.error("send msg failed! topic={}, msg={},  result={}", topic, JSON.toJSONString(memberDO), JSON.toJSONString(sendResult));
			result.setReturnCode(MemberReturnCode.SYSTEM_ERROR);
		}else{
			result.setValue(true);
		}
		return result ;
	}
	

	public MemberDO update(MemberDO record){
		if( record == null ){
			return null;
		}
		record.setGmtModified(new Date());
		int count = memberDOMapper.update(record);
		if( count == 1 ){
			return record ;
		}
		return null ;
	}
}
