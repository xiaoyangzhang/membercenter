package com.yimayhd.membercenter.dao.examine;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.membercenter.client.domain.merchant.CategoryQualificationDO;
import com.yimayhd.membercenter.client.domain.merchant.QualificationDO;
import com.yimayhd.membercenter.client.query.QualificationQueryDTO;
import com.yimayhd.membercenter.mapper.CategoryQualificationDOMapper;
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
	public List<QualificationDO> getQualification(QualificationQueryDTO queryDTO) {
		return qualificationDOMapper.getQualification(queryDTO,queryDTO.getIdSet());
	}
	
}
