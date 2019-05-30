package com.einssnc.updater;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CsvToMySqlUpdater {
	
	private static final String infileBase = "LOAD DATA%s INFILE '%s'\r\n" + 
			"IGNORE INTO TABLE simulation.%s\r\n" + 
			"CHARACTER SET euckr\r\n" + 
			"FIELDS\r\n" + 
			"    TERMINATED BY ','\r\n" + 
			"    OPTIONALLY ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE %s LINES\r\n";
	
	private String idColumn[] = null;
	
	private void insert(String fullFileName, String table, String ignoreLine, 
			String[] columns, String[] select) {
		Setting setting = new Setting();
		System.out.println("CsvToMySqlUpdater start");
		Connection conn = null;
        Statement st = null;
        Integer rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Properties props = new Properties();
		    props.put("user", setting.getUser());
		    props.put("password", "woojh1138!");
		    //props.put("--local-infile", 1);
		    
		    String url = "jdbc:mysql://%s/simulation?serverTimezone=UTC";
		    url = String.format(url, setting.getUrl()); //환경에 따라 주소 세팅
		    System.out.println("연결 시작 : " + url);
		    
			conn = DriverManager.getConnection(url, props);
			st = conn.createStatement();
			
//			ResultSet result = st.executeQuery("SHOW GLOBAL VARIABLES LIKE 'local_infile';");
//			
//			while (result.next() ) {
//				System.out.println(result.getString("Value"));
//			}

			rs = st.executeUpdate(setQuery(fullFileName, table, ignoreLine, columns, select));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (st != null && !st.isClosed()) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(rs + " : insert됨");
	}
	
	/**
	 * infile 쿼리문 반환
	 * @param fullFileName
	 * @param table
	 * @param ignoreLine
	 * @param columns
	 * @return
	 */
	public String setQuery(String fullFileName, String table, 
			String ignoreLine, String[] columns, String[] select) {
		
		//컬럼 쿼리
		String columnQuery = "(";
		for (String column : columns) {
			columnQuery += "@" + column + ", ";
		}
		columnQuery = replaceLast(columnQuery, ", ", ")\r\n");
		
		//set 쿼리
		String setQuery = "SET ";
		String setBase = "%s = nullif(@%s,''),\r\n";
		for (int i = 0; i < select.length; i++) {
			setQuery += String.format(setBase, idColumn[i], select[i]);
		}
		setQuery = replaceLast(setQuery, ",\r\n", ";");
		
		//최종 쿼리
		String infileQuery = String.format(infileBase, 
				new Setting().getInfile(),
				fullFileName, 
				table, 
				ignoreLine);
		infileQuery = infileQuery + columnQuery + setQuery;
		System.out.print(infileQuery);
		return infileQuery;
	}
	
	/**
	 * 지정한 마지막 문자를 치환
	 * @param str
	 * @param want
	 * @return
	 */
	private String replaceLast(String str, String before, String after) {
		StringBuilder b = new StringBuilder(str);
		b.replace(str.lastIndexOf(before), str.lastIndexOf(before) + 1, after);
		return b.toString();
	}
	
	public void update(String fullFileName, String table, String ignoreLine, 
			String[] columns) {
		idColumn = columns;
		insert(fullFileName, table, ignoreLine, columns, columns);
	}
	
	public void update(String fullFileName, String table, String ignoreLine, 
			String[] columns, String[] select) {
		idColumn = select;
		insert(fullFileName, table, ignoreLine, columns, select);
	}
	
	public void update(String fullFileName, String table, String ignoreLine, 
			String[] columns, String[] select, String[] id) {
		idColumn = id;
		insert(fullFileName, table, ignoreLine, columns, select);
	}
	
	public void update(String fullFileName, String table, String ignoreLine, 
			String[] columns, String[] select, String id) {
		
		List<String> idColumns = new ArrayList<String>();
		for ( String _ : columns) {
			idColumns.add(id);
		}
		idColumn = idColumns.toArray(new String[0]);
		
		insert(fullFileName, table, ignoreLine, columns, select);
	}
}
