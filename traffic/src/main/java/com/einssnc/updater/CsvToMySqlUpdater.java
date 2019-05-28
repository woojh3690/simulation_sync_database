package com.einssnc.updater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CsvToMySqlUpdater {
	
	private static final String baseSql = "LOAD DATA INFILE '%s'\r\n" + 
			"INTO TABLE simulation.nation_wide_speed\r\n" + 
			"CHARACTER SET utf8mb4\r\n" + 
			"FIELDS\r\n" + 
			"    TERMINATED BY ','\r\n" + 
			"    OPTIONALLY ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 0 LINES;";
	
	public void insert(String fullFileName) {
		System.out.println("CsvToMySqlUpdater start");
		Connection conn = null;
        Statement st = null;
        Integer rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://wooserver.iptime.org/simulation", 
//					"woojh3690", 
//					"woojh1138!");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/simulation?serverTimezone=UTC", 
					"root", 
					"woojh1138!");
			System.out.println("연결 완료");

			st = conn.createStatement();
			
			String sql = String.format(baseSql, fullFileName);
			System.out.println("insert 시작\n"+sql);
			
			rs = st.executeUpdate(sql);
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
}
