package LCRS;

import java.sql.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;

public class Postgraduate_Map {
	public static ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> CreateMap_postgraduate(String Name, String[] favorites) {
		//�f�[�^�x�[�X�ڑ�
		Connection con = null;
		//Map
		ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> rawDataMap = new ConcurrentHashMap<String, ConcurrentHashMap<String,Double>>();    //�w��
		//id,name,subject���X�g
		List<String> idNumbers = new ArrayList<String>();
		List<String> userNames = new ArrayList<String>();
		List<String> subjectNames = new ArrayList<String>();
		ResultSet result = null;
	    try {
	        // �h���C�o�N���X�����[�h
	        Class.forName("com.mysql.jdbc.Driver");
	        // �f�[�^�x�[�X�֐ڑ�
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=sjis","root","mysql");
	        //��������Ń��[�U��������
	   		 switch(favorites.length)
	   		 {
	    case 1:
	    		result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%'").executeQuery();
	    		break;
	  	case 2:
	    		result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1] + "%'").executeQuery();
	    		break;
	   	case 3:
	    		result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]
	    		+ "%' or field like '%"+ favorites[2] + "%'").executeQuery();
	    		break;
	   	case 4:
	    		result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"
	       		+ favorites[2] + "%' or field like '%"+ favorites[3] + "%'").executeQuery();
	    		break;
	   	case 5:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
				+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%'").executeQuery();
				break;
	    case 6:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
	   			+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%'").executeQuery();
				break;
		case 7:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
				+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%'").executeQuery();
				break;
		case 8:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
	   			+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%'").executeQuery();
				break;
		case 9:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
	    		+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%'").executeQuery();
				break;
		case 10:
	   			result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
	    		+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
	   			+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9] + "%'").executeQuery();
	   			break;
		case 11:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
	   			+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
				+ "%' or field like '%"+ favorites[10] + "%'").executeQuery();
				break;
		case 12:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
				+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
				+ "%' or field like '%"+ favorites[10] + "%' or field like '%"+ favorites[11] + "%'").executeQuery();
				break;
		case 13:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
				+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
				+ "%' or field like '%"+ favorites[10]+ "%' or field like '%"+ favorites[11] + "%' or field like '%"+ favorites[12] + "%'").executeQuery();
				break;
		case 14:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
				+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
				+ "%' or field like '%"+ favorites[10]+ "%' or field like '%"+ favorites[11] + "%' or field like '%"+ favorites[12]
				+ "%' or field like '%"+ favorites[13] + "%'").executeQuery();
				break;
		case 15:
				result = con.prepareStatement("select id from favorite where field like '%"+ favorites[0]+ "%' or field like '%"+ favorites[1]+ "%' or field like '%"+ favorites[2]
				+ "%' or field like '%"+ favorites[3] + "%' or field like '%"+ favorites[4] + "%' or field like '%"+ favorites[5] + "%' or field like '%"
				+ favorites[6] + "%' or field like '%"+ favorites[7] + "%' or field like '%"+ favorites[8] + "%' or field like '%"+ favorites[9]
				+ "%' or field like '%"+ favorites[10]+ "%' or field like '%"+ favorites[11] + "%' or field like '%"+ favorites[12]
				+ "%' or field like '%"+ favorites[13] + "%' or field like '%"+ favorites[14] + "%'").executeQuery();
				break;

		}
	//Map�쐬(��w�@)
	        //id�擾
	    	while(result.next()){
	        	String id = String.valueOf(result.getInt("id"));
	        	idNumbers.add(id);
	        }
	        //���O�擾
	        for(int i=0;i<idNumbers.size();i++){
	        	String idNumber = idNumbers.get(i);
	            ResultSet result2 = con.prepareStatement("select distinct name from postgraduate where id ='" + idNumber + "'").executeQuery();
	            	while(result2.next()){
	                	String name = result2.getString("name");
	                	userNames.add(name);
	           		}
	        }
	        //�Ȗڎ擾
	        for(int i=0;i<idNumbers.size();i++){
	            ResultSet result3 = con.prepareStatement("select distinct subject from postgraduate").executeQuery();
	            	while(result3.next()){
	            		String subject = result3.getString("subject");
	            		subjectNames.add(subject);
	            	}
	        }
	        //�]���擾
	        for(int i=0;i<userNames.size();i++){
	        	String userName = userNames.get(i);
	        	ConcurrentHashMap<String, Double> subjectScoreMap = new ConcurrentHashMap<String, Double>();
	        	for(int e=0;e<subjectNames.size();e++){
	        		String subjectName = subjectNames.get(e);
	        		ResultSet result4 = con.prepareStatement("select value from postgraduate where name ='" + userName +"'and subject ='" + subjectName +"'").executeQuery();
	        		while(result4.next()){
	        			double value = result4.getDouble("value");
	        			subjectScoreMap.put(subjectName, value);
	        		}
	        	}
	        	rawDataMap.put(userName,subjectScoreMap);
	        }
	        //�Ώێҕ]���擾
	        ConcurrentHashMap<String, Double> subjectScoreMap2 = new ConcurrentHashMap<String, Double>();
	    	for(int e=0;e<subjectNames.size();e++){
	    		String subjectName = subjectNames.get(e);
	    		ResultSet result5 = con.prepareStatement("select value from postgraduate where name ='" + Name +"'and subject ='" + subjectName +"'").executeQuery();
	    		while(result5.next()){
	    			double value = result5.getDouble("value");
	    			subjectScoreMap2.put(subjectName, value);
	    		}
	    	}
	    	rawDataMap.put(Name,subjectScoreMap2);

	    	return rawDataMap;

	    //��O����
	    }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // close����
                if(con != null){
                    con.close();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
		return null;
	}

}
