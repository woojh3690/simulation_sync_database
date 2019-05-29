package com.einssnc.updater;

import java.util.Arrays;

public class LinkUpdater {
	public static final String[] columns = {"link_id", "f_node", "t_node", 
			"road_use", "lanes", "road_rank", "road_name", "multi_link", 
			"connect", "max_spd", "rest_veh", "rest_w", "rest_h", "remark", 
			"road_type", "road_no"};
	
	public static final String fullFileName = "C:/Temp/nodelink1/link.csv";
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		
		String[] select = Arrays.copyOfRange(columns, 1, 3);
		updater.update(fullFileName, "node", "1", columns, select, "node_id");
		
		updater.update(fullFileName, "link", "1", columns);
		return true;
	}
}
