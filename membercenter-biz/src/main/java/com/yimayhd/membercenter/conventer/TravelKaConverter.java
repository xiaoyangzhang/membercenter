package com.yimayhd.membercenter.conventer;

import com.yimayhd.membercenter.client.domain.MemberProfileDO;
import com.yimayhd.membercenter.client.domain.UserAbilityRelationDO;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.entity.*;


import com.yimayhd.user.client.domain.UserDO;

import com.yimayhd.user.client.enums.AbilityEnum;
import com.yimayhd.user.entity.UserInfo;
import org.springframework.util.CollectionUtils;


import java.util.*;

/**
 * Created by houdh on 2015/11/26.
 */
public class TravelKaConverter {

    public static TravelKa converntTravelKaDetail( List<UserAbilityRelationDO> userAbilityRelationDOs , MemberProfileDO travelKaDO, UserDO userDO){
        TravelKa travelKa = new TravelKa();
        setAbility(travelKa, userAbilityRelationDOs);
        travelKa.id = travelKaDO.getId();
        travelKa.userId = userDO.getId();
        travelKa.userInfo = convertUserDO2UserInfo(userDO);
        travelKa.serviceContent = travelKaDO.getServiceContent();
        travelKa.backgroundImg = travelKaDO.getBackgroundImg();
        travelKa.isDel = travelKaDO.getIsDel();
        travelKa.gmtCreated = travelKaDO.getGmtCreated().getTime();
        travelKa.gmtModified = travelKaDO.getGmtModified().getTime();
        travelKa.identityValidated = travelKaDO.getIdentityValidated();
        travelKa.mobileValidated = travelKaDO.getMobileValidated();
        travelKa.occupationValidated = travelKaDO.getOccupationValidated();
        return travelKa;
    }

    public static TravelKa converntTravelKaDetail4UserInfo(UserDO userDO){
        TravelKa travelKa = new TravelKa();
        travelKa.userInfo = convertUserDO2UserInfo(userDO);
        return travelKa;
    }

    private static void setAbility(TravelKa travelKa, List<UserAbilityRelationDO> userAbilityRelationDOs){
        List<Ability> list = new ArrayList<Ability>();
        if(userAbilityRelationDOs != null){
            for(UserAbilityRelationDO userAbilityRelationDO :userAbilityRelationDOs){
                Ability ability = new Ability();
                ability.name = AbilityEnum.getById(userAbilityRelationDO.getAbilityId()).getName();
                ability.id = userAbilityRelationDO.getId();
                ability.img =AbilityEnum.getById(userAbilityRelationDO.getAbilityId()).getImg();
                list.add(ability);
            }
            travelKa.abilities = list;
        }
    }

    public static UserInfo convertUserDO2UserInfo(UserDO userDO){
        if(userDO == null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.id = userDO.getId();
        userInfo.avatar = userDO.getAvatar();
        userInfo.nickname = userDO.getNickname();
        userInfo.name = userDO.getName();
        userInfo.gender = String.valueOf(userDO.getGender());
        userInfo.age = getAge(userDO.getBirthday());
        userInfo.provinceCode = userDO.getProvinceCode();
        userInfo.cityCode = userDO.getCityCode();
        userInfo.signature = userDO.getSignature();
        return userInfo;
    }

    /**
     *  1、获取当前时间
     2、判断出生日期是否小于当前时间，如果大于，则引发一场
     3、从当前时间中取出年、月、日；从出生日期中取出年、月、日，年份相减
     4、然后做具体判断
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay){
        int age = 0;
        if(birthDay != null){
            //获取当前系统时间
            Calendar cal = Calendar.getInstance();
            //如果出生日期大于当前时间，则抛出异常
            if (cal.before(birthDay)) {
                throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
            }
            //取出系统当前时间的年、月、日部分
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            //将日期设置为出生日期
            cal.setTime(birthDay);
            //取出出生日期的年、月、日部分
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            //当前年份与出生年份相减，初步计算年龄
            age = yearNow - yearBirth;
            //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
            if (monthNow <= monthBirth) {
                //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) age--;
                }else{
                    age--;
                }
            }
        }
        return age;
    }

    public static TravelkaPageQuery TravelkaPageQuery(PageInfo pageInfo){
        TravelkaPageQuery travelkaPageQuery = new TravelkaPageQuery();
        travelkaPageQuery.setPageNo(pageInfo.pageNo);
        travelkaPageQuery.setPageSize(pageInfo.pageSize);
        return travelkaPageQuery;
    }


    public static TravelKaPageInfoList travelKaPageInfoList(List<MemberProfileDO> userDOList,Map<Long,UserDO> userDOMap, int pageNo, boolean hasNext){
        if (CollectionUtils.isEmpty(userDOList)) {
            return null;
        }
        TravelKaPageInfoList infoList = new TravelKaPageInfoList();
        infoList.pageNo = pageNo;
        infoList.hasNext = hasNext;
        List<TravelKaItem> travelKaItems = new ArrayList<TravelKaItem>();
        for (MemberProfileDO memberProfileDO : userDOList){
            TravelKaItem t = new TravelKaItem();
            t.userId = memberProfileDO.getUserId();
            UserDO userDO = userDOMap.get(t.userId);
            t.gender = userDO.getGender();
            t.avatar = userDO.getAvatar()==null?"":userDO.getAvatar();
            t.nickname = userDO.getNickname();
            t.provinceCode = userDO.getProvinceCode();
            t.cityCode = userDO.getCityCode();
            t.age = getAge(userDO.getBirthday());
            travelKaItems.add(t);
        }
        infoList.travelKaItemPageInfoList = travelKaItems;
        return infoList;
    }


}
