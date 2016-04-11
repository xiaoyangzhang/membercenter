/*
 * FileName: UserOptionAddConsumer.java
 * Author:   liubb
 * Date:     2016年4月11日 上午10:07:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.mq.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.membercenter.client.domain.examine.ExamineDO;
import com.yimayhd.membercenter.client.enums.topic.MemberTopic;
import com.yimayhd.membercenter.client.result.MemResult;
import com.yimayhd.membercenter.converter.ExamineConverter;
import com.yimayhd.membercenter.converter.MerchantConverter;
import com.yimayhd.membercenter.enums.ExamineStatus;
import com.yimayhd.membercenter.enums.ExamineType;
import com.yimayhd.membercenter.mq.BaseConsumer;
import com.yimayhd.membercenter.repo.MerchantRepo;
import com.yimayhd.membercenter.repo.UserOptionRepo;
import com.yimayhd.user.client.domain.MerchantDO;
import com.yimayhd.user.client.dto.MerchantDTO;
import com.yimayhd.user.client.enums.UserOptions;

/**
 * 〈一句话功能简述〉<br>
 * 〈 标记店铺或者达人〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UserOptionAddConsumer extends BaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserOptionAddConsumer.class);

    @Autowired
    MerchantRepo merchantRepo;

    @Autowired
    UserOptionRepo userOptionRepo;

    @Override
    public String getTopic() {
        return MemberTopic.EXAMINE_RESULT.getTopic();
    }

    @Override
    public String getTags() {
        return MemberTopic.EXAMINE_RESULT.getTags();
    }

    @Override
    public boolean doConsumeMessage(Serializable message) {
        String log = "UUID+" + UUID.randomUUID() + "  topic=" + getTopic() + "  msg={}" + JSON.toJSONString(message);
        logger.info(log);
        if (!(message instanceof ExamineDO)) {
            logger.error(log + "   Message not ExamineDO!");
            return true;
        }
        ExamineDO examineDO = (ExamineDO) message;
        // 审核通过保存基本信息
        if (examineDO.getStatues() == ExamineStatus.EXAMIN_OK.getStatus()) {
            MemResult<MerchantDO> merchantResult = merchantRepo.getMerchantById(examineDO.getSellerId());
            if (merchantResult.isSuccess() && null == merchantResult.getValue()) {
                MerchantDO merchantDO = ExamineConverter.examineToMerchant(examineDO);
                // 初始化店铺信息
                MemResult<MerchantDO> memResult = merchantRepo.saveMerchant(merchantDO);
                logger.info("dealExamineInfo param:{} saveMerchant return:{}", JSONObject.toJSONString(merchantDO),
                        JSONObject.toJSONString(memResult.getReturnCode()));
            }
            // 更新user option
            // FIXME 刘彬彬 这是一个跨系统调用，需要保证一定成功的，现在的代码如果调用user失败，数据就会错乱了。
            MemResult<Boolean> result = addUserOption(examineDO.getSellerId(), examineDO.getType());
            return result.isSuccess();
        }
        return true;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈更新达人或者商铺状态〉
     *
     * @param userId
     * @param type
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public MemResult<Boolean> addUserOption(long userId, int type) {
        List<UserOptions> userOptionsList = new ArrayList<UserOptions>();
        // 达人
        if (ExamineType.TALENT.getType() == type) {
            userOptionsList.add(UserOptions.TRAVEL_KA);
            // 达人默认大V
            userOptionsList.add(UserOptions.CERTIFICATED);
        } else {
            userOptionsList.add(UserOptions.COMMERCIAL_TENANT);
        }
        MemResult<Boolean> optionsResult = userOptionRepo.addUserOption(userId, userOptionsList);
        logger.info("addUserOption userId:{}, list.size:{} add return:{}", userId, userOptionsList.size(),
                JSONObject.toJSONString(optionsResult));
        return optionsResult;
    }
}
