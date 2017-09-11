<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"></c:set>

<!DOCTYPE html>
<head>
<title>Add Video Form</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>

<script src="${ctx}/resources/js/jquery.js"></script>

<script src="${ctx}/resources/js/plupload.full.min.js"></script>

<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


<script src="${ctx}/resources/js/qiniu.min.js"></script>

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
<form:form commandName="videotoAdd" action="${ctx}/a/sports/video/VideoAddSave" method="post">
    <fieldset>
        <legend>Video Basic Info</legend>
        <form:hidden path="create_time" />
        <form:hidden path="lastmodifytime" />
        <form:hidden path="route_id" />
        <form:hidden id="coverimage_name" path="cover_image.img_name" />
        <form:hidden id="videofile_url"  path="videofile_url"  />
        
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
            <form:input id="price" path="price"/>
        </p>

		<br />
		<br />

		<div id="videoContainer">
			<a class="btn btn-default btn-lg " id="picker" href="#" >
               	<i class="glyphicon glyphicon-plus"><span>选择文件</span></i>
			</a>     

			<input type="button" value="开始上传" id="upload-btn" class="btn btn-default" /> 				
   		</div>
			
		<!--用来存放文件信息-->
   		<div id="file-list" class="uploader-list"></div>	
    		               
		<br />
		<br />
		<br />
		
        <div id="buttons" style="display: block; float:right; margin-right: 5px; ">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5" class="btn btn-primary" value="Add Video">
        </div>
        
    </fieldset>
</form:form>
</div>

<script>

	//	引入Plupload 、qiniu.js后
	var uploader = Qiniu.uploader({
    	runtimes: 'html5,flash,html4',    //上传模式,依次退化
    	browse_button: 'picker',       //上传选择的点选按钮，**必需**
    	uptoken_url: 'http://localhost:9090/dynobike/a/sports/resmgmt/getUpToken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    	// uptoken : '', //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
    	// unique_names: true, // 默认 false，key为文件名。若开启该选项，SDK为自动生成上传成功后的key（文件名）。
    	save_key: true,   // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
    	domain: 'http://on20afvyf.bkt.clouddn.com',   //bucket 域名，下载资源时用到，**必需**
		//domain: 'dynobike-test',
    	get_new_uptoken: false,  //设置上传文件的时候是否每次都重新获取新的token
    	container: 'videoContainer',           //上传区域DOM ID，默认是browser_button的父元素，
    	max_file_size: '5000mb',           //最大文件体积限制
    	flash_swf_url: '${ctx}/resources/js/Moxie.swf',  //引入flash,相对路径
    	max_retries: 3,                   //上传失败最大重试次数
    	dragdrop: false,                   //开启可拖曳上传
    	drop_element: 'videoContainer',   //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
    	chunk_size: '4mb',                //分块上传时，每片的体积
    	auto_start: false,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
		multi_selection: false,
    	filters: {
            mime_types : [ //只允许上传图片和zip文件
                { title : "图片文件", extensions : "jpg,gif,png" },
                { title : "Mp4文件", extensions : "mp4" },
            ],
        },
    	init: {
        	'FilesAdded': function(up, files) {
            	plupload.each(files, function(file) {
                	// 文件添加进队列后,处理相关的事情
					
					var file_name = file.name; //文件名
					
					//	构造html来更新UI
					var html = '<li id="file-' + file.id +'"><p class="file-name">' + file_name + '</p><p id= "progress_bar" class="progress"></p></li>';
						$(html).appendTo('#file-list');
            	});
        	},
        	'BeforeUpload': function(up, file) {
               // 每个文件上传前,处理相关的事情
        	},
        	'UploadProgress': function(up, file) {
               // 每个文件上传时,处理相关的事情
			   //alert('UploadProgress');
			   $('#file-'+file.id+' .progress').css('width',file.percent + '%');//控制进度条
			   $('#progress_bar').html(file.percent + '%');
        	},
        	'FileUploaded': function(uploader, file, info) {
               // 每个文件上传成功后,处理相关的事情
               // 其中 info 是文件上传成功后，服务端返回的json，形式如
               // {
               //    "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
               //    "key": "gogopher.jpg"
               //  }
               // 参考http://developer.qiniu.com/docs/v6/api/overview/up/response/simple-response.html

               // var domain = up.getOption('domain');
               // var res = parseJSON(info);
               // var sourceLink = domain + res.key; 获取上传成功后的文件的Url
			   
			   $('#file-'+file.id+' .progress').css('display', 'none');//控制进度条
			   
			   var domain = uploader.getOption('domain');			   
			   var res = JSON.parse(info);
			   var sourceLink = domain + "//" + res.key; //	获取上传成功后的文件的Url
			   
			   //alert('FileUploaded!, source link : ' + sourceLink);
			   $('#videofile_url').val(sourceLink);
        	},
        	'Error': function(up, err, errTip) {
               //上传出错时,处理相关的事情
			   alert('Error');
        	},
        	'UploadComplete': function() {
               //队列文件处理完毕后,处理相关的事情
			   
			   //alert('UploadComplete');
        	},
        	'Key': function(up, file) {
            	// 若想在前端对每个文件的key进行个性化处理，可以配置该函数
            	// 该配置必须要在 unique_names: false , save_key: false 时才生效

            	var key = "";
            	// do something with key here
            	return key
        	}
    	}
	});
	
	//上传按钮
	$('#upload-btn').click(function(){
		uploader.start(); //开始上传
	});
	
</script>

<script>
var BASE_URL = '${ctx}';
</script>

</body>
</html>