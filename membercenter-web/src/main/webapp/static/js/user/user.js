var contextPath=$("#contextPath").val();

/*###########################ajax后台交互:start##################################################*/
function sendAuthCode(phoneNO,salt,sign,securityField,callback) {
	//sign=&salt=&securityField=phone
	$.post(contextPath + "/user/sendMsgCode", {
		phone : phoneNO,
		salt  : salt,
		sign  : sign,
		securityFields : securityFields
	}, function(data, status) {
		callback(data);
	});
}

function checkAuthCode(phone,authCode,salt,sign,securityFields,callback){
	// 校验短信验证码
	$.post(contextPath + "/user/checkMsgCode", {
		phone : phone,
		authCode : authCode,
		salt  : salt,
		sign  : sign,
		securityFields : securityFields
	}, function(data, status) {
		callback(data);
	});
}

/*###########################ajax后台交互:end##################################################*/


/*###########################用户相关页面js:start##########################################################*/
function getSendMsgUrl(){
	var phoneNO = $("#phone").val();
	var dataArray = new Array();
	dataArray[0] = phoneNO
	//var data = ":" + phoneNO;
	var salt = getSalt();
	var sign = generateSign(salt,dataArray);
	var securityFields = "phone";
	var url = contextPath + "/user/sendMsgCode?phone=" + $("#phone").val() + "&salt=" + salt + "&sign=" + sign + "&securityFields=" + securityFields;
	
	return url;
}
/**
 * 初始化短信验证码页面
 */
function initCheckAuthCode(){
	
	Zepto(function($){
		$('.sendcode-btn').countdown({autoTime:60},getSendMsgUrl);
	 }); 
}


/**
 * 跳转到二维码页面
 */
function getTwoDimension(){
	 var phone = $("#phone").val();
	 var authCode = $("#authCode").val();
	 if (isEmpty(authCode)) {
			alert("请输入验证码!");
			return;
	 }
	 var dataArray = new Array();
	 dataArray[0] = phone;
	 dataArray[1] = authCode;
	 
	 var salt = getSalt();
	 var sign = generateSign(salt,dataArray);
	 var securityFields = "phone:authCode";
	 checkAuthCode(phone,authCode,salt,sign,securityFields,function(data){
		    var isSuccessful = data.meta.success;
			if (isSuccessful == true) {
				alert("验证成功!");
				authCodeForm.submit();
			}else{
				alert("验证码不正确!");
			}
	 });

 }

/**
 * 校验注册必填项
 */
function checkRegisterForm() {
	var s = registerForm.phone.value;
	if(isEmpty(s) || s.length != 11 || s.substr(0,1) != '1') {
		alert("请输入正确的手机号码!");
		return ;
	}
	
	registerForm.submit();
	
}

/**
 * 初始化信息补全页面
 */
function initFulfillUserInfo(){
	Zepto(function($){ 
		$(document).selectCity();
	 });
	 
	 var yearCounts = 85;
	 var startYear = 1930;
	 for(var i = 0;i < yearCounts;++i){
		 $("#ym-year").append("<option value='" + (startYear + i) + "'>" + (startYear + i) +  "</option>");
	 }
	 
}

/**
 * 信息补全页面表单提交
 */
function fullfillUser() {
 	var username= $("#name").val();
 	var sex = $("#gender").val();
 	var birthdate = $("#birthdate").val();
 	var province = $("#ym-province").val();
 	var city = $("#ym-city").val();
 	
 	if( !checkEmpty(username,"名字") || !checkEmpty(sex,"性别") || !checkEmpty(birthdate,"出生年月") || !checkEmpty(province,"省份") ||  !checkEmpty(city,"城市")){
 		return ;
 	}
 	
 	if(username.length > 8){
 		alert("名字超过最大限制");
 		return ;
 	}
 	
 	fulfillUserInfoForm.submit();
 }


function initTwoDimension(){
	$("#code").qrcode({
		render : "canvas", //table方式
		width : "200", //宽度
		height : "200", //高度
		text : $("#codeInfo").val()
	});
	
	var userId = $("#userId").val();
	var merchantId = $("#merchantId").val();
	//获取当前积分
	getCurrentPoint(function(data){
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			$("#currentPoint").text("当前积分:" + data.data.totalPoint);
		}
	});
	
}


/*###########################用户相关页面js:end##########################################################*/