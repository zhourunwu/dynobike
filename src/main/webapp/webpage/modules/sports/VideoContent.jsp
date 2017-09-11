<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/webuploader.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>优芯微运动</title>

<link href="${ctxStatic}/svw_main/svw_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctxStatic}/flowplayer/jquery-1.8.3.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/flowplayer/jquery-ui.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/flowplayer/showhide.js"></script>  
<script type="text/JavaScript" src="${ctxStatic}/flowplayer/jquery.mousewheel.js"></script> 

<!-- Flow Player -->
<!--  <script type="text/javascript" src="videoplayer/flowplayer-3.2.8.min.js"></script> -->

<!--引入JS-->
<script src="${ctxStatic}/flowplayer/flowplayer-3.2.8.min.js"></script>

</head>

<body id="subpage">

<div id="svw_main">
	<div id="content">
    	<div class="post">
    	
            <h2>${video.video_name}</h2>
	    	
	    	<!--  
            <div id="player_window">

				<a id="player" href="${video.videofile_url}"></a>
				<script>
					flowplayer("player", "${ctxStatic}/flowplayer/flowplayer-3.2.8.swf"); 
				</script>
			</div>
			-->
			
			<div id="player_window">
				<video id="video" class="video-js vjs-default-skin vjs-big-play-centered"  
						controls preload="auto" width="100%" height="100%">
					<source src="${video.videofile_url}" type='video/mp4' />					
				</video>  
			</div>
			
            <div class="meta">
                <span class="content"><a href="${ctx}/sports/video/VideoReadByID?video_id=${video.video_id}"><spring:message code="video.content"/></a></span>                
                <div class="cleaner"></div>
            </div> 
        </div>
        
        <div class="cleaner h20"></div>
            <h3><spring:message code="video.intro"/></h3>
        	<div id="comment_section">
       		<c:choose>
   				<c:when test="${empty video.video_intr}">
   					<p><spring:message code="video.intronull"/></p>
   				</c:when>
   				<c:otherwise>
   					<p>${video.video_intr}</p>
   				</c:otherwise>
	    	</c:choose> 
        </div>
        
        <div class="cleaner h20"></div>
        
    </div>
    
    <div id="sidebar">
        <ul class="svw_list">
        	<li><a href="${ctx}/sports/route/RouteReadByID?route_id=${video.route_id}"><spring:message code="video.return"/></a></li>
        	<li><a href="${ctx}/sports/video/VideoReadByID?video_id=${video.video_id}"><spring:message code="video.content"/></a></li>
        </ul>
        
        <div class="cleaner h30"></div>

<!--  
        <s:action name="SidebarRecent" executeResult="true">
           	<s:param name="num">5</s:param>
        </s:action>
-->
        
    </div> <!-- end of sidebar -->
    <div class="cleaner"></div>
</div> <!-- END of svw_main -->

</body>
</html>