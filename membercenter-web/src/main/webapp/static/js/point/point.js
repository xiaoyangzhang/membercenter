
var contextPath=$("#contextPath").val();

/*#####################################ajax 后台交互:start##########################################*/

function getCurrentPoint( callback) {
	$.post(contextPath + "/point/memeberTotalPoint", {
	}, function(data, status) {
		callback(data);
	});
}

function getPointDetailsByPage(pageNumber, pageSize,
		callback) {
	// 分页获取积分明细
	$.post(contextPath + "/point/memberPointDetails", {
		pageNumber : pageNumber,
		pageSize : pageSize
	}, function(data, status) {
		callback(data);
	});

}
/*#####################################ajax 后台交互:end##########################################*/


/*############################积分明细页面:start##############################################*/
function pointDetailLoaded() {
	pullUpEl = document.getElementById('pullUp');	
	//pullUpEl = document.getElementById('pointDetails');
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		useTransition: true,
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
			}
		},
		onScrollMove : function(){
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '放开刷新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
				this.maxScrollY = pullUpOffset;
			}
			//this.maxScrollY = pullUpOffset;
			//console.log(this.maxScrollY);
			//console.log($("#scroller").height());
			console.log(pullUpOffset);
		},
		onScrollEnd: function () {
			if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
				pointDetailPullUpAction();	// Execute custom function (ajax call?)
			}
			/*setTimeout(
			pointDetailPullUpAction(),2000);*/
		}
	});
	
} 

function pointDetailPullUpAction () {
	//页号
	var pageNumber = $("#pageNumber").val();
	var totalPage = $("#totalPage").val();
	if(pageNumber >= totalPage){
		$("#pullUp").hide();
		return;
	}
	
	//每页显示多少条
	var pageSize = $("#pageSize").val();
	var userId = $("#userId").val();
	var merchantId = $("#merchantId").val();
	
	//获取当前积分
	getCurrentPoint(function(data){
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			$("#currentPoint").text(data.data.totalPoint);
		}else{
			alert("获取总积分错误:" + data.meta.message);
		}
	});

	//获取积分明细
	getPointDetailsByPage(pageNumber, pageSize, function(
			data) {
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			var pointDetails = data.data.pointDetails;
			var totalPage = data.data.totalPage;
			$("#totalPage").val(totalPage);

			for (i in pointDetails) {
				var detailStr = " <li> <span class='left'>"
						+ pointDetails[i].source + "&nbsp; <br/><em>"
						+ pointDetails[i].createDate
						+ "</em></span><span class='right'> "
						+ pointDetails[i].type + pointDetails[i].point
						+ "<br/> <em>有效期至" + pointDetails[i].endDate
						+ "</em></span></li> ";
				$("#pointDetails").append(detailStr);
			}
			
			$("#pageNumber").val(parseInt(pageNumber) + 1);
		}else{
			alert("获取积分明细错误:" + data.meta.message);
		}
		
		myScroll.refresh();
	});
}


function initPointDetails(){
	//页号
	var pageNumber = $("#pageNumber").val();
	//每页显示多少条
	var pageSize = $("#pageSize").val();
	var totalPage = $("#totalPage").val();
	
	//获取当前积分
	getCurrentPoint(function(data){
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			$("#currentPoint").text(data.data.totalPoint);
		}else{
			alert("获取总积分错误:" + data.meta.message);
		}
	});

	//获取积分明细
	getPointDetailsByPage(pageNumber, pageSize, function(
			data) {
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			var pointDetails = data.data.pointDetails;
			var totalPage = data.data.totalPage;
			if(totalPage > 1 ){
				$("#pullUp").show();
			}
			
			$("#totalPage").val(totalPage);

			for (i in pointDetails) {
				var detailStr = " <li> <span class='left'>"
						+ pointDetails[i].source + "&nbsp; <br/><em>"
						+ pointDetails[i].createDate
						+ "</em></span><span class='right'> "
						+ pointDetails[i].type + pointDetails[i].point
						+ "<br/> <em>有效期至" + pointDetails[i].endDate
						+ "</em></span></li> ";
				$("#pointDetails").append(detailStr);
			}
		}else{
			alert("获取积分明细错误:" + data.meta.message);
		}
	});

	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

	document.addEventListener('DOMContentLoaded', function () { setTimeout(pointDetailLoaded, 200); }, false);
}


/*############################积分明细页面:end##############################################*/
