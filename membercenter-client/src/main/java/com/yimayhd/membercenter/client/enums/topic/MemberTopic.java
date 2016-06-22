package com.yimayhd.membercenter.client.enums.topic;

import com.yimayhd.membercenter.client.constant.MemberConstant;

public enum MemberTopic {
	MEMBER_TAKE_EFFECT(MemberConstant.TOPIC_MEMBER, "MEMBER_TAKE_EFFECT", "会员生效"),
	MEMBER_OVERDUE(MemberConstant.TOPIC_MEMBER, "MEMBER_OVERDUE", "会员过期"),
	EXAMINE_RESULT(MemberConstant.TOPIC_MEMBER,"EXAMINE_RESULT","商户审核结果"),
	MERCHANT_ROLE_BIND(MemberConstant.TOPIC_MEMBER,"MERCHANT_ROLE_BIND","商家绑定角色"),
	MERCHANT_UPDATE(MemberConstant.TOPIC_MEMBER,"MERCHANT_UPDATE","更新商家")
	
	;

	private String topic;
	private String tags;
	private String desc;

	private MemberTopic(String topic, String tags, String desc) {
		this.topic = topic;
		this.tags = tags;
		this.desc = desc;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
