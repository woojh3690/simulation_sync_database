package com.einssnc;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.dao.BusLocationDao;
import com.einssnc.dao.BusRouteDao;
import com.einssnc.dao.BusStopDao;
import com.einssnc.dao.CityCodeDao;
import com.einssnc.dao.LinkDao;
import com.einssnc.dao.RealTimeTrafficJejuDao;
import com.einssnc.model.BusLocation;
import com.einssnc.model.BusStop;
import com.einssnc.model.JejuBusStop;
import com.einssnc.updater.astatic.BusStopUpdater;
import com.einssnc.updater.astatic.CityCodeUpdater;
import com.einssnc.updater.astatic.JejuBusStopUpdater;
import com.einssnc.updater.astatic.LinkUpdater;
import com.einssnc.updater.astatic.MultiLinkUpdater;
import com.einssnc.updater.astatic.NodeUpdater;
import com.einssnc.updater.astatic.TurnInfoUpdater;
import com.einssnc.updater.realtime.BusLocationUpdater;
import com.einssnc.updater.realtime.BusRouteUpdater;
import com.einssnc.updater.realtime.NationWideUpdater;
import com.einssnc.updater.realtime.RealTimeTrafficJejuUpdater;

@Component
public class Scheduler {
	
	public static final int LOCAL = Setting.LOCAL_CLIENT;
	public static final int REMOTE = Setting.REMOTE_SERVER;
	
	@Autowired
	RealTimeTrafficJejuDao dao;
	
	@Autowired
	LinkDao linkDao;
	
	@Autowired
	CityCodeDao cityDao;
	
	@Autowired
	BusStopDao busStopDao;
	
	@Autowired
	BusRouteDao busRouteDao;
	
	@Autowired
	BusLocationDao locationDao;
	
	private String dir;

	public Scheduler() {
		this.dir = new Setting().getDir();
	}

	@Scheduled(fixedDelay = 1000 * 60 * 60 * 60 * 60) // (cron = "0 0/30 8-20 * * *")
	public void test() {
		nationInsert();
		System.out.println("스케줄러 끝");
	}
	
	private void nationInsert() {
		
//		new NodeUpdater(dir).start();
//		new TurnInfoUpdater(dir).start();
//		new LinkUpdater(dir).start();
//		new MultiLinkUpdater(dir).start();
//		new JejuBusStopUpdater(dir).start();
		
//		new CityCodeUpdater(cityDao).start();
//		new BusStopUpdater(cityDao, busStopDao).start();
//		new BusRouteUpdater(cityDao, busRouteDao).start();
		new BusLocationUpdater(busRouteDao, locationDao).start();
		
//		
//		new RealTimeTrafficJejuUpdater(dao, linkDao).start();
//		
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