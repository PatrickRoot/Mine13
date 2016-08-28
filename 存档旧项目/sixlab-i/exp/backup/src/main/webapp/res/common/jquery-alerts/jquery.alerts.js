// jQuery Alert Dialogs Plugin
//
// Version 1.1
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 14 May 2009
//
// Visit http://abeautifulsite.net/notebook/87 for more information
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
//		1.01 - Fixed bug where unbinding would destroy all resize events
//
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//
(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .01,                // transparency level of overlay
		overlayColor: '#FFF',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;确定&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;取消&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		alert: function(message, title, callback) {
			if( title == null || title == '') title = '温馨提示';
			$.alerts._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			if( title == null || title == '' ) title = '请你确认';
			$.alerts._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
		
		payConfirm: function(message, title, callback) {
			if( title == null || title == '' ) title = '请你确认';
			$.alerts._show(title, message, null, 'payConfirm', function(result) {
				if( callback ) callback(result);
			});
		},	
		prompt: function(message, value, title, callback) {
			if( title == null || title == '' ) title = 'Prompt';
			$.alerts._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
		success: function(message, callback) {
			 
			$.alerts._showSuccess(message, function(result) {
				if( callback ) callback(result);
			});
		},
		
		// Private methods
		_showSuccess:function(msg,callback) {
			if($("#popup_container3").length <= 0) {
				$("BODY").append( 
						'<div class="ope-msgbox" id="popup_container3"><div class="ope-msgbox-bd"><i class="iconfont">&#xe649;</i><span id="popup_message_tip"></span></div></div>'
						 
				);
			}
			$("#popup_message_tip").text(msg);
			$("#popup_message_tip").html( $("#popup_message_tip").text().replace(/\n/g, '<br />'));  
			 
			$("#popup_container3 .ope-msgbox-bd").css("marginLeft",-($("#popup_container3").width()/2) + 15);
			//$("#popup_container3 .m-layer-wrap").width($("#popup_message_tip").parent().parent().width() + 100);
			setTimeout(function() {
				$("#popup_container3").remove();
			}, 2000 )
		},
		
		_show: function(title, msg, value, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			 
			var html = [
			'<div class="m-popup width-420 z-show" id="popup_container2" style="margin-left: -210px; margin-bottom: 50px; float: left;">',
				'<div class="m-popup-hd">',
					'<h2 id="popup_title">一句话完事的框</h2>',
					'<a href="###" id="popup_close" class="iconfont">&#xe640;</a>',
				'</div>',
				'<div class="m-popup-bd">',
					'<div class="content">',
					    '<i class="iconfont mpp-status orange" title="提示" id="popup_icon">&#xe677;</i>',
						 
						'<h3 id="popup_message">操作已成功执行。</h3>',
					'</div>',
				'</div>',
				'<div class="m-popup-bt" id="popup_btn"><span>删除后无法恢复，谨慎操作。</span><a href="###" class="u-button u-button-xlblue width-70">确定</a><a href="###" class="u-button u-button-xlwhite width-70">取消</a></div>',
			'</div>'].join('');
			$("BODY").append(html);
			
			//$("BODY").append(
//			'<div class="m-layer m-layer-mask z-show" id="popup_container2">'+
//			    '<table class="m-layer-frame">'+
//			        '<tbody>'+
//			            '<tr>'+
//			                '<td>'+
//			                    '<div class="m-layer-wrap width-400">'+
//			                        '<div class="m-layer-wrap-hd f-linear">'+
//			                            '<h2 class="m-layer-wrap-title" id="popup_title">窗口标题名称</h2>'+
//			                            '<a href="###" class="m-layer-close" id="popup_close"><i class="iconfont">&#xe640;</i></a>'+
//			                        '</div>'+
//			                        '<div class="m-layer-wrap-bd">'+
//			                            '<div class="m-tiptext m-tiptext-withicon m-tiptext-large m-tiptext-blue">'+
//			                                '<i class="iconfont" title="提示" id="popup_icon">&#xe677;</i><div class="m-tiptext-more"><span id="popup_message"></span></div>'+ 
//			                            '</div>'+
//			                        '</div>'+
//			                        '<div class="m-layer-wrap-bt">'+
//			                            '<div class="m-layer-tiptext">'+
//			                            '</div>'+
//			                            '<div class="m-layer-button" id="popup_btn">'+
//			                                '<a href="###" class="u-button u-button-lblue">无图标蓝色中</a>'+
//			                                '<button type="button" class="u-button u-button-lwhite">取消删除</button>'+
//			                            '</div>'+
//			                        '</div>'+
//			                    '</div>'+
//			                '</td>'+
//			            '</tr>'+
//			        '</tbody>'+
//			    '</table>'+
//			'</div>'); 
			/*$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');*/
			
			//if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix 
			var pos = ('undefined' == typeof(document.body.style.maxHeight)) ? 'absolute' : 'fixed'; 
			
			//$("#popup_container2").css({
//				position: pos,
//				zIndex: 99999,
//				padding: 0,
//				margin: 0
//			});
			
			var $h = $("#popup_container2").outerHeight();
			$("#popup_container2").css({
				zIndex: 99999,
				marginTop:-$h/2
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					//$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
			
					$("#popup_btn").html('<a href="###" class="u-button u-button-xlblue width-70" id="popup_ok"><i class="iconfont iconfont-left">&#xe63f;</i>' + $.alerts.okButton + '</a>');
					$("#popup_ok,#popup_close").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
					
					$("#popup_icon").html("&#xe6a4;");
				break;
				case 'confirm':
					//$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					if(typeof(customBtn) === "function"){
						//自定义按钮,曲建秀添加
						customBtn($("#popup_btn"));
					}else{
						$("#popup_btn").html('<a href="###" class="u-button u-button-xlblue width-70" id="popup_ok"><i class="iconfont iconfont-left">&#xe63f;</i>' + $.alerts.okButton + '</a> <a href="###" class="u-button u-button-xlwhite width-70" id="popup_cancel"><i class="iconfont iconfont-left">&#xe640;</i>' + $.alerts.cancelButton + '</a>');
					}
					
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel,#popup_close").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'payConfirm':
					//$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					if(typeof(customBtnPay) === "function"){
						//自定义按钮,曲建秀添加
						customBtnPay($("#popup_btn"));
					}else{
						$("#popup_btn").html('<a href="###" class="u-button u-button-xlblue" id="popup_ok"><i class="iconfont iconfont-left">&#xe63f;</i>' + $.alerts.okButton + '</a> <a href="###" class="u-button u-button-xlwhite" id="popup_cancel"><i class="iconfont iconfont-left">&#xe640;</i>' + $.alerts.cancelButton + '</a>');
					}
					
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel,#popup_close").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break; 
			}
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container2").remove(); 
			$(".j-alert-mask").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div class="m-popup-mask" id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height()
						//background: $.alerts.overlayColor
						//opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( 'undefined' == typeof(document.body.style.maxHeight) ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	// Shortuct functions
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	}
	
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
	jPayConfirm	= function(message, title, callback) {
		$.alerts.payConfirm(message, title, callback);
	};
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
	
	jSuccess = function(message, callback) {
		$.alerts.success(message, callback);
	};
	
})(jQuery);