package com.yimayhd.membercenter.client.enums;

/**
 * Created by Administrator on 2015/12/22.
 */
public enum AbilityTestEnum {

    AIRPORT_SHUTTLE("机场接送",1,"L1xzCTByVT1R4cSCrK.png"),
    SCENEY_DESC("景点讲解",2,"L16zCTByCT1R4cSCrK.png"),
    ACCOMPANY_PLAY("陪游陪玩",3,"L1_FYTBydT1R4cSCrK.png"),
    FIND_EAT_DRINK("找吃找喝",4,"L1LXCTByYT1R4cSCrK.png"),
    SHOPPING_DAREN("购物达人",5,"L1fNCTByKT1R4cSCrK.png"),
    PHOTOGRAPHY_DAREN("摄影达人",6,"L1kRYTBy_T1R4cSCrK.png"),
    ENGLISH_DAREN("英语达人",7,"L1ZXCTByCT1R4cSCrK.png"),
    SING_DANCE("唱歌跳舞",8,"L1mXYTByKT1R4cSCrK.png"),
    SWIMMING("游泳潜水",9,"L1SNCTByKT1R4cSCrK.png"),
    ACTING_CUTE("卖萌逗趣",10,"L1oNCTByCT1R4cSCrK.png");


    private long id;
    private String name;
    private String img;

    AbilityTestEnum(String name, long id, String img){
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getImg(){ return img; }

    public static AbilityTestEnum getByName(String name){
        if(name ==null){
            return null;
        }
        for(AbilityTestEnum abilityEnum : values()){
            if (abilityEnum.name.equals(name)){
                return abilityEnum;
            }
        }
        return null;
    }

    public static AbilityTestEnum getById(long id){
        if(id == 0){
            return null;
        }
        for (AbilityTestEnum abilityEnum : values()){
            if (abilityEnum.id==id){
                return abilityEnum;
            }
        }

        return null;
    }
}
