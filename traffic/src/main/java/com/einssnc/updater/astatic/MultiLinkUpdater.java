package com.einssnc.updater.astatic;

import java.util.Arrays;

import com.einssnc.file.CsvToMySqlUpdater;

public class MultiLinkUpdater {
	private static final String[] columns = {"multi_id", "link_id", 
			"road_rank", "road_type", "road_no", "road_name", "remark"};
	public String fullFileName;
	
	public MultiLinkUpdater(String dir) {
		this.fullFileName = dir + "multilink.csv";
	}
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		
		String[] select = Arrays.copyOfRange(columns, 1, 2);
		updater.update(fullFileName, "link", "1", columns, select);
		
		updater.update(fullFileName, "multilink", "1", columns);
		
		return true;
	}
}
