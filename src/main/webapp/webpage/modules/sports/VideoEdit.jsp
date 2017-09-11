<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/webuploader.jsp"%>

<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<html>
<head>
<title>Add Video Form</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>

<script>

	$(function() {
		
       var $ = jQuery,
           	$list = $('#fileList'),
       		// 优化retina, 在retina下这个值是2
           	ratio = window.devicePixelRatio || 1,
  
       		// 缩略图大小
           	thumbnailWidth = 100 * ratio,
           	thumbnailHeight = 100 * ratio,
  
       		// Web Uploader实例
           	uploader;
           	
			//	初始化Web Uploader
	 		uploader = WebUploader.create({

    			// 选完文件后，是否自动上传。
    			auto: true,

    			// swf文件路径
    			swf: './js/webuploader-0.1.5/Uploader.swf',

    			// 文件接收服务端。
    			server: 'http://localhost:9090/dynobike/a/sports/resmgmt/uploadVideoFile',

    			// 选择文件的按钮。可选。
    			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
    			pick: '#picker',

    			// 只允许选择图片文件。
    			//accept: {
        		//	title: 'Images',
        		//	extensions: 'gif,jpg,jpeg,bmp,png',
        		//	mimeTypes: 'image/jpg,image/jpeg,image/png'
    			//}
				
				accept: {
					title: 'Video',
					extensions: 'mp4,avi,mpg',
					mimeTypes: 'video/mp4'
				}
			});
	
		// 当有文件添加进来的时候
		uploader.on('fileQueued', function(file) {
	    	var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	                '<img>' +
	                '<div class="info">' + file.name + '</div>' +
	            '</div>'
	            ),
			$info = $li.find('.info'),
	        $img = $li.find('img');
			
//			$info.css('width', 560px);
			$li.css('width', "520px");
			
	    	// $list为容器jQuery实例
	    	$list.append($li);

	    	// 创建缩略图
	    	// 如果为非图片文件，可以不用调用此方法。
	    	// thumbnailWidth x thumbnailHeight 为 100 x 100
//	    	uploader.makeThumb( file, function( error, src ) {
//	        	if ( error ) {
//	            	$img.replaceWith('<span>不能预览</span>');
//	            	return;
//	        	}
//
//	        	$img.attr( 'src', src );
//	    	}, thumbnailWidth, thumbnailHeight );
		});	
	
		// 文件上传过程中创建进度条实时显示。
		uploader.on('uploadProgress', function(file, percentage) {
			
	    	var $li = $('#'+file.id),
	        	$percent = $li.find('.progress span');
			
			
	    	// 避免重复创建
	    	if 	(!$percent.length) {
	        	$percent = $('<div class="progress"><span id="bar"></span></div>')
	                .appendTo($li)
	                .find('span');
	    	}
		
			var bar = document.getElementById("bar");
			var percentComplete = Math.round(percentage * 100);
						
			bar.style.width = percentComplete + '%';
        	bar.innerHTML = percentComplete + ' % complete';
			
//	    	$percent.css('width', 	percentComplete + '%');
			$percent.css('height', 	'5px');
			$percent.css('color',   '#0000FF');
			
			$percent.css("background-color","yellow");
		});

		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on('uploadSuccess', function(file) {
			
	    	$('#'+file.id).addClass('upload-state-done');
		});

		// 文件上传失败，显示上传出错。
		uploader.on('uploadError', function(file) {
			
	    	var $li = $('#'+file.id),
	        	$error = $li.find('div.error');

	    	// 避免重复创建
	    	if 	(!$error.length) {
	        	$error = $('<div class="error"></div>').appendTo( $li );
	    	}

	    	$error.text('上传失败');
		});

		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on('uploadComplete', function(file) {
	    	$('#'+file.id ).find('.progress').remove();
		});
	})
	
</script>

<style>

#global {
    text-align: left;
    border: 1px solid #dedede;
    background: #efefef;
    width: 560px;
    padding: 20px;
    margin: 100px auto;
}

form {
  font:100% verdana;
  min-width: 500px;
  max-width: 600px;
  width: 560px;
}

form fieldset {
  border-color: #bdbebf;
  border-width: 3px;
  margin: 0;
}

legend {
    font-size: 1.3em;
}

form label { 
    width: 250px;
    display: block;
    float: left;
    text-align: right;
    padding: 2px;
}

table td {
	border: 1px solid #dedede;
	background: MistyRose;
	/* for web colors visit http://en.wikipedia.org/wiki/Web_colors */
}

#buttons {
    text-align: right;
}
#errors, li {
	color: red;
}
.error {
    color: red;
    font-size: 9pt;	
}
</style>

</head>
<body>

<div id="global">
<form:form commandName="video" action="${ctx}/sports/video/VideoAddSave" method="post">
    <fieldset>
        <legend>Video Basic Info</legend>
        <form:hidden path="create_time" />
        <form:hidden path="lastmodifytime" />
        <form:hidden path="route_id" />
        
        <!-- video_name -->
        <p>
            <label for="title">Video Name: </label>
            <form:input id="video_name" path="video_name"/>
        </p>
        
        <!-- author -->
        <p>
            <label for="author">Author: </label>
            <form:input id="creator_id" path="creator_id"/>
        </p>
        
        <!-- video_intr -->
        <p>
            <label for="video_intr">Introduction: </label>
            <form:textarea id="video_intr" path="video_intr" rows="5" cols="40" />
        </p>
        
        <!--  isFree -->
        
        <!-- price -->
        <p>
            <label for="price">Price: </label>
            $<form:input id="price" path="price"/>
        </p>

		<br />
		<br />
		
		<div id="uploader" class="wu-example">    
    		<!--用来存放文件信息-->
    		<div id="fileList" class="uploader-list"></div>		
    		<div class="btns">
        		<div id="picker" style="float:left">选择文件</div>
        		<button id="ctlBtn" style="float:left; margin-left: 5px; border-radius: 3px; padding: 10px 15px;" class="btn btn-default">开始上传</button>
    		</div>
		</div>
                
		<br />
		<br />
		<br />
		                
        <div id="buttons" style="display: block; float:right; margin-right: 5px; ">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" class="btn btn-primary" value="Save Video">
        </div>
        
    </fieldset>
</form:form>
</div>
</body>
</html>