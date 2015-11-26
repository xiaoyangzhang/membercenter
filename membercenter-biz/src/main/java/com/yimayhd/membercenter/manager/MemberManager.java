package com.yimayhd.membercenter.manager;


import com.yimayhd.membercenter.client.domain.MemberDurationDO;
import com.yimayhd.membercenter.client.domain.MemberFirehoseDO;
import com.yimayhd.membercenter.mapper.MemberDurationDOMapper;
import com.yimayhd.membercenter.mapper.MemberFirehoseDOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/11/21.
 */
public class MemberManager {

    private final static Logger log = LoggerFactory.getLogger(MemberManager.class);

    @Autowired
    private MemberDurationDOMapper memberDurationDOMapper;

    @Autowired
    private MemberFirehoseDOMapper memberFirehoseDOMapper;


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
