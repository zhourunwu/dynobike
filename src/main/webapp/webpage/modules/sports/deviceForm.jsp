<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
			$("#name").focus();
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


	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="device" action="${ctx}/sports/device/saveOrUpdate" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active"><font color="red">*</font><label class="pull-right">设备类型:</label></td>
		         <td class="width-35" ><sys:treeselect id="typeId" name="typeId" value="${device.typeId}" labelName="device.typeId" labelValue="${device.typeName}"
					title="设备类型" url="/sports/device/typeData" cssClass="form-control required"/></td>
				  <td  class="width-15 active"><font color="red">*</font><label class="pull-right">所属厂商:</label></td>
				  <td class="width-35" ><sys:treeselect id="manufacturerId" name="manufacturerId" value="${device.manufacturerId}" labelName="device.manufacturerId" labelValue="${device.manufacturerName}"
														title="厂商" url="/sports/device/manufacturerData" cssClass="form-control required"/></td>
		      </tr>
			  <tr>
				  <td  class="width-15 active"><font color="red">*</font><label class="pull-right">设备编号:</label></td>
				  <td  class="width-35"><form:input path="uuid" htmlEscape="false" maxlength="50" class="required form-control "/></td>
				  <td  class="width-15 active"><font color="red">*</font><label class="pull-right">所有者:</label></td>
				  <td class="width-35" ><sys:treeselect id="ownerId" name="ownerId" value="${device.ownerId}" labelName="device.ownerId" labelValue="${device.ownerName}"
														title="厂商" url="/sports/device/ownerData" cssClass="form-control required"/></td>
			  </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-100" colspan="3"><form:textarea path="note" htmlEscape="false" rows="5" maxlength="200" class="form-control "/></td>
		         <%--<td  class="width-15 active"><label class="pull-right"></label></td>
		         <td  class="width-35" ></td>--%>
		      </tr>
		    </tbody>
		  </table>
	</form:form>
</body>
</html>