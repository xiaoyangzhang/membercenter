(function ($) {
	$.fn.countdown = function(options,getSendMsgUrl){
		var opts = $.extend({
			grayBtn : 'gray-btn',
			autoTime : 60
		}, options);
		var $self = $(this); 
//		var actionUrl = $(this).attr('href');
		
		var time = opts.autoTime;
		var clickBtnEvent = function(){
			if($(this).hasClass('gray-btn')) return false;
			var actionUrl = getSendMsgUrl();
			$(this).attr('href',actionUrl);

			$.post(actionUrl,function(){
				var t = window.setInterval(function(){
					time--;
					if(time==0){
						time = opts.autoTime;
						clearInterval(t);
						$self.removeClass(opts.grayBtn).html('重新获取');
					}else{
						$self.addClass(opts.grayBtn);
						$self.html('重新获取('+time+')');
					}
				},1000);
			});
			return false;			
		}
		$self.on("click",clickBtnEvent);
	}
})(Zepto);