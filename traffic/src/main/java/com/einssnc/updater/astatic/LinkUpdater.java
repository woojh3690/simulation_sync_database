package com.einssnc.updater.astatic;

import java.util.Arrays;

import com.einssnc.file.CsvToMySqlUpdater;

public class LinkUpdater {
	
	public static final String[] columns = {"xys", "link_id", "f_node", "t_node", 
			"road_use", "lanes", "road_rank", "road_name", "multi_link", 
			"connect", "max_spd", "rest_veh", "rest_w", "rest_h", "remark", 
			"road_type", "road_no"};
	private String fullFileName;
	
	public LinkUpdater(String dir) {
		this.fullFileName = dir + "link.csv";
	}
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		
		String[] select = Arrays.copyOfRange(columns, 1, 3);
		updater.update(fullFileName, "node", "1", columns, select, "node_id");
		
		updater.update(fullFileName, "link", "1", columns);
		return true;
	}
}
