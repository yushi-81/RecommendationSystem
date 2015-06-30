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
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;


import LCRS.Score;

public class Pearson {
	  /**
     * �ގ��x�v���F�s�A�\������
	 *
	 * @param prefs ���͑Ώۃf�[�^
	 * @param person1 �Ώێ�1
	 * @param person2 �Ώێ�2
	 * @param algoType �]��
	 * @return �ގ��x
	 */

public static double simPearson(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>>prefs, String person1, String person2) {

  	// 2�l�Ƃ��]�����Ă���A�C�e�����X�g�𐶐�����
  	List<String> subjectList = new ArrayList<String>();

  	Set<String> user1subjectList = prefs.get(person1).keySet();
  	Set<String> user2subjectList = prefs.get(person2).keySet();

  	for (String subject : user1subjectList) {
    	if (user2subjectList.contains(subject)) {
    		subjectList.add(subject);
    	}
  	}

  	// �����Q�l�ɋ��ʂ̃A�C�e����������΁A�ގ����Ȃ��Ɣ��f����
  	if (subjectList.size() == 0) {
    	return 0.0;
  	}

  	// �S�Ă̚n�D�����v���܂�
  	Map<String, Double> user1Map = prefs.get(person1);
  	Map<String, Double> user2Map = prefs.get(person2);
  	double sum1 = 0.0, sum2 = 0.0;
  	for (String subject : subjectList) {
    	sum1 += user1Map.get(subject);
    	sum2 += user2Map.get(subject);
  	}

  	// ���������v���܂�
  	double sum1Sq = 0.0, sum2Sq = 0.0;
  	for (String subject : subjectList) {
    	sum1Sq += user1Map.get(subject) * user1Map.get(subject);
    	sum2Sq += user2Map.get(subject) * user2Map.get(subject);
  	}

  	// �ς����v���܂�
  	double pSum = 0.0;
  	for (String subject : subjectList) {
    	pSum += user1Map.get(subject) * user2Map.get(subject);
  	}

  	// �s�A�\���ɂ��X�R�A���v�Z���܂�
  	int n = subjectList.size();
  	double num = pSum - (sum1 * sum2 / n);
  	double den = Math.sqrt((sum1Sq - Math.pow(sum1, 2) / n) * (sum2Sq - Math.pow(sum2, 2) / n));

  	if (den == 0) return 0.0;

  		return num / den;
	}

 /**
     * �ގ��x�����ɂ����Ȗڂ̕]��
	 *
	 * @param prefs ���͑Ώۃf�[�^
	 * @param user �Ώێ�
	 * @param algoType �]��
	 * @return �ގ��x
	 */

public static List<Score> getRecommendations2(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> prefs, String user,double value,String[] favorites) {

		// �ގ��x�ŏd�ݕt�������Ȗڕ]���̍��v
		Map<String, Double> totals = new HashMap<String, Double>();

		// �ގ��x�̍��v
		Map<String, Double> simSums = new HashMap<String, Double>();

		// �����Ƃ̗ގ��x
		double sim0 = value;

		// �]���҂��P�l����
		for (String other : prefs.keySet()) {
			if(other.equals(user)) continue;
			// �܂����Ă��Ȃ��A�C�e���̂ݓ��_�����Z����
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

		// ���K���������X�g�����
		List<Score> scoreList = new ArrayList<Score>();

		//�f�[�^�x�[�X�ڑ�
			Connection con = null;
			//subject���X�g
			List<String> Subjects = new ArrayList<String>();

			ResultSet result = null;
		    try {
		        // �h���C�o�N���X�����[�h
		        Class.forName("com.mysql.jdbc.Driver");
		        // �f�[�^�x�[�X�֐ڑ�
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=sjis","root","mysql");
		        //��������ŉȖڂ�����
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
		        //subject�擾
		    	while(result.next()){
		        	String sub = result.getString("subject");
		        	Subjects.add(sub);
		        }
			    //��O����
		    }catch (SQLException e) {
	            e.printStackTrace();
	        }catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }finally {
	            try {
	                // close����
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

  		// �X�R�A�ǂ����Ƀ\�[�g
  		Collections.sort(scoreList, new Comparator<Score>() {
    		@Override public int compare(Score o1, Score o2) {
      						return Double.compare(o2.score, o1.score);
    					}
  		});

  		// �X�R�A���X�g��Ԃ�
  		return scoreList;
  }

}