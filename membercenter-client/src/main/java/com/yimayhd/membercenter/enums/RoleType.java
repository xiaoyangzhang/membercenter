package com.yimayhd.membercenter.enums;

/**
 * 
 * @author wzf
 *
 */
public enum RoleType {
    
    REGISTER_USER("注册用户", 10),
    TALENT("达人", 20),
    MERCHANT("商户", 30),
    ;
    
    private String name;
    private int type;

    RoleType(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public static RoleType getByType(int type) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.getType() == type) {
                return roleType;
            }
        }
        return null;
    }
}
