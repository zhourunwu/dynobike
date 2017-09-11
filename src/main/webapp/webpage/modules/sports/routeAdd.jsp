<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"></c:set>

<!DOCTYPE html">
<html>
<head>
<title>Add Route Form</title>

<script src="${ctx}/resources/js/jquery.js"></script>

<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


<link rel="stylesheet" href="${ctx}/resources/uploader/webuploader.css">

<style>

#global {
    text-align: left;
    border: 1px solid #dedede;
    background: #efefef;
    width: 600px;
    padding: 20px;
    margin: 60px auto;
}

form {
  font:100% verdana;
  min-width: 500px;
  max-width: 600px;
  width: 590px;
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
    width: 150px;
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
		
<form:form commandName="routetoAdd" action="${ctx}/a/sports/route/routeAddSave" method="post">
    <fieldset>
        <legend>Route Basic Info</legend>
        <form:hidden path="create_time" />
        <form:hidden path="lastmodifytime" />
        <form:hidden id="coverimage_name" path="cover_image.img_name" />
        <form:hidden id="coverimage_id"   path="cover_image.img_id"  />
        
        <!-- route_name -->
        <p>
            <label for="title">Route Name: </label>
            <form:input id="route_name" path="route_name"/>
        </p>
        
        <!-- author -->
        <p>
            <label for="author">Author: </label>
            <form:input id="creator_id" path="creator_id"/>
        </p>
        
        <!-- video_intr -->
        <p>
            <label for="route_intr">Introduction: </label>
            <form:textarea id="route_intr" path="route_intr" rows="5" cols="40" />
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
        		<button id="ctlBtn" type="button" style="float:left; margin-left: 5px; border-radius: 3px; padding: 10px 15px;" class="btn btn-default">开始上传</button>
    		</div>
		</div>

		<br />
		<br />
		<br />
		
        <div id="buttons" style="display: block; float:right; margin-right: 5px; ">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" class="btn btn-primary" value="Add Route">
        </div>
        
    </fieldset>
</form:form>
        
</div>

<script src="${ctx}/resources/uploader/webuploader.min.js"></script>

<script>

	$(function(){
	
		//var	ratio = window.devicePixelRatio || 1,
		//
       	//	// 缩略图大小
        //   	thumbnailWidth = 100 * ratio,
        //   	thumbnailHeight = 100 * ratio;
			
    	var uploader = $.WebUploader({
    		
    		swf : BASE_URL + '/resources/uploader/Uploader.swf',
    		
    		//server: 'http://localhost:9090/dynobike/a/sports/uploader/upload.json',
    		//server: BASE_URL + '/upload.json',
    		server: 'http://localhost:9090/dynobike/a/sports/resmgmt/upload',
			
    		pick : '#picker',
    		
    		fileVal : 'filenameUploader',
    		
			//	No Timeout
			timeout: 0,
			
			//	file Num Limit	
			fileNumLimit : 1,
			
    		// 只允许选择图片文件。
			accept: {
    			title: 'Images',
    			extensions: 'gif,jpg,jpeg,bmp,png',
    			mimeTypes: 'image/jpg,image/jpeg,image/png'
			},
    		fileQueued : function(file) {
            	var $list = $('#fileList');
            	$list.append('<div id="' + file.id + '" class="item">'
                    + '<h4 class="info">' + file.name + '</h4>'
                    + '<p class="state">等待上传...</p>' + '</div>');
            	
            	$info = $list.find('.info'),
    			$image_name = $("#coverimage_name"),
    	        //$img = $list.find('img');
    			
//    			$info.css('width', 560px);
    			$list.css('width', "520px");
    			
    	    	// $list为容器jQuery实例
    	    	//$list.append($li);

    	    	//	save file name
    	    	$image_name.val(file.name);
    	    	
    	    	// 创建缩略图
    	    	// 如果为非图片文件，可以不用调用此方法。
    	    	// thumbnailWidth x thumbnailHeight 为 100 x 100
    	    	//uploader.makeThumb(file, function(error, src) {
    	        //	if 	(error) {
    	        //    	$img.replaceWith('<span>不能预览</span>');
    	        //    	return;
    	        //	}
				//
    	        //	$img.attr('src', src);
    	    	//}, thumbnailWidth, thumbnailHeight);            	
        	},
        	uploadProgress : function(file, percentage) {
            	var $li = $('#' + file.id), $percent = $li.find('.progress .progress-bar');

            	// 避免重复创建
            	if (!$percent.length) {
                	$percent = $('<div class="progress progress-striped active">'
                                + '<div class="progress-bar" id="bar" role="progressbar" style="width: 0%">'
                                + '</div>' + '</div>')
                        .appendTo($li).find('.progress-bar');
            	}
            
            	$li.find('p.state').text('上传中');
            
            	var bar = document.getElementById("bar");
				var percentComplete = Math.round(percentage * 100);
						
				bar.style.width = percentComplete + '%';
        		bar.innerHTML = percentComplete + ' % complete';
        	
            	$percent.css('width', percentage * 100 + '%');
        	},
        	uploadSuccess : function(file, response) {
            	$('#' + file.id).find('p.state').text('已上传');
            
            	//alert(response._raw);            
				//
				//	alert("name:" + response.name + ",url:" + response.url + ",status:" + response.status + ",upload_id:" + response.upload_id);
				
				$("#coverimage_id").val(response.upload_id);
				$("#coverimage_name").val(response.name);
        	},
        	uploadError : function(file, reason) {
            	$('#' + file.id).find('p.state').text('上传出错, reason:' + reason);
        	},
        	uploadComplete : function(file) {
            	$('#' + file.id).find('.progress').fadeOut();
        	}
    	});
    
    	$('#ctlBtn').on('click', function() {
        	uploader.upload();
    	});
	});
	
</script>

<script>
var BASE_URL = '${ctx}';
</script>

</body>
</html>