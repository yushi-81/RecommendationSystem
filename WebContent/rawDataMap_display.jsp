<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@ page import="LCRS.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���R�����h�V�X�e�� �f�[�^�ꗗ</title>

<script language="JavaScript">
function Check(){
	if(document.favorite_regist.name.value == ""){
		alert("���O����͂��Ă�������");return false;
	}else if(document.favorite_regist.name.value != ""){
		for (i = 0; i < document.favorite_regist.elements.length; i++){
			if (document.favorite_regist.elements[i].checked){
				status =1;
				favo ++;
			}
		}
		if(!status){
			alert("�����̂��镪���I�����Ă�������");
			return false;
		}
	}
}
</script>
</head>
<body>

<!--<form name="favorite_regist" method="post" action="simPearson.jsp" onSubmit="return Check()">
-->

<!--<form name="favorite_regist" method="post" action="simPearson2.jsp" onSubmit="return Check()">

-->
<!--<form name="favorite_regist" method="post" action="simDistance.jsp" onSubmit="return Check()">
-->

<form name="favorite_regist" method="post" action="simDistance2.jsp" onSubmit="return Check()">


<p>�����̓��͂����肢���܂�</p>
<input type="text" name="name" size=20 maxlength="20">
<p>���Ȃ��̋����̂��镪��́H�i�����j</p>
  <input type="checkbox" name="favorite" value="�R���s���[�^�T�C�G���X">�R���s���[�^�T�C�G���X<br>
  <input type="checkbox" name="favorite" value="�R���s���[�^�l�b�g���[�N">�R���s���[�^�l�b�g���[�N<br>
  <input type="checkbox" name="favorite" value="���f�����O�Z�p">���f�����O�Z�p<br>
  <input type="checkbox" name="favorite" value="�������">�������<br>
  <input type="checkbox" name="favorite" value="������b���w">������b���w<br>
  <input type="checkbox" name="favorite" value="��񐔊w">��񐔊w<br>
  <input type="checkbox" name="favorite" value="�\�t�g�E�F�A�H�w">�\�t�g�E�F�A�H�w<br>
  <input type="checkbox" name="favorite" value="�r�W���A���N���G�C�g">�r�W���A���N���G�C�g<br>
  <input type="checkbox" name="favorite" value="�l�ԏ�񏈗�">�l�ԏ�񏈗�<br>
  <input type="checkbox" name="favorite" value="�V�~�����[�V�����H�w">�V�~�����[�V�����H�w<br>
  <input type="checkbox" name="favorite" value="��񐔗�����">��񐔗�����<br>
  <input type="checkbox" name="favorite" value="�m����񏈗�">�m����񏈗�<br>
  <input type="checkbox" name="favorite" value="�\�t�g�E�F�A�J��">�\�t�g�E�F�A�J��<br>
  <input type="checkbox" name="favorite" value="�R���e���c�쐬">�R���e���c�쐬<br>
  <input type="checkbox" name="favorite" value="�R���e���c�Ǘ�">�R���e���c�Ǘ�<br></br>
  <input type="submit" value="���R�����h�J�n�I">
</form>
</body>
</html>
