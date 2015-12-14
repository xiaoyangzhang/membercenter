
function  isEmpty(val){
	if(val == 'undefined' || val == '' || val == ""){
		return true;
	}
	
	return false;
}

/**
 * 校验字段是否为空，并给出提示
 * @param val
 * @param field
 * @returns {Boolean}
 */
function checkEmpty(val,field,defaultVal){
	if(isEmpty(val)){
		alert(field + "不能为空");
		return false;
	}
	
	if(defaultVal != 'undefined' && val == defaultVal){
		alert(field + "不能为空");
		return false;
	}
	
	return true;
}

jQuery.validator.addMethod("carNum",function(value,element){
	return this.optional(element) || /^[^_][\u4e00-\u9fa5\da-zA-Z\-\_]+$/.test(value);
	//return this.optional(element) || /^[^_][A-Za-z]*[a-z0-9_]*$/.test(value);
},"不能输入特殊字符！");