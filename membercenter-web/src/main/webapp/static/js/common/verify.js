
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
function checkEmpty(val,field){
	if(isEmpty(val)){
		alert(field + "不能为空");
		return false;
	}
	
	return true;
}