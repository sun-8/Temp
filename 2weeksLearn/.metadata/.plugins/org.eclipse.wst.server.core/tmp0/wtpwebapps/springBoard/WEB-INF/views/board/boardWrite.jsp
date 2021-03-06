<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j(".hide").hide();

		var $n = "1";
		$n++;
		/*
		var $a는 jQuery변수
		jQuery에서 사용하는 내장 함수들을 모두 사용할 수 있다.
		jQuery에서만 사용하는 메서드가 있는데 일반적인 javascript변수로는 적용이 안된다.
		jQuery변수는 모든 스크립트 사용이 가능
		*/
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite tbody tbody :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do?pageNo=${pageNo}";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});

		//행추가
		$j("#row").on("click",function(){
//			$j(".tbody:last").after($j(".tbody").html());
//			$j(".del").show();
			
			$j("#writer").before($j(".hide").html());
			
			$j(".hide:last tr:first-child select").attr("name","boardVoList["+$n+"].boardType");
			$j(".hide:last tr:nth-child(2) input").attr("name","boardVoList["+$n+"].boardTitle");
			$j(".hide:last tr:last-child textarea").attr("name","boardVoList["+$n+"].boardComment");
			//기존에 만들었던 .hide는 hide()처리가 되었기 때문에 요소를 숨겨서 요소가 없는 것으로 인식하는 것 같다.
			//그래서 :last를 적용해도 마지막 요소의 name이 바뀌지 않는 것 같다.
			
			$n++;
			
			
		});
		
		//행삭제
		$j(document).on("click","button[class='del']",function delHtml(){
			$j(this).parent().parent().next().next().remove();
			$j(this).parent().parent().next().remove();
			$j(this).parent().parent().remove();
			
//			$n=1;
//			
//			for(var $i=1; $i<$j(".hide").length; i++){
//				$j(".hide:nth-child("+$i+") tr:first-child select").attr("name","boardVoList["+$n+"].boardType");
//				$j(".hide:nth-child("+$i+") tr:nth-child(2) input").attr("name","boardVoList["+$n+"].boardTitle");
//				$j(".hide:nth-child("+$i+") tr:last-child textarea").attr("name","boardVoList["+$n+"].boardComment");
//				
//				$i++;
//				$n++;
//			}
		});
		/*
			동적 태그일 경우, document.ready의 이벤트가 작동하지 x 
			document.ready는 화면이 최조에 로드 되었을 때 화면 안에 있는 태그들에 이벤트를 건다.
			그래서 로드 되었을 때 존재하지 않는 태그에 대해서 이벤트를 걸 수 없는 것이다
		*/

	});

</script>
<body>
<form class="boardWrite">
	
	<table align="center">
		<tr>
			<td align="right">
			<input id="submit" type="button" value="작성">
			<input id="row" type="button" value="행추가">
			</td>
		</tr>
		<tr class="write">
			<td>
				<table border ="1" align="center" id="test">
					<tr class="type">
						<td width="120" align="center">
						Type
						</td>
						<td>
							<select name="boardVoList[0].boardType">
								<c:forEach items="${boardTypeList}" var="type">
									<option value="${type.code_id}">${type.code_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
						<input name="boardVoList[0].boardTitle" type="text" size="50" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardVoList[0].boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
						</td>
					</tr>
					<tr id="writer">
						<td align="center">
						Writer
						</td>
						<td>
						</td>
					</tr>
					
					
					<thead class="hide">
					<tr class="type">
						<td width="120" align="center">
						Type
						</td>
						<td>
							<select name="boardVoList[1].boardType">
								<c:forEach items="${boardTypeList}" var="type">
									<option value="${type.code_id}">${type.code_name}</option>
								</c:forEach>
							</select>
							<button class="del" style="float:right;">삭제</button>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
						<input name="boardVoList[1].boardTitle" type="text" size="50" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardVoList[1].boardComment" rows="20" cols="55">${board.boardComment}</textarea>
						</td>
					</tr>
					</thead>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>

</form>	
</body>
</html>