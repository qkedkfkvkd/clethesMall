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
	// 아이디 중복 검사
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
					$('#idChk').text('사용 가능한 아이디입니다.');
					$('#u_id').attr('disabled', true);
				} else if(data == 0) {
					$('#idChk').text('이미 사용중인 아이디 입니다.');
					u_id.val('');
				}
			}
		});
	});
	
	// 폰번호 입력을 위해 포커스가 놓인 경우
	$('#u_phone').focus(function() {
		$('#phoneChk').text('"-" 없이 입력해주세요.');
	});
	
	function totalChk() {
		// 아이디 확인
		if(!$('#u_id').attr('disabled')) {
			$('#idChk').text('아이디 중복체크를 해주세요.');
			return false;
		} else {
			$('#idChk').text('');
		}
		
		
		// 암호 일치여부 확인
		let u_pw = $.trim($('#u_pw').val());
		let u_pwChk = $.trim($('#u_pwChk').val());
		if((u_pw !=='' & u_pwChk !== '') & u_pw == u_pwChk) {
			$('#pwChk').text('입력한 비밀번호가 일치합니다.');
			$('#pwChk').text('비밀번호가 서로 일치하지 않습니다.');
			return false;
		}
		
		
		// 이름 공백 여부 확인
		let u_name = $.trim($('#u_name').val());
		if(u_name == '') {
			$('#nameChk').text('이름을 입력하셔야 합니다.');
		} else {
			$('#nameChk').text('');
			return false;
		}
		
		
		// 권한 체크여부 검사
		if(!$('input:radio[name="u_level"]').is(':checked')) {
			$('#levelChk').text('권한을 체크하셔야 합니다.');
			return false;
		} else {
			$('#levelChk').text('');
		}
		
		
		// 성별 체크여부 검사
		if(!$('input:radio[name="u_gender"]').is(':checked')) {
			$('#genChk').text('성별을 체크하셔야 합니다.');
			return false;
		} else {
			$('#genChk').text('');
		}
		
		
		//폰번호 유효성 검사
		//let phoneReg = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
		//                      010       02    063			  숫자는 3~4글자  숫자 네글자
		//					폰번호 또는 지역번호            
		
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
			$('#phoneChk').text('휴대폰 번호를 잘못 입력하셨습니다.');
			return false;
		}
		
		
		let emailChk = false;
		// 이메일 유효성 검사
		// 아이디는 첫글자가 숫자로 시작하면 안된다.
		let email_id = $('#u_email_id').val();
		let emailreg = /^[0-9]{1};
		if(emailreg.test(email_id)) {
			$('#emailChk').text('이메일은 숫자로 시작할 수 없습니다!');
			return false;
		} else {
			$('#emailChk').text('');
		}
		
		// 이메일 도메인 유효성 검사
		// 도메인은 .뒤에 반드시 net나 com 이어야만 한다.
		// 모두 일치할 경우 ajax로 u_email 변수명으로 값 보내기
		let email_domain = $('#u_email_domain').val();
		
		
		
		// 주소 공백여부 검사
		let addr = $.trim($('#u_address').val());
		if(addr !== '') {
			totalChk = true;
			$('#addrChk').text('');
		} else {
			$('#addrChk').text('주소를 입력해주세요.');
			return false;
		}
		
		return true;
		//let queryString = $('#memInsert').serialize();
		// id=xxx&pw=xxx&name=xxx 이렇게 변환해주는게 serialize() 이다.
		//$('#memInsert').submit();
		// 폼 태그 submit와 같은 기능을 한다.
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
			<th>아이디</th>
			<td>
				<input type="text" id="u_id" name="u_id">
				<button id="btnIdChk">중복확인</button>
				<div id="idChk"></div>
			</td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" id="u_pw" name="u_pw"></td>
		</tr>
		<tr>
			<th>암호 확인</th>
			<td>
				<input type="password" id="u_pwChk">
				<div id="pwChk"></div>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" id="u_name" name="u_name">
				<div id="nameChk"></div>
			</td>
		</tr>
		<tr>
			<th>가입 유형</th>
			<td>
				<input type="radio" name="u_level" value="판매자">판매자 |
				<input type="radio" name="u_level" value="구매자">구매자
				<div id="levelChk"></div>
			</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<input type="radio" name="u_gender" value="woman">여성 |
				<input type="radio" name="u_gender" value="man">남성
				<div id="genChk"></div>
			</td>
		</tr>
		<tr>
			<th>폰번호</th>
			<td>
				<input type="text" id="u_phone_1" name="u_phone_1">-
				<input type="text" id="u_phone_2" name="u_phone_2">-
				<input type="text" id="u_phone_3" name="u_phone_3">
				<div id="phoneChk"></div>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="text" id="u_email_id" name="u_email_id">@
				<input type="text" id="u_email_domain" name="u_email_domain">
				<div id="emailChk"></div>
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text" name="u_address">
				<div id="addrChk"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button">가입하기</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/module/bottom.jsp" %>
</body>
</html>
