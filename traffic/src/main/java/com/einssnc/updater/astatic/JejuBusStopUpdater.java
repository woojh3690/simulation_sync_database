package com.einssnc.updater.astatic;

import java.util.Arrays;

import com.einssnc.file.CsvToMySqlUpdater;

public class JejuBusStopUpdater {

public static final String[] columns = {"a", "id", "name", "lon", "lat", "remark"};
	
	private String fullFileName;
	
	public JejuBusStopUpdater(String dir) {
		this.fullFileName = dir + "jeju_bus_stop.csv";
	}
	
	public boolean start() {
		CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
		String[] select = Arrays.copyOfRange(columns, 1, 6);
		System.out.println(Arrays.toString(select));
		updater.update(fullFileName, "jeju_bus_stop", "1", columns, select);
		return true;
	}
	
}
