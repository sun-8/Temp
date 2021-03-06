<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j.ajax({
	    	url : "/board/boardListSort",
	    	dataType: "json",
	    	type: "GET",
	    	success: function(data, textStatus, jqXHR)
	    	{
	    		for(var i=0; i<data.success.length; i++){
	    			
	    		}
	    		location.href="/board/boardList.do";
	    	},
	    	error: function (jqXHR, textStatus, errorThrown)
	    	{
	    		alert("????");
	    	}
		
	});
	
	function selectAll(){
		var checked = $j("input:checkbox[name='select']").is(":checked")
		if(checked){
			$j("input:checkbox[name='type']").prop("checked",true);
		}else{
			$j("input:checkbox[name='type']").prop("checked",false);
		}
	}
	
	function check(){
		var checkboxes = document.querySelectorAll("input[name='type']");
		var checked = document.querySelectorAll("input[name='type']:checked");
		var selectAll = document.getElementById("all");
		
		if(checkboxes.length === checked.length){
			selectAll.checked = true;
		}else{
			selectAll.checked = false;
		}
	}
	
	function sort(){
		var checkbox = [];
		
		$j("input[name='type']:checked").each(function(i){
			checkbox.push($j(this).val());
		});
		

		});
	}
</script>
<body>
<form action="/board/boardList.do">
<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.code_name}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">?۾???</a>
		</td>
	</tr>
</table>
<div style="text-align: center;">	
	<input type="checkbox" name="select" id="all" value="selectAll" onclick="return selectAll();">??ü&nbsp;
	
	<c:forEach items="${boardTypeList}" var="type">
		<input type="checkbox" name="type" onclick="check();" value="${type.code_id}">${type.code_name}
	</c:forEach>
	
	<input type="button" value="??ȸ" id="submit" onclick="sort();">
</div>
</form>
</body>
</html>