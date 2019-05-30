package com.einssnc.updater;

import com.einssnc.Scheduler;

public class Setting {
	
	public static final int LOCAL_CLIENT = 1;
	public static final int LOCAL_SERVER = 2;
	public static final int REMOTE_CLIENT = -1;
	public static final int REMOTE_SERVER = -2;
	
	private final int CUR_LOCAL;
	private final int CUR_REMOTE;
	
	public Setting() {
		this.CUR_LOCAL = Scheduler.LOCAL;
		this.CUR_REMOTE = Scheduler.REMOTE;
		// TODO Auto-generated constructor stub
	}
	
	public String getDir() {
		switch (CUR_LOCAL) {
		case LOCAL_CLIENT:
			return "C:/Temp/nodelink/";
		case LOCAL_SERVER:
			return "/data/nodelink/";
		default:
			return "?";
		}
	}
	
	public String getInfile() {
		switch (CUR_LOCAL) {
		case LOCAL_CLIENT:
			return " LOCAL";
		case LOCAL_SERVER:
			return " ";
		default:
			return "?";
		}
	}
	
	public String getUrl() {
		switch (CUR_REMOTE) {
		case REMOTE_CLIENT:
			return "localhost";
		case REMOTE_SERVER:
			return "wooserver.iptime.org";
		default:
			return "?";
		}
	}
	
	public String getUser() {
		switch (CUR_REMOTE) {
		case REMOTE_CLIENT:
			return "root";
		case REMOTE_SERVER:
			return "woojh3690";
		default:
			return "?";
		}
	}
}
