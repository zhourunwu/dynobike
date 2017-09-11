<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>通知管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
			if(validateForm.form()){
				$("#inputForm").submit();
				return true;
			}

			return false;
		}
		$(document).ready(function() {
			//$("#name").focus();
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

		$(document).ready(function() {
			//外部js调用
			laydate({
				elem: '#birthday', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			});


		})
	</script>
</head>
<body>
<form:form id="inputForm" modelAttribute="users" action="${ctx}/stu/student/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
		<tr>
			<td  class="width-15 active">	<label class="pull-left"><font color="red">*</font>账号：</label></td>
			<td class="width-35" ><form:input path="account" htmlEscape="false" maxlength="200" class="form-control required"/></td>
			<td  class="width-15 active">	<label class="pull-left"><font color="red">*</font>微信ID：</label></td>
			<td class="width-35" ><form:input path="wxid" htmlEscape="false" maxlength="200" class="form-control required"/></td>

		</tr>
		<tr>

			<td  class="width-15 active">	<label class="pull-left"><font color="red">*</font>姓名：</label></td>
			<td class="width-35" ><form:input path="name" htmlEscape="false" maxlength="200" class="form-control required"/></td>
			<td  class="width-15 active">	<label class="pull-left"><font color="red">*</font>性别：</label></td>
			<td class="width-35" ><form:select path="sex" rows="6"  class="form-control required">
				<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"></form:options>
			</form:select></td>

		</tr>
		<tr>
			<td  class="width-15 active">	<label class="pull-left"><font color="red">*</font>电话号码：</label></td>
			<td class="width-35" ><form:input path="mobile" htmlEscape="false" maxlength="15" class="form-control required"/></td>
		</tr>
		<tr>
			<td  class="width-15 active">	<label class="pull-left">生日：</label></td>
			<td class="width-35"> <input id="birthday" name="birthday" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
										 value="<fmt:formatDate value="${users.birthday}" pattern="yyyy-MM-dd"/>"/></td>
			<td  class="width-15 active">	<label class="pull-left">创建时间：</label></td>
			<td class="width-35"><fmt:formatDate value="${users.createtime}" type="both"/></td>
		</tr>

		</tbody>
	</table>
</form:form>
</body>
</html>