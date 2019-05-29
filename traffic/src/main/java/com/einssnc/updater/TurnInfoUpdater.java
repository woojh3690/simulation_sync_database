package com.einssnc.updater;

import java.util.Arrays;

public class TurnInfoUpdater {
	public static final String[] columns = {"node_id", "turn_id", "st_link", "ed_link", 
			"turn_type", "turn_oper", "remark","1","1","1","1","1","1","1","1"};

	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		
		String[] select = Arrays.copyOfRange(columns, 0, 1);
		updater.update("C:/Temp/nodelink1/turninfo.csv", "node", "1", columns, select);
		
		select = Arrays.copyOfRange(columns, 0, 7);
		updater.update("C:/Temp/nodelink1/turninfo.csv", "turninfo", "1", columns, select);
		return true;
	}
}
