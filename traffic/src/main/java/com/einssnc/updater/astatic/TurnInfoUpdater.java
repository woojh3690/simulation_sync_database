package com.einssnc.updater.astatic;

import java.util.Arrays;

import com.einssnc.file.CsvToMySqlUpdater;

public class TurnInfoUpdater {
	
	public static final String[] columns = {"node_id", "turn_id", "st_link", "ed_link", 
			"turn_type", "turn_oper", "remark","1","1","1","1","1","1","1","1"};
	private String fullFileName;
	
	public TurnInfoUpdater(String dir) {
		this.fullFileName = dir + "turninfo.csv";
	}
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		
		String[] select = Arrays.copyOfRange(columns, 0, 1);
		updater.update(fullFileName, "node", "1", columns, select);
		
		select = Arrays.copyOfRange(columns, 0, 7);
		updater.update(fullFileName, "turninfo", "1", columns, select);
		return true;
	}
}
