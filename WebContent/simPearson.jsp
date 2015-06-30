<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@ page import="LCRS.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.concurrent.ConcurrentHashMap" %>
<%@ page import="java.sql.*" %>
<%
//Map
ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> rawDataMap = new ConcurrentHashMap<String, ConcurrentHashMap<String,Double>>();    //学部
ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> rawDataMap2 = new ConcurrentHashMap<String, ConcurrentHashMap<String,Double>>();   //大学院
//興味分野配列
String[] favorites;
//データベース接続
Connection con = null;
//類似度
double similarity = 0;
//テーブル
String tableRows = " ";
String tableRows2 = " ";
//レコメンドリスト
List<Score> recommendList = new ArrayList<Score>();
List<Score> recommendList2 = new ArrayList<Score>();


//興味取得
	//エンコード方式の指定
		response.setContentType("text/html; charset=Windows-31J");
	//パラメータのエンコード方式の指定
		request.setCharacterEncoding("Windows-31J");
	//vehicleパラメータの取得
		String[] fav = request.getParameterValues("favorite");
		List<String> list = new ArrayList<String>();
		for(int t=0;t<fav.length;t++){
			list.add(fav[t]);
		}
		favorites = new String[list.size()];
		for(int e=0;e<list.size();e++){
			favorites[e] = list.get(e);
		}

//対象者名取得
  	String Name = request.getParameter("name");
/*
//Map作成
	new College_Map();
	new Postgraduate_Map();
	rawDataMap = College_Map.CreateMap_college(Name,favorites); //学部
	rawDataMap2 = Postgraduate_Map.CreateMap_postgraduate(Name,favorites); //大学院
*/
List<String> userNames = new ArrayList<String>();
List<String> subjectNames = new ArrayList<String>();
try {
    // ドライバクラスをロード
    Class.forName("com.mysql.jdbc.Driver");
    // データベースへ接続
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=sjis","root","mysql");
//名前取得
    ResultSet result2 = con.prepareStatement("select distinct name from college where not name in ('" + Name + "')").executeQuery();
    	while(result2.next()){
        	String name = result2.getString("name");
        	userNames.add(name);
   		}
//科目取得
    ResultSet result3 = con.prepareStatement("select distinct subject from college").executeQuery();
    	while(result3.next()){
    		String subject = result3.getString("subject");
    		subjectNames.add(subject);
    	}
//評価取得
for(int i=0;i<userNames.size();i++){
	String userName = userNames.get(i);
	ConcurrentHashMap<String, Double> subjectScoreMap = new ConcurrentHashMap<String, Double>();
	for(int e=0;e<subjectNames.size();e++){
		String subjectName = subjectNames.get(e);
		ResultSet result4 = con.prepareStatement("select value from college where name ='" + userName +"'and subject ='" + subjectName +"'").executeQuery();
		while(result4.next()){
			double value = result4.getDouble("value");
			subjectScoreMap.put(subjectName, value);
		}
	}
	rawDataMap.put(userName,subjectScoreMap);
}
//対象者評価取得
ConcurrentHashMap<String, Double> subjectScoreMap2 = new ConcurrentHashMap<String, Double>();
for(int e=0;e<subjectNames.size();e++){
	String subjectName = subjectNames.get(e);
	ResultSet result5 = con.prepareStatement("select value from college where name ='" + Name +"'and subject ='" + subjectName +"'").executeQuery();
	while(result5.next()){
		double value = result5.getDouble("value");
		subjectScoreMap2.put(subjectName, value);
	}
}
rawDataMap.put(Name,subjectScoreMap2);


List<String> userNames2 = new ArrayList<String>();
List<String> subjectNames2 = new ArrayList<String>();


//名前取得
ResultSet result6 = con.prepareStatement("select distinct name from postgraduate where not name in ('" + Name + "')").executeQuery();
	while(result6.next()){
    	String name = result6.getString("name");
    	userNames2.add(name);
		}
//科目取得
ResultSet result7 = con.prepareStatement("select distinct subject from postgraduate").executeQuery();
	while(result7.next()){
		String subject = result7.getString("subject");
		subjectNames2.add(subject);
	}
//評価取得
for(int i=0;i<userNames2.size();i++){
String userName = userNames2.get(i);
ConcurrentHashMap<String, Double> subjectScoreMap3 = new ConcurrentHashMap<String, Double>();
for(int e=0;e<subjectNames2.size();e++){
	String subjectName = subjectNames2.get(e);
	ResultSet result8 = con.prepareStatement("select value from postgraduate where name ='" + userName +"'and subject ='" + subjectName +"'").executeQuery();
	while(result8.next()){
		double value = result8.getDouble("value");
		subjectScoreMap3.put(subjectName, value);
	}
}
rawDataMap2.put(userName,subjectScoreMap3);
}
//対象者評価取得
ConcurrentHashMap<String, Double> subjectScoreMap4 = new ConcurrentHashMap<String, Double>();
for(int e=0;e<subjectNames2.size();e++){
String subjectName = subjectNames2.get(e);
ResultSet result9 = con.prepareStatement("select value from postgraduate where name ='" + Name +"'and subject ='" + subjectName +"'").executeQuery();
while(result9.next()){
	double value = result9.getDouble("value");
	subjectScoreMap4.put(subjectName, value);
}
}
rawDataMap2.put(Name,subjectScoreMap4);

}catch (SQLException e) {
    e.printStackTrace();
} catch (ClassNotFoundException e) {
    e.printStackTrace();
} finally {
    try {
        // close処理
        if(con != null){
            con.close();
        }
    } catch(SQLException e){
        e.printStackTrace();
    }
}










//レコメンド開始
	for ( String key : rawDataMap.keySet() ) {
		// 自分同士はスキップ
		if (key.equals(Name)) continue;
		new Pearson();
		//類似度の測定
		similarity = Pearson.simPearson(rawDataMap, Name, key);

		if(similarity<=0) continue;
		//レコメンドの測定
		recommendList = Pearson.getRecommendations2(rawDataMap2, Name,similarity,favorites);

		//
		//自分&相手&類似度の表示
		tableRows += "<tr bgcolor=\"#FFFFFF\"><td align=\"center\" nowrap>" + key +
		"<td align=\"center\" nowrap>" + similarity;

	}
	//レコメンド結果

	for(int i=0;i<recommendList.size();i++){
		if(recommendList != null && recommendList.size() != 0){
			tableRows2 += "<tr bgcolor=\"#FFFFFF\"><td align=\"center\" nowrap>" + recommendList.get(i);
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title><%= Name %>のレコメンド結果</title>
</head>
<body>

<p align="left">-- <%= Name %>との類似度一覧(ピアソン相関) --</p>
<table border="0" cellspacing="0" cellpadding="0" bgcolor="#808080" width="50%">
<tr><td>
	<table border="0" cellspacing="1" cellpadding="4" width="100%">
		<tr>
		<td align="center" bgcolor="#000080">
			<font size="2" color="#FFFFFF">相手</font></td>
		<td align="center" bgcolor="#000080">
			<font size="2" color="#FFFFFF">類似度</font></td>
		</tr>

		<%= tableRows %>
	</table>
</td></tr>
</table>

<p align="left">-- <%= Name %>のレコメンド結果 --</p>
<table border="0" cellspacing="0" cellpadding="0" bgcolor="#808080" width="50%">
<tr><td>
	<table border="0" cellspacing="1" cellpadding="4" width="100%">
		<tr>
		<td align="center" bgcolor="#000080">
			<font size="2" color="#FFFFFF">結果</font></td>
		</tr>

		<%= tableRows2 %>
	</table>
</td></tr>
</table>
<a href="rawDataMap_display.jsp"><input type="button" value="戻る"></input></a>
</body>
</html>
