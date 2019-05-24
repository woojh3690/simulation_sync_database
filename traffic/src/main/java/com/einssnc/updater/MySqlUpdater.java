package com.einssnc.updater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlUpdater {

	private int limit = 1000;
	private int threadNum = 5;
	
	public MySqlUpdater() {
		// TODO Auto-generated constructor stub
	}
	
	public void insert() {
		Connection conn = null;
        Statement stmt = null;
        
        Integer rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://wooserver.iptime.org/simulation", 
					"woojh3690", 
					"woojh1138!");
			System.out.println("연결 완료");

			stmt = conn.createStatement();

			CsvReader.init("C:/Temp/20190401_5Min.csv", limit);
			
			for (int i=0; i < threadNum; i++) {
				new CsvReader(i+1).start();
			}

			do {
				rs = stmt.executeUpdate(CsvReader.qTake());
				System.out.println(rs + " : insert됨");
			} while (CsvReader.qCheck() != 0 || CsvReader.getLiveThread() != 0);

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				CsvReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
