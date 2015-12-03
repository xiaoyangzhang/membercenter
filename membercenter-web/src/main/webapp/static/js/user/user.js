var contextPath=$("#contextPath").val();

/*###########################ajax后台交互:start##################################################*/
function sendAuthCode(phone,callback) {
	var phone = $("#phone").val();
	//alert(phone);
	
	$.post(contextPath + "/user/sendMsgCode", {
		phone : phone
	}, function(data, status) {
		alert("验证发送成功!");
	});
}

function checkAuthCode(phone,authCode,callback){
	// 校验短信验证码
	$.post(contextPath + "/user/checkMsgCode", {
		phone : phone,
		authCode : authCode
	}, function(data, status) {
		callback(data);
	});
}

/*###########################ajax后台交互:end##################################################*/


/*###########################用户相关页面js:start##########################################################*/
/**
 * 初始化短信验证码页面
 */
function initCheckAuthCode(){
	Zepto(function($){
		$('.sendcode-btn').countdown({autoTime:60});
	 });
	
	
	 $(".sendcode-btn").attr("href",contextPath + "/user/sendMsgCode?phone=" + $("#phone").val());
	 
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
	 
	 checkAuthCode(phone,authCode,function(data){
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
 	
 	if(isEmpty(username) || isEmpty(sex) || isEmpty(birthdate) ||  province == ''|| city == '' ){
 		alert("请完善信息!");
 		return ;
 	}
 	
 	fulfillUserInfoForm.submit();
 }


function initTwoDimension(){
	$("#code").qrcode({
		render : "table", //table方式
		width : 200, //宽度
		height : 200, //高度
		text : $("#codeInfo").val()
	});
	
	var userId = $("#userId").val();
	var merchantId = $("#merchantId").val();
	//获取当前积分
	getCurrentPoint(userId,merchantId,function(data){
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			$("#currentPoint").text("当前积分:" + data.data.totalPoint);
		}
	});
	
}


/*###########################用户相关页面js:end##########################################################*/