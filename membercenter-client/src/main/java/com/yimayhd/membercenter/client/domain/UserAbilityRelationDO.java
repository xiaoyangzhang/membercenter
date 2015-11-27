package com.yimayhd.membercenter.client.domain;

/**
 * 
 * @table user_ability_relation
 * @author houdh
 **/
public class UserAbilityRelationDO {

    private static final long serialVersionUID = 1L;


    private long id; // 

    private long userId; // 

    private long abilityId; // 


    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setAbilityId(long abilityId){
        this.abilityId = abilityId;
    }

    public long getAbilityId() {
        return abilityId;
    }

}