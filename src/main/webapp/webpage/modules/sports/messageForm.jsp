<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消息指令</title>
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
			$("#targetId,#targetName").val(0);
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

		function changeType(){
			var v = $("#targetType").val();
			$("#targetButton").attr({ class:"btn btn-primary"});
			//避免重复绑定事件
			$("#targetButton, #targetName").unbind("click");
			//清除已选值
			$("#targetName, #targetId").val("");
			switch(Number(v)) {
				case 0:
					//全体设备
					$("#targetButton").attr({ class:"btn btn-primary disabled "});
					$("#targetId,#targetName").val(0);
					break;
				case 1:
					//制造商
					$("#targetButton, #targetName").click(function(){
						// 正常打开
						top.layer.open({
							type: 2,
							area: ['300px', '420px'],
							title:"选择目标",
							ajaxData:{selectIds: $("#targetId").val()},
							content: "/a/tag/treeselect?url="+encodeURIComponent("/sports/device/manufacturerData")+"&module=&checked=&extId=&isAll=" ,
							btn: ['确定', '关闭']
							,yes: function(index, layero){ //或者使用btn1
								var tree = layero.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
								var ids = [], names = [], nodes = [];
								if ("" == "true"){
									nodes = tree.getCheckedNodes(true);
								}else{
									nodes = tree.getSelectedNodes();
								}
								for(var i=0; i<nodes.length; i++) {//
									ids.push(nodes[i].id);
									names.push(nodes[i].name);//
									break; // 如果为非复选框选择，则返回第一个选择
								}
								$("#targetId").val(ids.join(",").replace(/u_/ig,""));
								$("#targetName").val(names.join(","));
								$("#targetName").focus();
								top.layer.close(index);
							},
							cancel: function(index){ //或者使用btn2
								//按钮【按钮二】的回调
							}
						});

					});
					break;
				case 2:
					//所有者
					$("#targetButton, #targetName").click(function(){
						// 正常打开
						top.layer.open({
							type: 2,
							area: ['300px', '420px'],
							title:"选择目标",
							ajaxData:{selectIds: $("#targetId").val()},
							content: "/a/tag/treeselect?url="+encodeURIComponent("/sports/device/ownerData")+"&module=&checked=&extId=&isAll=" ,
							btn: ['确定', '关闭']
							,yes: function(index, layero){ //或者使用btn1
								var tree = layero.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
								var ids = [], names = [], nodes = [];
								if ("" == "true"){
									nodes = tree.getCheckedNodes(true);
								}else{
									nodes = tree.getSelectedNodes();
								}
								for(var i=0; i<nodes.length; i++) {//
									ids.push(nodes[i].id);
									names.push(nodes[i].name);//
									break; // 如果为非复选框选择，则返回第一个选择
								}
								$("#targetId").val(ids.join(",").replace(/u_/ig,""));
								$("#targetName").val(names.join(","));
								$("#targetName").focus();
								top.layer.close(index);
							},
							cancel: function(index){ //或者使用btn2
								//按钮【按钮二】的回调
							}
						});

					});
					break;
				case 3:
					//设备
					$("#targetButton, #targetName").click(function(){
						// 正常打开
						top.layer.open({
							type: 2,
							area: ['300px', '420px'],
							title:"选择目标",
							ajaxData:{selectIds: $("#targetId").val()},
							content: "/a/tag/treeselect?url="+encodeURIComponent("/sports/device/deviceData")+"&module=&checked=&extId=&isAll=" ,
							btn: ['确定', '关闭']
							,yes: function(index, layero){ //或者使用btn1
								var tree = layero.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
								var ids = [], names = [], nodes = [];
								if ("" == "true"){
									nodes = tree.getCheckedNodes(true);
								}else{
									nodes = tree.getSelectedNodes();
								}
								for(var i=0; i<nodes.length; i++) {//
									ids.push(nodes[i].id);
									names.push(nodes[i].name);//
									break; // 如果为非复选框选择，则返回第一个选择
								}
								$("#targetId").val(ids.join(",").replace(/u_/ig,""));
								$("#targetName").val(names.join(","));
								$("#targetName").focus();
								top.layer.close(index);
							},
							cancel: function(index){ //或者使用btn2
								//按钮【按钮二】的回调
							}
						});

					});
					break;
			}
		}
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="message" action="${ctx}/sports/message/saveOrUpdate" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active">	<label class="pull-right"><font color="red">*</font>目标类型：</label></td>
		         <td class="width-35" >
					 <select class="form-control required" id="targetType" name="targetType" onchange="changeType()">
						 <option value="0">全体设备</option>
						 <option value="1">厂商</option>
						 <option value="2">所有者</option>
						 <option value="3">指定设备</option>
					 </select>
				 </td>
		         <td  class="width-15 active">	<label class="pull-right"><font color="red">*</font>指令目标：</label></td>
				  <td class="width-35" ><sys:treeselect id="target" name="targetId" value="${message.targetId}" labelName="message.targetId" labelValue="${message.targetId}"
														disabled="disabled" title="目标" url="/sports/device/manufacturerData" cssClass="form-control required"/>
		      </tr>
		       <tr>
		         <td  class="width-15 active">	<label class="pull-right">消息指令：</label></td>
		         <td class="width-35" colspan="3" ><form:textarea path="message" htmlEscape="false" rows="6" maxlength="4000" class="form-control"/></td>
		         </td>
		      </tr>
		</tbody>
		</table>
	</form:form>
</body>
</html>