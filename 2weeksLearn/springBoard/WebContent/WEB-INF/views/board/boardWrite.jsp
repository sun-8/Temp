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
		var $a�� jQuery����
		jQuery���� ����ϴ� ���� �Լ����� ��� ����� �� �ִ�.
		jQuery������ ����ϴ� �޼��尡 �ִµ� �Ϲ����� javascript�����δ� ������ �ȵȴ�.
		jQuery������ ��� ��ũ��Ʈ ����� ����
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
					alert("�ۼ��Ϸ�");
					
					alert("�޼���:"+data.success);
					
					location.href = "/board/boardList.do?pageNo=${pageNo}";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("����");
			    }
			});
		});

		//���߰�
		$j("#row").on("click",function(){
//			$j(".tbody:last").after($j(".tbody").html());
//			$j(".del").show();
			
			$j("#writer").before($j(".hide").html());
			
			$j(".hide:last tr:first-child select").attr("name","boardVoList["+$n+"].boardType");
			$j(".hide:last tr:nth-child(2) input").attr("name","boardVoList["+$n+"].boardTitle");
			$j(".hide:last tr:last-child textarea").attr("name","boardVoList["+$n+"].boardComment");
			//������ ������� .hide�� hide()ó���� �Ǿ��� ������ ��Ҹ� ���ܼ� ��Ұ� ���� ������ �ν��ϴ� �� ����.
			//�׷��� :last�� �����ص� ������ ����� name�� �ٲ��� �ʴ� �� ����.
			
			$n++;
			
			
		});
		
		//�����
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
			���� �±��� ���, document.ready�� �̺�Ʈ�� �۵����� x 
			document.ready�� ȭ���� ������ �ε� �Ǿ��� �� ȭ�� �ȿ� �ִ� �±׵鿡 �̺�Ʈ�� �Ǵ�.
			�׷��� �ε� �Ǿ��� �� �������� �ʴ� �±׿� ���ؼ� �̺�Ʈ�� �� �� ���� ���̴�
		*/

	});

</script>
<body>
<form class="boardWrite">
	
	<table align="center">
		<tr>
			<td align="right">
			<input id="submit" type="button" value="�ۼ�">
			<input id="row" type="button" value="���߰�">
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
							<button class="del" style="float:right;">����</button>
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