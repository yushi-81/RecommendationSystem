<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@ page import="LCRS.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>レコメンドシステム データ一覧</title>

<script language="JavaScript">
function Check(){
	if(document.favorite_regist.name.value == ""){
		alert("名前を入力してください");return false;
	}else if(document.favorite_regist.name.value != ""){
		for (i = 0; i < document.favorite_regist.elements.length; i++){
			if (document.favorite_regist.elements[i].checked){
				status =1;
				favo ++;
			}
		}
		if(!status){
			alert("興味のある分野を選択してください");
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


<p>氏名の入力をお願いします</p>
<input type="text" name="name" size=20 maxlength="20">
<p>あなたの興味のある分野は？（複数可）</p>
  <input type="checkbox" name="favorite" value="コンピュータサイエンス">コンピュータサイエンス<br>
  <input type="checkbox" name="favorite" value="コンピュータネットワーク">コンピュータネットワーク<br>
  <input type="checkbox" name="favorite" value="モデリング技術">モデリング技術<br>
  <input type="checkbox" name="favorite" value="数理解析">数理解析<br>
  <input type="checkbox" name="favorite" value="数理基礎数学">数理基礎数学<br>
  <input type="checkbox" name="favorite" value="情報数学">情報数学<br>
  <input type="checkbox" name="favorite" value="ソフトウェア工学">ソフトウェア工学<br>
  <input type="checkbox" name="favorite" value="ビジュアルクリエイト">ビジュアルクリエイト<br>
  <input type="checkbox" name="favorite" value="人間情報処理">人間情報処理<br>
  <input type="checkbox" name="favorite" value="シミュレーション工学">シミュレーション工学<br>
  <input type="checkbox" name="favorite" value="情報数理実装">情報数理実装<br>
  <input type="checkbox" name="favorite" value="知識情報処理">知識情報処理<br>
  <input type="checkbox" name="favorite" value="ソフトウェア開発">ソフトウェア開発<br>
  <input type="checkbox" name="favorite" value="コンテンツ作成">コンテンツ作成<br>
  <input type="checkbox" name="favorite" value="コンテンツ管理">コンテンツ管理<br></br>
  <input type="submit" value="レコメンド開始！">
</form>
</body>
</html>
