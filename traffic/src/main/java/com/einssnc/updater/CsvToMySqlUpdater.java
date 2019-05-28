package com.einssnc.updater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CsvToMySqlUpdater {
	
	private static final String infileBase = "LOAD DATA INFILE '%s'\r\n" + 
			"INTO TABLE simulation.%s\r\n" + 
			"CHARACTER SET utf8mb4\r\n" + 
			"FIELDS\r\n" + 
			"    TERMINATED BY ','\r\n" + 
			"    OPTIONALLY ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE %s LINES\r\n";
	
	public void insert(String fullFileName, String table, String ignoreLine, String[] columns) {
		System.out.println("CsvToMySqlUpdater start");
		Connection conn = null;
        Statement st = null;
        Integer rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/simulation?serverTimezone=UTC", 
					"root", 
					"woojh1138!");
			st = conn.createStatement();
			
			System.out.println("insert 시작");
			rs = st.executeUpdate(setQuery(fullFileName, table, ignoreLine, columns));
			System.out.println(rs + " : insert됨");

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
		
		System.out.println("CsvToMySqlUpdater finish");
	}
	
	/**
	 * infile 쿼리문 반환
	 * @param fullFileName
	 * @param table
	 * @param ignoreLine
	 * @param columns
	 * @return
	 */
	public String setQuery(String fullFileName, String table, String ignoreLine, String[] columns) {
		
		//컬럼 쿼리
		String columnQuery = "(";
		for (String column : columns) {
			columnQuery += "@" + column + ", ";
		}
		columnQuery = replaceLast(columnQuery, ", ", ")\r\n");
		
		//set 쿼리
		String setQuery = "SET ";
		String setBase = "%s = nullif(@%s,''),\r\n";
		for (String column : columns) {
			setQuery += String.format(setBase, column, column);
		}
		setQuery = replaceLast(setQuery, ",\r\n", ";");
		
		//최종 쿼리
		String infileQuery = String.format(infileBase, fullFileName, table, ignoreLine);
		System.out.println(infileQuery);
		return infileQuery + columnQuery + setQuery;
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
}
