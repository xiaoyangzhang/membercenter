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

	 $("#ym-year").append("<option value=''>请选择</option>");
	 for(var i = 0;i < yearCounts;++i){
		 $("#ym-year").append("<option value='" + (startYear + i) + "'>" + (startYear + i) +  "</option>");
	 }
	 
	 $("#ym-month").on("change",function(){
		 generateDay();
	 });
	 
	 $("#ym-year").on("change",function(){
		 generateDay();
	 });
}

function generateDay(){
	
	if($("#ym-month").val() == "-1" || $("#ym-year").val() == "-1"){
		return;
	}
	
	var dayCount = 31;
	
	// 1,3,5,7,8,10,12有31天; 4,6,8,10有30天 ; 2月份需要判断是否为闰年，闰年29天，否则28天
	if ($("#ym-month").val() == "04" || $("#ym-month").val() == "06"
			|| $("#ym-month").val() == "09" || $("#ym-month").val() == "11") {
		dayCount = 30;
	} else if ($("#ym-month").val() == "02"){

		var ymYear = $("#ym-year").val();
		if (((ymYear % 4) == 0) && ((ymYear % 100) != 0)
				|| ((ymYear % 400) == 0)) {
			dayCount = 29;
		} else {
			dayCount = 28;
		}
	}
	
	$("#ym-day").children().remove();
	$("#ym-day").append("<option value=''>请选择</option>");
	for(var i = 1;i < 10; ++i){
		$("#ym-day").append("<option value='0" + i + "'>0" +  i +  "</option>");
	}
	
	for(var i = 10;i <= dayCount; ++i){
		$("#ym-day").append("<option value=" + i + ">" +  i +  "</option>");
	}
	
}

/**
 * 信息补全页面表单提交
 */
function fullfillUser() {
 	var username= $("#name").val();
 	var sex = $("#gender").val();
 	
 	var ymYear =  $("#ym-year").val();
 	var ymMonth = $("#ym-month").val();
 	var ymDay =   $("#ym-day").val();
 	
 	var province = $("#ym-province").val();
 	var city = $("#ym-city").val();
 	
 	/*if( !checkEmpty(username,"名字") || !checkEmpty(sex,"性别","-1") || !checkEmpty(ymYear,"出生日期","-1") || !checkEmpty(ymMonth,"出生日期","-1") || !checkEmpty(ymDay,"出生日期","-1") || !checkEmpty(province,"省份") ||  !checkEmpty(city,"城市")){
 		return ;
 	}*/
 	
 	/*if(username.length > 10){
 		alert("会员名长度不能大于10位");
 		return ;
 	}*/
	if($("#fulfillUserInfoForm").valid()){
		fulfillUserInfoForm.submit();
	}
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