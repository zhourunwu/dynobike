<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备类型管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/webpage/include/treetable.jsp" %>
	<script src="${pageContext.request.contextPath}/resources/js/pager/jquery.pager.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/resources/js/pager/Pager.css" rel="stylesheet"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 1,column:1}).show();

		});

		function search() {
			$("#pageNumber").val("1");
			$("#hid_search").val(encodeURI($("#searchName").val()));
			$("#searchForm").submit();
		}
		function searchToggle(){
			$("#div-togle-search").toggle(500);
		}
		/*按下回车进行搜索*/
		function keybDown(e) {
			var ev= window.event||e;
			if (ev.keyCode == 13){
				search();
			}
		}
		function refresh(){//刷新
			window.location="${ctx}/sports/deviceType";
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
			<h5>设备类型列表 </h5>
			<div class="ibox-tools">
				<a class="collapse-link">
					<i class="fa fa-chevron-up"></i>
				</a>
				<a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
					<i class="fa fa-wrench"></i>
				</a>
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
	</div>
    <div class="ibox-content">
	<sys:message content="${message}"/>

			<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">

				<table:addRow url="${ctx}/sports/deviceType/form" title="设备类型" width="800px" height="200px" ></table:addRow><!-- 增加按钮 -->

			<%--<shiro:hasPermission name="sports:device:edit">--%>
			    <table:editRow url="${ctx}/sports/deviceType/form" id="treeTable" title="设备类型"  width="800px" height="200px"></table:editRow><!-- 编辑按钮 -->
			<%--</shiro:hasPermission>--%>
			<%--<shiro:hasPermission name="sports:device:del">--%>
				<table:delRow url="${ctx}/sports/deviceType/deleteAll" id="treeTable"></table:delRow><!-- 删除按钮 -->
			<%--</shiro:hasPermission>--%>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
		</div>
		<div id="div-togle-search" class="pull-right">
			<form method="post" id="searchForm" action="${ctx}/sports/deviceType">
				<table cellspacing="3" cellpadding="4">
					<tr>
						<td>
							<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
							<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
							<input id="searchName" type="text" class="form-control" placeholder="请输入查询内容..."   onkeydown="keybDown(event)"  value="${deviceType.searchName}" />
							<input type="hidden" name="searchName" id="hid_search" />
						</td>
						<td><a href="javascript:void(0);"> <button type="button" class="btn btn-large btn-success"  onclick="search();">查询</button></a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</div>
			<table id="treeTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
				<thead><tr><th><input type="checkbox" class="i-checks"></th><th class="col-sm-6" style="text-align:center;">设备类型</th><th style="text-align:center;">操作</th></tr></thead>
				<tbody><c:forEach items="${page.list}" var="menu">
					<tr id="${menu.id}">
						<td> <input type="checkbox" id="${menu.id}" class="i-checks"></td>
						<td nowrap style="text-align:center;">${menu.typeName}</td>
						<td nowrap style="text-align:center;">
								<a href="#" onclick="openDialogView('设备类型详情', '${ctx}/sports/deviceType/form?id=${menu.id}','800px', '200px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
								<a href="#" onclick="openDialog('修改设备类型', '${ctx}/sports/deviceType/form?id=${menu.id}','800px', '200px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
								<a href="${ctx}/sports/deviceType/delete?id=${menu.id}" onclick="return confirmx('您确定要删除 ${menu.typeName} 设备类型吗？', this.href)" class="btn btn-danger btn-xs" ><i class="fa fa-trash"></i> 删除</a>
						</td>
					</tr>
				</c:forEach></tbody>
			</table>
		<table:page page="${page}"></table:page>
		<br />
		<br />
	 </div>
	</div>
	</div>
</body>
</html>