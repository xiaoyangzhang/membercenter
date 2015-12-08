var contextPath=$("#contextPath").val();
var myScroll, pullUpEl, pullUpOffset, generatedCount = 0;

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


function pointDetailPullUpAction () {
	//页号
	var pageNumber = $("#pageNumber").val();
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
				$("#theList").append(detailStr);
			}
		}else{
			alert("获取积分明细错误:" + data.meta.message);
		}
	});
}




//function pullUpAction () {
//	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
//		var el, li, i;
//		el = document.getElementById('thelist');
//
//		for (i=0; i<3; i++) {
//			li = document.createElement('li');
//			li.innerText = " <li> <span class='left'>"
//				+ pointDetails[i].source + "&nbsp; <br/><em>"
//				+ pointDetails[i].createDate
//				+ "</em></span><span class='right'> "
//				+ pointDetails[i].type + pointDetails[i].point
//				+ "<br/> <em>有效期至" + pointDetails[i].endDate
//				+ "</em></span></li> ";
//			el.appendChild(li, el.childNodes[0]);
//		}
//
//		myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
//	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
//}

function loaded() {
	pullUpEl = document.getElementById('pullUp');
	pullUpOffset = pullUpEl.offsetHeight;

	myScroll = new iScroll('wrapper', {
		useTransition: true,
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
			}
		},
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '放开刷新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
				pointDetailPullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});

	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
