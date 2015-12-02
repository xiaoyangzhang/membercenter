
var contextPath=$("#contextPath").val();

/*#####################################ajax 后台交互:start##########################################*/

function getCurrentPoint(userId, merchantId, callback) {
	$.post(contextPath + "/point/memeberTotalPoint", {
		userId : userId,
		merchantId : merchantId
	}, function(data, status) {
		callback(data);
	});
}

function getPointDetailsByPage(pageNumber, pageSize, userId, merchantId,
		callback) {
	// 分页获取积分明细
	$.post(contextPath + "/point/memberPointDetails", {
		userId : userId,
		merchantId : merchantId,
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
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		useTransition: true,
		onScrollMove: function () {
			if (this.y > 5 && !pullDownEl.className.match('flip')) {
				pullDownEl.className = 'flip';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Release to refresh...';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl.className.match('flip')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Release to refresh...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			pointDetailPullUpAction();	
		}
	});
	
} 

function pointDetailPullUpAction () {
	//页号
	var currentPage = parseInt($("#pageNumber").val()) + 1;
	
	var totalPage = $("#totalPage").val();
	if(currentPage >  parseInt(totalPage)){
		return ;
	}
	
	$("#pageNumber").val(currentPage);
	
	//每页显示多少条
	var pageSize = $("#pageSize").val();
	var userId = $("#userId").val();
	var merchantId = $("#merchantId").val();
	
	//获取积分明细
	getPointDetailsByPage(currentPage, pageSize, userId, merchantId, function(
			data) {
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			var pointDetails = data.data.pointDetails;
			for (i in pointDetails) {
				var detailStr = " <li> <span class='left'>"
						+ pointDetails[i].source + "&nbsp; <br/><em>"
						+ pointDetails[i].createDate
						+ "</em></span><span class='right'> "
						+ pointDetails[i].type + pointDetails[i].point
						+ "<br/> <em>有效期至" + pointDetails[i].endDate
						+ "</em></span></li> ";
				$("#showPointDetailsDiv").append(detailStr);
			}
		}else{
			alert("获取积分明细错误:" + data.meta.message);
		}

	});	
	myScroll.refresh();	
}


function initPointDetails(){
	//页号
	var pageNumber = $("#pageNumber").val();
	//每页显示多少条
	var pageSize = $("#pageSize").val();
	var userId = $("#userId").val();
	var merchantId = $("#merchantId").val();
	
	//获取当前积分
	getCurrentPoint(userId,merchantId,function(data){
		var isSuccessful = data.meta.success;
		if (isSuccessful == true) {
			$("#currentPoint").text(data.data.totalPoint);
		}else{
			alert("获取总积分错误:" + data.meta.message);
		}
	});

	//获取积分明细
	getPointDetailsByPage(pageNumber, pageSize, userId, merchantId, function(
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
				$("#showPointDetailsDiv").append(detailStr);
			}
		}else{
			alert("获取积分明细错误:" + data.meta.message);
		}
	});

	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

	document.addEventListener('DOMContentLoaded', function () { setTimeout(pointDetailLoaded, 200); }, false);
}


/*############################积分明细页面:end##############################################*/
