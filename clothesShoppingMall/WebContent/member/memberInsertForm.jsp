<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	// ���̵� �ߺ� �˻�
	$('#btnIdChk').click(function() {
		let uidchk = $.trim($('#u_id').val());
		console.log(`u_id:${idchk}`);
		
		$.ajax({
			url:'./memberIdChk.jsp',
			data:{userId:uidchk},
			type:'post',
			dataType:'json',
			success:function(data){
				if(data == 1) {
					$('#idChk').text('��� ������ ���̵��Դϴ�.');
					$('#u_id').attr('disabled', true);
				} else if(data == 0) {
					$('#idChk').text('�̹� ������� ���̵� �Դϴ�.');
					u_id.val('');
				}
			}
		});
	});
	
	// ����ȣ �Է��� ���� ��Ŀ���� ���� ���
	$('#u_phone').focus(function() {
		$('#phoneChk').text('"-" ���� �Է����ּ���.');
	});
	
	function totalChk() {
		// ���̵� Ȯ��
		if(!$('#u_id').attr('disabled')) {
			$('#idChk').text('���̵� �ߺ�üũ�� ���ּ���.');
			return false;
		} else {
			$('#idChk').text('');
		}
		
		
		// ��ȣ ��ġ���� Ȯ��
		let u_pw = $.trim($('#u_pw').val());
		let u_pwChk = $.trim($('#u_pwChk').val());
		if((u_pw !=='' & u_pwChk !== '') & u_pw == u_pwChk) {
			$('#pwChk').text('�Է��� ��й�ȣ�� ��ġ�մϴ�.');
			$('#pwChk').text('��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.');
			return false;
		}
		
		
		// �̸� ���� ���� Ȯ��
		let u_name = $.trim($('#u_name').val());
		if(u_name == '') {
			$('#nameChk').text('�̸��� �Է��ϼž� �մϴ�.');
		} else {
			$('#nameChk').text('');
			return false;
		}
		
		
		// ���� üũ���� �˻�
		if(!$('input:radio[name="u_level"]').is(':checked')) {
			$('#levelChk').text('������ üũ�ϼž� �մϴ�.');
			return false;
		} else {
			$('#levelChk').text('');
		}
		
		
		// ���� üũ���� �˻�
		if(!$('input:radio[name="u_gender"]').is(':checked')) {
			$('#genChk').text('������ üũ�ϼž� �մϴ�.');
			return false;
		} else {
			$('#genChk').text('');
		}
		
		
		//����ȣ ��ȿ�� �˻�
		//let phoneReg = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
		//                      010       02    063			  ���ڴ� 3~4����  ���� �ױ���
		//					����ȣ �Ǵ� ������ȣ            
		
		let phone_1 = $('#u_phone_1').val();
		let phone_2 = $('#u_phone_2').val();
		let phone_3 = $('#u_phone_3').val();
		let phone_reg = /^(01[016789]{1})$/;
		let phone_back_reg = /^([0-9]{4})$/;
		if(phone_reg.test(phone_1) &
		   phone_back_reg.test(phone_2) &
		   phone_back_reg.test(phone_3)) {
			$('#phoneChk').text('');
		} else {
			$('#phoneChk').text('�޴��� ��ȣ�� �߸� �Է��ϼ̽��ϴ�.');
			return false;
		}
		
		
		let emailChk = false;
		// �̸��� ��ȿ�� �˻�
		// ���̵�� ù���ڰ� ���ڷ� �����ϸ� �ȵȴ�.
		let email_id = $('#u_email_id').val();
		let emailreg = /^[0-9]{1};
		if(emailreg.test(email_id)) {
			$('#emailChk').text('�̸����� ���ڷ� ������ �� �����ϴ�!');
			return false;
		} else {
			$('#emailChk').text('');
		}
		
		// �̸��� ������ ��ȿ�� �˻�
		// �������� .�ڿ� �ݵ�� net�� com �̾�߸� �Ѵ�.
		// ��� ��ġ�� ��� ajax�� u_email ���������� �� ������
		let email_domain = $('#u_email_domain').val();
		
		
		
		// �ּ� ���鿩�� �˻�
		let addr = $.trim($('#u_address').val());
		if(addr !== '') {
			totalChk = true;
			$('#addrChk').text('');
		} else {
			$('#addrChk').text('�ּҸ� �Է����ּ���.');
			return false;
		}
		
		return true;
		//let queryString = $('#memInsert').serialize();
		// id=xxx&pw=xxx&name=xxx �̷��� ��ȯ���ִ°� serialize() �̴�.
		//$('#memInsert').submit();
		// �� �±� submit�� ���� ����� �Ѵ�.
	}
</script>
</head>
<body>
<%@ include file="/module/top.jsp" %>
<%@ include file="/module/left.jsp" %>

<form action="<%=request.getContextPath()%>/member/memberInsert.jsp"
method="post" id="memInsert" onsubmit="return totalChk()">
	<table width="100%">
		<tr>
			<th>���̵�</th>
			<td>
				<input type="text" id="u_id" name="u_id">
				<button id="btnIdChk">�ߺ�Ȯ��</button>
				<div id="idChk"></div>
			</td>
		</tr>
		<tr>
			<th>��ȣ</th>
			<td><input type="password" id="u_pw" name="u_pw"></td>
		</tr>
		<tr>
			<th>��ȣ Ȯ��</th>
			<td>
				<input type="password" id="u_pwChk">
				<div id="pwChk"></div>
			</td>
		</tr>
		<tr>
			<th>�̸�</th>
			<td>
				<input type="text" id="u_name" name="u_name">
				<div id="nameChk"></div>
			</td>
		</tr>
		<tr>
			<th>���� ����</th>
			<td>
				<input type="radio" name="u_level" value="�Ǹ���">�Ǹ��� |
				<input type="radio" name="u_level" value="������">������
				<div id="levelChk"></div>
			</td>
		</tr>
		<tr>
			<th>����</th>
			<td>
				<input type="radio" name="u_gender" value="woman">���� |
				<input type="radio" name="u_gender" value="man">����
				<div id="genChk"></div>
			</td>
		</tr>
		<tr>
			<th>����ȣ</th>
			<td>
				<input type="text" id="u_phone_1" name="u_phone_1">-
				<input type="text" id="u_phone_2" name="u_phone_2">-
				<input type="text" id="u_phone_3" name="u_phone_3">
				<div id="phoneChk"></div>
			</td>
		</tr>
		<tr>
			<th>�̸���</th>
			<td>
				<input type="text" id="u_email_id" name="u_email_id">@
				<input type="text" id="u_email_domain" name="u_email_domain">
				<div id="emailChk"></div>
			</td>
		</tr>
		<tr>
			<th>�ּ�</th>
			<td>
				<input type="text" name="u_address">
				<div id="addrChk"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button">�����ϱ�</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/module/bottom.jsp" %>
</body>
</html>
