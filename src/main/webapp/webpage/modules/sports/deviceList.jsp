<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
			
			window.location="${ctx}/sports/device";
		}
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
			<h5>设备列表 </h5>
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
	<sys:message content="${message}"/>

			<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">

				<table:addRow url="${ctx}/sports/device/form" width="800px" height="340px" title="设备"></table:addRow><!-- 增加按钮 -->

			<%--<shiro:hasPermission name="sports:device:edit">--%>
			    <table:editRow url="${ctx}/sports/device/form" width="800px" height="340px" id="treeTable"  title="设备"></table:editRow><!-- 编辑按钮 -->
			<%--</shiro:hasPermission>--%>
			<%--<shiro:hasPermission name="sports:device:del">--%>
				<table:delRow url="${ctx}/sports/device/deleteAll" id="treeTable"></table:delRow><!-- 删除按钮 -->
			<%--</shiro:hasPermission>--%>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
		</div>
		<div id="div-togle-search" class="pull-right">
			<form method="post" id="searchForm" action="${ctx}/sports/device">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<table cellspacing="3" cellpadding="4">
					<tr>
						<td>
							<input id="searchName" type="text" class="form-control" placeholder="请输入查询内容..."   onkeydown="keybDown(event)"  value="${device.searchName}" />
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
				<thead><tr><th><input type="checkbox" class="i-checks"></th><th>设备类型</th><th>所属厂商</th><th>所有者</th><th>设备编号</th><th style="text-align:center;">备注</th><th style="text-align:center;">操作</th></tr></thead>
				<tbody><c:forEach items="${page.list}" var="menu">
					<tr id="${menu.id}">
						<td> <input type="checkbox" singleSelect="true" id="${menu.id}" class="i-checks"></td>
						<td nowrap>${menu.typeName}</td>
						<td title="${menu.manufacturerName}">${fns:abbr(menu.manufacturerName,30)}</td>
						<td title="${menu.ownerName}">${fns:abbr(menu.ownerName,30)}</td>
						<td title="${menu.uuid}">${fns:abbr(menu.uuid,30)}</td>
						<td title="${menu.note}" style="text-align:center;">${fns:abbr(menu.note,30)}</td>
						<td nowrap>
								<a href="#" onclick="openDialogView('设备详情', '${ctx}/sports/device/form?id=${menu.id}','800px', '340px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
								<a href="#" onclick="openDialog('修改设备', '${ctx}/sports/device/form?id=${menu.id}','800px', '340px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
								<a href="${ctx}/sports/device/delete?id=${menu.id}" onclick="return confirmx('确定要删除该设备吗？', this.href)" class="btn btn-danger btn-xs" ><i class="fa fa-trash"></i> 删除</a>
						</td>
					</tr>
				</c:forEach></tbody>
			</table>
		<table:page page="${page}"></table:page>
		<br />
		<br />
<%--		<div class="page" id="pager" >
		</div>--%>
	 </div>
	</div>
	</div>
</body>
</html>