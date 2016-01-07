package com.yimayhd.membercenter.conventer;

import com.yimayhd.membercenter.client.domain.MemberProfileDO;
import com.yimayhd.membercenter.client.domain.TravelKaVO;
import com.yimayhd.membercenter.client.domain.UserAbilityRelationDO;
import com.yimayhd.membercenter.client.enums.AbilityEnum;
import com.yimayhd.membercenter.client.enums.AbilityTestEnum;
import com.yimayhd.membercenter.client.query.TravelkaPageQuery;
import com.yimayhd.membercenter.entity.*;


import com.yimayhd.membercenter.entity.Ability;
import com.yimayhd.membercenter.entity.KaClub;
import com.yimayhd.membercenter.entity.PageInfo;
import com.yimayhd.membercenter.entity.TravelKa;
import com.yimayhd.membercenter.entity.TravelKaClub;
import com.yimayhd.membercenter.entity.TravelKaItem;
import com.yimayhd.membercenter.entity.TravelKaPageInfoList;
import com.yimayhd.membercenter.entity.UserInfo;
import com.yimayhd.membercenter.util.ConfigUtil;
import com.yimayhd.snscenter.client.result.ClubInfoDTO;
import com.yimayhd.snscenter.client.result.ClubInfoListDTO;
import com.yimayhd.user.client.domain.UserDO;



import com.yimayhd.user.entity.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;


import java.util.*;

/**
 * Created by houdh on 2015/11/26.
 */
public class TravelKaConverter {
    private final static Logger log = LoggerFactory.getLogger(TravelKaConverter.class);

    public static TravelKa converntTravelKaDetail( List<UserAbilityRelationDO> userAbilityRelationDOs , MemberProfileDO travelKaDO, UserDO userDO){
        TravelKa travelKa = new TravelKa();
        String flag = ConfigUtil.getProperty("memcenter.ability.isproduction");
        if(StringUtils.isNotBlank(flag)){
            if(flag.equals("true")){
                setAbility(travelKa, userAbilityRelationDOs);
            }
            if(flag.equals("false")){
                setTestAbility(travelKa, userAbilityRelationDOs);
            }
        } else {
            log.error("  ConfigUtil.getProperty(\"memcenter.ability.isproduction\") is null");
        }

        travelKa.id = travelKaDO.getId();
        travelKa.userId = userDO.getId();
        travelKa.userInfo = convertUserDO2UserInfo(userDO);
        travelKa.serviceContent = travelKaDO.getServiceContent() == null ? null : travelKaDO.getServiceContent();
        travelKa.backgroundImg = travelKaDO.getBackgroundImg() == null ? null : travelKaDO.getBackgroundImg();
        travelKa.isDel = travelKaDO.getIsDel() == null ? null : travelKaDO.getIsDel();
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
        if(userDO.getId() > 0){
            travelKa.userId = userDO.getId();
        }
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

    /** 测试 环境*/
    private static void setTestAbility(TravelKa travelKa, List<UserAbilityRelationDO> userAbilityRelationDOs){
        List<Ability> list = new ArrayList<Ability>();
        if(userAbilityRelationDOs != null){
            for(UserAbilityRelationDO userAbilityRelationDO :userAbilityRelationDOs){
                Ability ability = new Ability();
                ability.name = AbilityTestEnum.getById(userAbilityRelationDO.getAbilityId()).getName();
                ability.id = userAbilityRelationDO.getId();
                ability.img =AbilityTestEnum.getById(userAbilityRelationDO.getAbilityId()).getImg();
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
        userInfo.avatar = userDO.getAvatar() == null ? null : userDO.getAvatar();
        userInfo.nickname = userDO.getNickname() == null ? null : userDO.getNickname();
        userInfo.name = userDO.getName() == null ? null : userDO.getName();
        if(userDO.getGender() != 0){
            userInfo.gender = String.valueOf(userDO.getGender());
        }
        if(userDO.getBirthday() != null){
            userInfo.age = getAge(userDO.getBirthday());
        }

        if(userDO.getProvinceCode() != 0){
            userInfo.provinceCode = userDO.getProvinceCode();
        }

        if(userDO.getCityCode() != 0){
            userInfo.cityCode = userDO.getCityCode();
        }
        userInfo.province = userDO.getProvince() == null ? null : userDO.getProvince();
        userInfo.city = userDO.getCity() == null ? null : userDO.getCity();

        userInfo.signature = userDO.getSignature() == null ? null : userDO.getSignature();
        if(userDO.getBirthday() != null){
            userInfo.birthday = userDO.getBirthday().getTime();
        }
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
        travelkaPageQuery.setHasNextMod(true);
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
            if(userDO.getGender() != 0){
                t.gender = userDO.getGender();
            }

            t.avatar = userDO.getAvatar()==null ? null : userDO.getAvatar();
            t.nickname = userDO.getNickname() == null ? null : userDO.getNickname();
            if(userDO.getProvinceCode() != 0){
                t.provinceCode = userDO.getProvinceCode();
            }
            if( userDO.getCityCode() != 0){
                t.cityCode = userDO.getCityCode();
            }
            if(userDO.getBirthday() != null){
                t.age = getAge(userDO.getBirthday());
            }
            t.province = userDO.getProvince()==null ? null : userDO.getProvince();
            t.city = userDO.getCity() == null ? null : userDO.getCity();
            t.signature = userDO.getSignature() == null ? null : userDO.getSignature();
            if(userDO.getBirthday() != null){
                t.birthday = userDO.getBirthday().getTime();
            }
            travelKaItems.add(t);
        }
        infoList.travelKaItemPageInfoList = travelKaItems;
        return infoList;
    }

    public static TravelKaClub convertTravelKaClub(ClubInfoListDTO clubInfoListDTO){
        if(clubInfoListDTO == null){
            return null;
        }
        TravelKaClub travelKaClub = new TravelKaClub();
        travelKaClub.liveCount = clubInfoListDTO.getSubjectNum()== 0 ? 0 :clubInfoListDTO.getSubjectNum();
        travelKaClub.informationsCount =clubInfoListDTO.getDynamicNum()==0 ?0 :clubInfoListDTO.getDynamicNum();
        List<ClubInfoDTO> minsters = clubInfoListDTO.getMinisterClubList();
        List<KaClub> minsterKaClubs = null;;  // 是部长 的俱乐部
        if(minsters != null && minsters.size() > 0){
            minsterKaClubs = convertKaClubs(minsters, true);
        }
        List<ClubInfoDTO> clubInfoDTOs = clubInfoListDTO.getMemberClubList();

        List<KaClub> kaClubList = new ArrayList<KaClub>();

        if(minsterKaClubs !=null && minsterKaClubs.size() > 0){
            kaClubList.addAll(minsterKaClubs);
        }

        List<KaClub> kaClubs = null; // 非部长的俱乐部
        if(clubInfoDTOs!= null && clubInfoDTOs.size() > 0){
            kaClubs = convertKaClubs(clubInfoDTOs,false);
            kaClubList.addAll(kaClubs);
        }
        if(kaClubList != null){
            travelKaClub.kaClubs = kaClubList;
        }
        return travelKaClub;
    }

    private static List<KaClub>  convertKaClubs(List<ClubInfoDTO> clubInfoDTOs,boolean isMinster){
        if(clubInfoDTOs == null || clubInfoDTOs.size() < 0){
            return null;
        }
        List<KaClub> kaClubs = new ArrayList<KaClub>();
        for(ClubInfoDTO clubInfoDTO : clubInfoDTOs){
            KaClub kaClub = convertKaClub(clubInfoDTO,isMinster);
            kaClubs.add(kaClub);
        }
        return kaClubs;
    }

    private static KaClub convertKaClub(ClubInfoDTO clubInfoDTO, boolean isMinster){
        if(clubInfoDTO == null){
            return null;
        }
        KaClub kaClub = new KaClub();
        kaClub.clubImg = clubInfoDTO.getLogoUrl() == null ? null :clubInfoDTO.getLogoUrl();
        kaClub.clubName = clubInfoDTO.getClubName() == null ? null :clubInfoDTO.getClubName();
        kaClub.clubId = clubInfoDTO.getId() ==0 ? 0 : clubInfoDTO.getId();
        kaClub.isMinister = 0; // 默认都不是部长
        if(isMinster){
            kaClub.isMinister = 1;
        }

        return kaClub;
    }


    public static TravelKaVO converntTravelKaVODetail( List<UserAbilityRelationDO> userAbilityRelationDOs , MemberProfileDO travelKaDO, UserDO userDO){
        TravelKaVO travelKaVO = new TravelKaVO();
        travelKaVO.setId(travelKaDO.getId());
        travelKaVO.setUserId(userDO.getId());
        travelKaVO.setOptions(userDO.getOptions() == 0 ? 0 : userDO.getOptions());
        travelKaVO.setName(userDO.getName());
        travelKaVO.setNickName(userDO.getNickname() );
        return travelKaVO;
    }

    public static List<TravelKaVO> convertTravelKaList( List<MemberProfileDO> memberProfileDOs,Map<Long,UserDO> userDOMap, int pageNo, boolean hasNext){
        List<TravelKaVO> list = new ArrayList<TravelKaVO>();
        if(CollectionUtils.isEmpty(memberProfileDOs)){
            return null;
        }
        if(CollectionUtils.isEmpty(userDOMap)){
            return null;
        }
         for(MemberProfileDO memberProfileDO : memberProfileDOs){
            TravelKaVO travelKaVO = new TravelKaVO();
            if(userDOMap.containsKey(memberProfileDO.getUserId())){
                UserDO userDO = userDOMap.get(memberProfileDO.getUserId());
                travelKaVO.setName(userDO.getName());
                travelKaVO.setNickName(userDO.getNickname());
                travelKaVO.setOptions(userDO.getOptions());
                travelKaVO.setId(memberProfileDO.getId());
                travelKaVO.setUserId(userDO.getId());
                travelKaVO.setAvatar(userDO.getAvatar() == null ? null : userDO.getAvatar());
                travelKaVO.setName(userDOMap.get(memberProfileDO.getUserId()).getName());
            }

            list.add(travelKaVO);
        }
        return list;
    }



}
