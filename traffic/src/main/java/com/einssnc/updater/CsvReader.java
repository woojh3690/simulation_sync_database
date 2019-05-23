package com.einssnc.updater;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvReader {
	
	private String[] row = null;
	CSVReader reader = null;
	
	public CsvReader(String downloadPath) {	
		FileInputStream steam;
		try {
			steam = new FileInputStream(downloadPath);
			reader = new CSVReader(new InputStreamReader(
					steam ,"EUC-KR"), ',', '"');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<String[]> readNext(int buffer) {
		List<String[]> rows = new ArrayList<>(); 
		
		try {
			row = reader.readNext();
			if (row == null) {
				return null;
			}
			for (int i = 0; (row = reader.readNext()) == null || i < buffer; i++) {
				rows.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rows;
	}
	
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
