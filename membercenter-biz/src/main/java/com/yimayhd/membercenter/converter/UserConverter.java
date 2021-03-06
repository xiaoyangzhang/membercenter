/*
 * FileName: UserConverter.java
 * Author:   liubb
 * Date:     2016年3月23日 下午4:24:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.yimayhd.membercenter.converter;

import com.yimayhd.membercenter.client.domain.talent.TalentInfoDO;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.dto.UserDTO;
import com.yimayhd.user.client.enums.Gender;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author liubb
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UserConverter {

    /**
     * 
     * 功能描述: <br>
     * 〈talentInfoDO转userDO〉
     *
     * @param talentDO 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static UserDO talentInfoConverterToUserDO(TalentInfoDO talentDO){
        UserDO userDO = new UserDO();
        if(null == talentDO){
            return userDO;
        }
        userDO.setId(talentDO.getId());
        userDO.setAvatar(talentDO.getAvatar());
        userDO.setName(talentDO.getReallyName());
        userDO.setGender(talentDO.getGender());
        userDO.setBirthday(talentDO.getBirthday());
        userDO.setNickname(talentDO.getNickName());
        userDO.setCity(talentDO.getCity());
        userDO.setCityCode(talentDO.getCityCode());
        userDO.setProvince(talentDO.getProvince());
        userDO.setProvinceCode(talentDO.getProvinceCode());
        return userDO;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈USERDTO〉
     *
     * @param talentDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static UserDTO talentInfoConverterToUserDTO(TalentInfoDO talentDO, UserDO userDO){
        UserDTO userDTO = new UserDTO();
        if(null == talentDO){
            return userDTO;
        }
        if(null == userDO){
            userDO = new UserDO();
        }
        userDTO.setId(talentDO.getId());
        userDTO.setAvatar(talentDO.getAvatar());
        userDTO.setName(talentDO.getReallyName());
        userDTO.setGender(Gender.getGenderByValue(talentDO.getGender()));
        userDTO.setBirthday(talentDO.getBirthday());
        userDTO.setNickname(talentDO.getNickName());
        userDTO.setCityCode(talentDO.getCityCode());
        userDTO.setProvinceCode(talentDO.getProvinceCode());
        userDTO.setSignature(userDO.getSignature());
        return userDTO;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈userDO转talentInfoDO〉
     *
     * @param userDO
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static TalentInfoDO userDOConverterToTalentInfo(UserDO userDO){
        TalentInfoDO talentInfoDO = new TalentInfoDO();
        if(null == userDO){
            return talentInfoDO;
        }
        talentInfoDO.setId(userDO.getId());
        talentInfoDO.setAvatar(userDO.getAvatar());
        talentInfoDO.setReallyName(userDO.getName());
        talentInfoDO.setGender(userDO.getGender());
        talentInfoDO.setBirthday(userDO.getBirthday());
        talentInfoDO.setNickName(userDO.getNickname());
        talentInfoDO.setCity(userDO.getCity());
        talentInfoDO.setCityCode(userDO.getCityCode());
        talentInfoDO.setProvince(userDO.getProvince());
        talentInfoDO.setProvinceCode(userDO.getProvinceCode());
        return talentInfoDO;
    }
    
}
