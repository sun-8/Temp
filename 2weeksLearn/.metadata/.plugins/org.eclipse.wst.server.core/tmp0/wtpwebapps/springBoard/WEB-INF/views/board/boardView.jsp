<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<script>

	function del(){
		var param = $j("#submit");
		
		if(!confirm("정말 삭제하시겠습니까?")){
			alert("취소");
		}else{
			$j.ajax({
		    	url : "/board/boardDelAction.do?boardType=${boardType}&boardNum=${boardNum}",
		    	dataType: "json",
		    	type: "POST",
		    	data : param,
		    	success: function(data, textStatus, jqXHR)
		    	{
		    		if(data.success == "N"){
		    			alert("이미 삭제된 게시물입니다.");
		    		}else{
		    			alert("삭제가 완료되었습니다.");
		    		}
		    		
					alert("메세지:"+data.success);
				
					location.href = "/board/boardList.do";
		    	},
		    	error: function (jqXHR, textStatus, errorThrown)
		    	{
		    		alert("실패");
		    	}
			});
		}
	
		
	}
</script>
<body>
<form Method="post">
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Type
					</td>
					<td width="400" id="title">
						${board.code_name}
					</td>
				</tr>
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400" id="title">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td id=comment>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<input type="button" onclick="location.href='/board/boardModify.do?boardType=${boardType}&boardNum=${boardNum}'" value="수정">
			<input type="button" onclick="del();" id="submit" value="삭제"> 
			<input type="button" onclick="location.href='/board/boardList.do'" value="목록">
		</td>
	</tr>
</table>	
</form>
</body>
</html>