package com.einssnc.updater;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySqlUpdater {

	public MySqlUpdater() {
		// TODO Auto-generated constructor stub
	}
	
	public void insert() {
		Connection conn = null;
        Statement stmt = null;
        CsvReader csvReader = null;
        
        int rs;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://wooserver.iptime.org/simulation", 
					"woojh3690", 
					"woojh1138!");
			System.out.println("연결 완료");
			
			stmt = conn.createStatement();
			
			csvReader = new CsvReader("C:/Temp/20190401_5Min.csv");
			List<String[]> rows = new ArrayList<String[]>();
			
			while ((rows = csvReader.readNext(100)) != null) {
				String sql = "INSERT INTO `nation_wide_speed` VALUES ";
				
				for (String[] row : rows) {
					row[0] = "'" + row[0] + "'";
					row[1] = "'" + row[1] + "'";
					String query = Arrays.toString(row);
					query = query.replace("[", "(").replace("]", ")");
					query += ",\n";
					//System.out.println(query);
					sql += query;
				}
				sql = sql.substring(0, sql.length() - 2);
				sql += ";";
				System.out.println(sql);
				rs = stmt.executeUpdate(sql);
				System.out.println(rs);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            try{
            	if( conn != null && !conn.isClosed()){
                    conn.close();
                }
                if( stmt != null && !stmt.isClosed()){
                    stmt.close();
                }
                csvReader.close();
            }
            catch( SQLException e){
                e.printStackTrace();
            }
        }
	}
}
