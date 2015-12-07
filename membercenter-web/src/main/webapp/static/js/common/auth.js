
/**
 * 获取salt
 */
function getSalt(){
	 len = 16;
	 var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	 var maxPos = $chars.length;
	 var salt = '';
	 for (i = 0; i < len; i++) {
		 salt += $chars.charAt(Math.floor(Math.random() * maxPos));
	 }
	
	 return salt;
}

/**
 * 生成sign值,暂时省略md5加密
 * @param salt
 * @param data
 * @returns {String}
 */
function generateSign(salt,dataArray){
	//对salt做混淆，混淆算法应同服务器端一致
	var sign = mix(salt);
	for(var i = 0 ;i < dataArray.length; ++i){
		sign += ":" + dataArray[i];
	}
	
	return sign;
}

/**
 * 按照一定的规则混淆字符串
 */
function mix(target){
	var result = target;
//	
//	if(target.length < 8){
//		return result;
//	}
//	
//	var indexArray = [1,3,4,5,6,8]
//	var replaceArray = ['a','e','4','d','o','n'];
//	//替换
//	for(var i = 0;i < indexArray.length;++i){
//		var index = indexArray[i] % replaceArray.length;
//		result = result.replace(indexArray[i],replaceArray[index]);
//	}
	
	return result;
}


