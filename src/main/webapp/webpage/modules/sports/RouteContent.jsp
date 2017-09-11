<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/svw.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Video, FFmpeg, JavaEE" />
<meta name="author" content="Peng Xiaoxi" />
<meta name="description" content="The simplest video website based on JavaEE and FFmpeg" />

<title>优芯微运动</title>
<link 	href="css/svw_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script> 
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script type="text/javascript" src="js/showhide.js"></script> 
<link 	rel="stylesheet" href="css/slimbox2.css" type="text/css" media="screen" /> 
<script type="text/JavaScript" src="js/jquery.mousewheel.js"></script> 
<script type="text/JavaScript" src="js/slimbox2.js"></script> 

</head>
<body>

<div id="svw_main">

	<h2>
		${route.route_name }
	</h2>
	
	<!-- 所有的运动路线的列表  ${resultroute}, 每一个有其标题，内容，展示图片等信息  -->
	
	<div class="post">
		<div class="meta">
            <span ><b><spring:message code="video.listmanage"/></b></span>
                <span class="add"><a href="${ctx}/sports/video/VideoAdd?route_id=${route.route_id}"><spring:message code="video.add"/></a></span>
            </div> 
            </div>
            <c:if test="${empty videolist}">
            <p><spring:message code="video.listempty"/></p>
            <div style="height:300px;"></div>
            </c:if>
            
            <c:forEach items="${videolist}" var="video">
			<div class="col one_fourth gallery_box" style="background:#FFFFFF">
            
                <a href="${ctx}/sports/video/VideoReadByID?video_id=${video.video_id}"><img src="${video.cover_image.img_url}" alt="thumbnail" height="200" width="200" class="image_frame"/></a>
                <h5><a href="${ctx}/sports/video/VideoReadByID?video_id=${video.video_id}">${video.video_name}</a></h5>
                <p><spring:message code="video.edittime"/>:${video.getFormattedLastmodifytime()}</p>
                <p>
                <a href="${ctx}/sports/video/VideoReadByID?video_id=${video.video_id}"><spring:message code="video.content"/></a>
                <a href="${ctx}/sports/video/VideoEditByID?video_id=${video.video_id}"><spring:message code="video.edit"/></a>
                <a href="${ctx}/sports/video/VideoDelByID?video_id=${video.video_id}"><spring:message code="video.delete"/></a>
			</div>
            </c:forEach>
            
            
            <div class="cleaner">
            </div>
            
    <div class="cleaner"></div>
    
</div> <!-- END of svw_main -->

</body>
</html>