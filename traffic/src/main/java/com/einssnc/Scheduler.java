package com.einssnc;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.dao.LinkDao;
import com.einssnc.dao.RealTimeTrafficJejuDao;
import com.einssnc.updater.LinkUpdater;
import com.einssnc.updater.MultiLinkUpdater;
import com.einssnc.updater.NationWideUpdater;
import com.einssnc.updater.NodeUpdater;
import com.einssnc.updater.RealTimeTrafficJejuUpdater;
import com.einssnc.updater.Setting;
import com.einssnc.updater.TurnInfoUpdater;

@Component
public class Scheduler {
	
	public static final int LOCAL = Setting.LOCAL_SERVER;
	public static final int REMOTE = Setting.REMOTE_SERVER;
	
	@Autowired
	RealTimeTrafficJejuDao dao;
	
	@Autowired
	LinkDao linkDao;
	
	private String dir;

	public Scheduler() {
		this.dir = new Setting().getDir();
	}

	@Scheduled(fixedDelay = 1000 * 60 * 60 * 60 * 60) // (cron = "0 0/30 8-20 * * *")
	public void test() {
		nationInsert();
	}
	
	private void nationInsert() {
		
		new NodeUpdater(dir).start();
//		new TurnInfoUpdater(dir).start();
//		new LinkUpdater(dir).start();
//		new MultiLinkUpdater(dir).start();
//		
//		new RealTimeTrafficJejuUpdater(dao, linkDao).start();
		
//		Calendar start = Calendar.getInstance();
//		start.set(2019, 4, 1);
//
//		Calendar end = Calendar.getInstance();
//		end.set(2019, 4, 1);
//		
//		NationWideUpdater nationUpdater = new NationWideUpdater(dir, "nationWideSpeedData.zip");
//		nationUpdater.insertPastData(start, end);
	}

}