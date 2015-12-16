package com.yimayhd.membercenter.client.enums;

/**
 * Created by Administrator on 2015/11/14.
 */
public enum AbilityEnum {

    //机场接送，景点讲解，陪游陪玩，找吃找喝，购物达人，摄影达人，英语达人，唱歌跳舞，游泳潜水，卖萌逗趣

    AIRPORT_SHUTTLE("机场接送",1,"xxx/xxxx/xxx.png"),
    SCENEY_DESC("景点讲解",2,"xxx/xxxx/xxx.png"),
    ACCOMPANY_PLAY("陪游陪玩",3,"xxx/xxxx/xxx.png"),
    FIND_EAT_DRINK("陪游陪玩",4,"xxx/xxxx/xxx.png"),
    SHOPPING_DAREN("购物达人",5,"xxx/xxxx/xxx.png"),
    PHOTOGRAPHY_DAREN("摄影达人",6,"xxx/xxxx/xxx.png"),
    ENGLISH_DAREN("英语达人",7,"xxx/xxxx/xxx.png"),
    SING_DANCE("唱歌跳舞",8,"xxx/xxxx/xxx.png"),
    SWIMMING("游泳潜水",9,"xxx/xxxx/xxx.png"),
    ACTING_CUTE("卖萌逗趣",10,"xxx/xxxx/xxx.png");

    private long id;
    private String name;
    private String img;

    AbilityEnum(String name, long id, String img){
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

    public static AbilityEnum getByName(String name){
        if(name ==null){
            return null;
        }
        for(AbilityEnum abilityEnum : values()){
            if (abilityEnum.name.equals(name)){
                return abilityEnum;
            }
        }
        return null;
    }

    public static AbilityEnum getById(long id){
        if(id == 0){
            return null;
        }
        for (AbilityEnum abilityEnum : values()){
            if (abilityEnum.id==id){
                return abilityEnum;
            }
        }

        return null;
    }

    public static void main(String args[]){
        System.out.println(AbilityEnum.ACCOMPANY_PLAY.getImg());
    }






}
