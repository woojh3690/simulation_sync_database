package com.einssnc.updater;

public class NodeUpdater {
	public static final String[] columns = {"node_id", "node_type", "node_name", "turn_p", "remark", "user_id", "workstate", "dept_code", "stnl_reg", "tmpid", "upload_id"};
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		updater.update("C:/Temp/nodelink1/node.csv", "node", "1", columns);
		return true;
	}
}
