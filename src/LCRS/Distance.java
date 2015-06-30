package LCRS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Distance {
	/**
	 * 類似度を計測するメソッド。
	 *
	 * @param prefs 分析対象データ
	 * @param person1 対象者1
	 * @param person2 対象者2
	 * @param algoType 評価
	 * @return 類似度
	 */

 //類似度の測定(ユークリッド距離)
	public static double simDistance(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>>prefs, String person1, String person2) {


		// 2人とも評価しているアイテムリストを生成する

		List<String> movieList = new ArrayList<String>();

		Set<String> user1MovieList = prefs.get(person1).keySet();
		Set<String> user2MovieList = prefs.get(person2).keySet();

			for (String movie : user1MovieList) {

				if (user2MovieList.contains(movie)) {
					movieList.add(movie);
				}
			}

			// もし２人に共通のアイテムが無ければ、類似性なしと判断する

			if (movieList.size() == 0) {
				return 0.0;
			}

			// 全ての差の平方根を足し合わせる
			Map<String, Double> user1Map = prefs.get(person1);
			Map<String, Double> user2Map = prefs.get(person2);
			double sumOfSquares = 0.0;
			for (String movie : movieList) {
				sumOfSquares += Math.sqrt(Math.abs(user1Map.get(movie) - user2Map.get(movie)));
			}

			// 数値が大きいほど類似性が高いことにしたいので、逆数を取る。
			// その際にゼロ除算しないように+1する。
			return 1 / (1 + sumOfSquares);
	}

	 /**
	     * 類似度を元にした科目の評価
		 *
		 * @param prefs 分析対象データ
		 * @param user 対象者
		 * @param algoType 評価
		 * @return 類似度
		 */

	public static List<Score> getRecommendations2(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> prefs, String user,double value,String[] favorites) {

			// 類似度で重み付けした科目評価の合計
			Map<String, Double> totals = new HashMap<String, Double>();

			// 類似度の合計
			Map<String, Double> simSums = new HashMap<String, Double>();

			// 自分との類似度
			double sim0 = value;

			// 評価者を１人ずつ
			for (String other : prefs.keySet()) {
				if(other.equals(user)) continue;
				// まだ見ていないアイテムのみ得点を加算する
				Map<String, Double> mysubjectScores = prefs.get(user);
				Map<String, Double> othersubjectScores = prefs.get(other);
				for (String othersubject : othersubjectScores.keySet()) {

					if (mysubjectScores.keySet().contains(othersubject) == false) {

						double total = 0.0, simSum = 0.0;

						if (totals.containsKey(othersubject) == true) {
							total += totals.get(othersubject);
							simSum += simSums.get(othersubject);
						}
						total += othersubjectScores.get(othersubject) * sim0;
						simSum += sim0;

						totals.put(othersubject, total);
						simSums.put(othersubject, simSum);
					}
				}
			}
			// 正規化したリストを作る
			List<Score> scoreList = new ArrayList<Score>();

			//データベース接続
				Connection con = null;
				//subjectリスト
				List<String> Subjects = new ArrayList<String>();

				ResultSet result = null;
			    try {
			        // ドライバクラスをロード
			        Class.forName("com.mysql.jdbc.Driver");
			        // データベースへ接続
			        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=sjis","root","mysql");
			        //興味分野で科目を検索
			   		 switch(favorites.length)
			   		 {
			    case 1:
			    		result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%'").executeQuery();
			    		break;
			  	case 2:
			    		result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1] + "%'").executeQuery();
			    		break;
			   	case 3:
			    		result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]
			    		+ "%' or field like '%"+ favorites[2] + "%'").executeQuery();
			    		break;
			   	case 4:
			    		result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"
			       		+ favorites[2] + "%' or field like '%"+ favorites[3] + "%'").executeQuery();
			    		break;
			   	case 5:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
						+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%'").executeQuery();
						break;
			    case 6:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
			   			+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%'").executeQuery();
						break;
				case 7:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
						+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%'").executeQuery();
						break;
				case 8:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
			   			+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%'").executeQuery();
						break;
				case 9:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
			    		+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%'").executeQuery();
						break;
				case 10:
			   			result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
			    		+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
			   			+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9] + "%'").executeQuery();
			   			break;
				case 11:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
			   			+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
						+ "%' or field like '%"+ favorites[10] + "%'").executeQuery();
						break;
				case 12:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
						+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
						+ "%' or field like '%"+ favorites[10] + "%' or field like '%"+ favorites[11] + "%'").executeQuery();
						break;
				case 13:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
						+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
						+ "%' or field like '%"+ favorites[10]+ "%' or field like '%"+ favorites[11] + "%' or field like '%"+ favorites[12] + "%'").executeQuery();
						break;
				case 14:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
						+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
						+ "%' or field like '%"+ favorites[10]+ "%' or field like '%"+ favorites[11] + "%' or field like '%"+ favorites[12]
						+ "%' or field like '%"+ favorites[13] + "%'").executeQuery();
						break;
				case 15:
						result = con.prepareStatement("select subject from subject_info where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
						+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
						+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
						+ "%' or field like '%"+ favorites[10]+ "%' or field like '%"+ favorites[11] + "%' or field like '%"+ favorites[12]
						+ "%' or field like '%"+ favorites[13] + "%' or field like '%"+ favorites[14] + "%'").executeQuery();
						break;

				}
			        //subject取得
			    	while(result.next()){
			        	String sub = result.getString("subject");
			        	Subjects.add(sub);
			        }
				    //例外処理
			    }catch (SQLException e) {
		            e.printStackTrace();
		        }catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }finally {
		            try {
		                // close処理
		                if(con != null){
		                    con.close();
		                }
		            } catch(SQLException e){
		                e.printStackTrace();
		            }
		        }


		        for(int i=0;i<Subjects.size();i++){

		    		for (String subject : totals.keySet()) {

		    			if(Subjects.get(i).equals(subject)){

		    				double ranking = totals.get(subject) / simSums.get(subject);
		    				scoreList.add(new Score(subject, ranking));
		    			}

		    		}

		        }

	  		// スコア良い順にソート
	  		Collections.sort(scoreList, new Comparator<Score>() {
	    		@Override public int compare(Score o1, Score o2) {
	      						return Double.compare(o2.score, o1.score);
	    					}
	  		});

	  		// スコアリストを返す
	  		return scoreList;
	  }

}
