package com.yimayhd.membercenter.client.domain.feature;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.yimayhd.membercenter.client.enums.feature.MerchantCategoryFeatureKey;

public class MerchantCategoryFeature implements Serializable {

	private static final long	serialVersionUID	= 1L;
	private Map<String, Object>	feature				= new HashMap<String, Object>();

	public MerchantCategoryFeature(String feature) {
		setFeature(feature);
	}

	public void put(MerchantCategoryFeatureKey MerchantCategoryFeatureKey, Object object) {
		feature.put(MerchantCategoryFeatureKey.getValue(), object);
	}

	public long getBusinessType() {
		return parseLong(MerchantCategoryFeatureKey.BUSINESS_TYPE);
	}


	private <T> List<T> parseList(MerchantCategoryFeatureKey MerchantCategoryFeatureKey, Class<T> clazz) {
		Object ob = feature.get(MerchantCategoryFeatureKey.getValue());
		if (ob == null || !(ob instanceof List)) {
			return null;
		}
		return (List<T>) ob;
	}


	private <T> List<T> parseCustomObjectList(MerchantCategoryFeatureKey MerchantCategoryFeatureKey, Class<T> clazz) {
		Object ob = feature.get(MerchantCategoryFeatureKey.getValue());
		if (ob == null || !(ob instanceof List)) {
			return null;
		}
		if(ob.getClass()==JSONArray.class){
			return JSON.parseArray(ob.toString(), clazz);
		}
		return (List<T>) ob;
	}

	private long parseLong(MerchantCategoryFeatureKey MerchantCategoryFeatureKey) {
		Object ob = feature.get(MerchantCategoryFeatureKey.getValue());
		if (ob == null) {
			return 0;
		}
		if (ob instanceof Long) {
			return (Long) ob;
		}
		if (ob instanceof Integer) {
			return (Integer) ob;
		}
		return 0;
	}

	private String parseString(MerchantCategoryFeatureKey MerchantCategoryFeatureKey) {
		Object ob = feature.get(MerchantCategoryFeatureKey.getValue());
		if (ob == null || !(ob instanceof String)) {
			return null;
		}
		return (String) ob;
	}

	private int parseInt(MerchantCategoryFeatureKey MerchantCategoryFeatureKey) {
		Object ob = feature.get(MerchantCategoryFeatureKey.getValue());
		if (ob == null) {
			return 0;
		}
		if (ob instanceof Long) {
			return ((Long) ob).intValue();
		}
		if (ob instanceof Integer) {
			return (Integer) ob;
		}
		return 0;
	}

	public String getFeature() {
		return JSON.toJSONString(feature);
	}

	private void setFeature(String feature) {
		if (StringUtils.isBlank(feature)) {
			this.feature = new HashMap<String, Object>();
			return;
		}
		this.feature = JSON.parseObject(feature, HashMap.class);
		if (this.feature == null) {
			this.feature = new HashMap<String, Object>();
		}
	}

}
