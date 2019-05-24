package com.einssnc.updater;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.opencsv.CSVReader;

public class CsvReader extends Thread {
	
	private static LinkedBlockingQueue<String> q;
	private static CSVReader reader = null;
	private static int limit; 
	private static int liveThread = 0;
	
	private int name = 0;
	
	public CsvReader(int name) {
		this.name = name;
	}
	
	public static void init(String downloadPath, int alimit) {
		q = new LinkedBlockingQueue<String>();
		limit = alimit;
		
		FileInputStream steam;
		try {
			steam = new FileInputStream(downloadPath);
			reader = new CSVReader(new InputStreamReader(steam ,"EUC-KR"), ',', '"');
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		arrive();
		String[] row = null;
		
		do {
			try {
				
				String sql = "INSERT INTO `nation_wide_speed` VALUES ";
				
				for (int i = 0; i < limit; i++) {
					synchronized (reader) {
						row = reader.readNext();
					}
					if (row == null) {
						break;
					}
					row[0] = "'" + row[0] + "'";
					row[1] = "'" + row[1] + "'";
					String query = Arrays.toString(row);
					query = query.replace("[", "(").replace("]", "),\n");
					sql += query;
				}

				sql = sql.substring(0, sql.length() - 2);
				sql += ";";

				q.offer(sql);
				System.out.println(name + "-쓰레드 큐에 데이터 추가");
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (row != null);
		
//		if (row == null) {
//			System.out.println("null 맞음");
//		} else {
//			System.out.println("null 아님");
//		}
		
		dead();
	}
	
	public static String qTake() throws InterruptedException {
		return q.take();
	}
	
	public static int qCheck() {
		return q.size();
	}
	
	public synchronized void arrive() {
		liveThread += 1;
		System.out.println(name + "-쓰레드 시작 현재 살아있는 쓰레드 수 : " + liveThread);
	}
	
	public synchronized void dead() {
		liveThread -= 1;
		System.out.println(name + "-쓰레드 완료 현재 살아있는 쓰레드 수 : " + liveThread);
	}
	
	public static int getLiveThread() {
		return liveThread;
	}
	
	public static void close() {
		System.out.println("reader close");
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
