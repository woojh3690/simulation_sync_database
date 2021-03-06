package com.einssnc.updater.astatic;

import com.einssnc.file.CsvToMySqlUpdater;

public class NodeUpdater {
	
	public static final String[] columns = {"xy", "node_id", "node_type", "node_name", "turn_p", "remark", "user_id", "workstate", "dept_code", "stnl_reg", "tmpid", "upload_id"};
	
	private String fullFileName;
	
	public NodeUpdater(String dir) {
		this.fullFileName = dir + "node.csv";
	}
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		updater.update(fullFileName, "node", "1", columns);
		return true;
	}
}
