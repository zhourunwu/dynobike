<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消息指令管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/webpage/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 1,column:1}).show();

		});

		function refresh(){//刷新
			
			window.location="${ctx}/sports/message";
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
			<h5>消息指令列表 </h5>
			<div class="ibox-tools">
				<a class="collapse-link">
					<i class="fa fa-chevron-up"></i>
				</a>
				<a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
					<i class="fa fa-wrench"></i>
				</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#">选项1</a>
					</li>
					<li><a href="#">选项2</a>
					</li>
				</ul>
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
	</div>
    <div class="ibox-content">
	<sys:message content=""/>

			<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">

		<form:form id="searchForm" modelAttribute="oaNotify" action="${ctx}/sports/message" method="post" class="form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		</form:form>

		<div class="pull-left">
				<table:addRow url="${ctx}/sports/message/form" width="800px" height="300px" title="消息指令"></table:addRow><!-- 增加按钮 -->

			<%--<shiro:hasPermission name="sports:device:edit">--%>
			    <%--<table:editRow url="${ctx}/sports/device/form" width="800px" height="300px" id="treeTable"  title="消息"></table:editRow><!-- 编辑按钮 -->--%>
			<%--</shiro:hasPermission>--%>
			<%--<shiro:hasPermission name="sports:device:del">--%>
				<%--<table:delRow url="${ctx}/sports/device/delete" id="treeTable"></table:delRow><!-- 删除按钮 -->--%>
			<%--</shiro:hasPermission>--%>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
		</div>

	</div>
	</div>
			<table id="treeTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
				<thead><tr><th>目标类型</th><th>目标名称</th><th class="col-sm-6" style="text-align:center;">消息内容</th><th>状态</th><th>创建时间</th><th>接收时间</th></tr></thead>
				<tbody><c:forEach items="${page.list}" var="msg">
					<tr>
						<td nowrap>
							<c:choose>
								<c:when test="${msg.targetType == 0}">全体设备</c:when>
								<c:when test="${msg.targetType == 1}">厂商</c:when>
								<c:when test="${msg.targetType == 2}">所有者</c:when>
								<c:when test="${msg.targetType == 3}">指定设备</c:when>
								<c:otherwise> </c:otherwise>
							</c:choose>
						</td>
						<td title="${msg.targetName}">${fns:abbr(msg.targetName,16)}</td>
						<td title="${msg.message}" style="text-align:center;">${fns:abbr(msg.message,30)}</td>
						<td title="${msg.status}">
							<c:choose>
								<c:when test="${msg.status == 0}">服务端创建</c:when>
								<c:when test="${msg.status == 1}">客户端已接受</c:when>
							</c:choose>
						</td>
						<td title="${msg.createTime}"><fmt:formatDate value="${msg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td title="${msg.receiveTime}"><fmt:formatDate value="${msg.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach></tbody>
			</table>
		<table:page page="${page}"></table:page>
		<br/>
		<br/>
	 </div>
	</div>
	</div>
</body>
</html>