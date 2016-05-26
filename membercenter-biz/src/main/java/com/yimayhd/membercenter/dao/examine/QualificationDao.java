package com.yimayhd.membercenter.dao.examine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.mapper.QualificationDOMapper;

/**
 * 
* @ClassName: QualificationDao
* @Description: 操作资质
* @author zhangxy
* @date 2016年5月26日 上午11:19:37
*
 */
public class QualificationDao {

	@Autowired
	private QualificationDOMapper qualificationDOMapper;
	public List<QualificationDO> getAllQualification(int domainId) {
		//List<QualificationDO> qualificationList = new ArrayList<QualificationDO>();
//		if (domainId <= 0) {
//			return null;
			//return MemResult.buildFailResult(-1, "参数错误", qualificationList);
	//	}
		List<QualificationDO> qualificationList = qualificationDOMapper.selectAllQualifications(domainId);
//		if (qualificationList == null) {
//			return null;
//		}
		//return MemResult.buildSuccessResult(qualificationList);
		return qualificationList;
	}
	
	
}
