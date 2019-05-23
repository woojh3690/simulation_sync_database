package com.einssnc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.dao.LinkDao;
import com.einssnc.dao.MultilinkDao;
import com.einssnc.dao.NationWideSpeedDao;
import com.einssnc.dao.NodeDao;
import com.einssnc.dao.RealTimeTrafficJejuDao;
import com.einssnc.dao.TurninfoDao;
import com.einssnc.model.RealTimeTrafficJejuId;
import com.einssnc.updater.LinkUpdater;
import com.einssnc.updater.MultiLinkUpdater;
import com.einssnc.updater.MySqlUpdater;
import com.einssnc.updater.NationWideSpeedDBUpdate;
import com.einssnc.updater.NodeUpdater;
import com.einssnc.updater.TurnInfoUpdater;

@Component
public class Scheduler {
	
	private static final String baseUrl = "C:/Temp/nodelink/";
	
	@Autowired
	private NationWideSpeedDao nationDao;
	
	@Autowired
	private NodeDao nodeDao;
	
	@Autowired
	private TurninfoDao turnDao;
	
	@Autowired
	private LinkDao linkDao;
	
	@Autowired
	private MultilinkDao multiDao;
	
	@Autowired
	private RealTimeTrafficJejuDao trafficJejuDao;
	
	NodeUpdater nodeUpdater;
	TurnInfoUpdater turnInfoUpdater;
	LinkUpdater linkUpdater;
	MultiLinkUpdater multiUpdater;
	
	
	public Scheduler() {
		
	}
	
	@Scheduled(fixedDelay=1000 * 60 * 60 * 60 * 60)//(cron = "0 0/30 8-20 * * *")
	public void test() {
		MySqlUpdater temp = new MySqlUpdater();
		temp.insert();
	}
	
//	private void init() {
//		nodeUpdater = new NodeUpdater(nodeDao);
//		turnInfoUpdater = new TurnInfoUpdater(turnDao, nodeUpdater);
//		linkUpdater = new LinkUpdater(linkDao, nodeUpdater);
//		multiUpdater = new MultiLinkUpdater(multiDao, linkUpdater);
//	}
//    
//	
//	public void asynchronousScheduler() {
//		System.out.println("비동기 스케줄러 시작");
//		init();
//		nodeUpdater.saveData(baseUrl + "node.csv");
//		turnInfoUpdater.saveData(baseUrl + "turninfo.csv");
//		linkUpdater.saveData(baseUrl + "link2.csv");
//		multiUpdater.saveData(baseUrl + "multilink.csv");
//		
//		System.out.println("비동기 스케줄러 완료");
//	}
	
//	@Scheduled(cron = "0 1 * * *") //매일 1시에 작동
//	public void dayScheduler() {
//		System.out.println("하루 업데이트 스케줄러 시작");
//		
//		Calendar date = Calendar.getInstance();
//		date.set(2019, 4, 14);
//		NationWideSpeedDBUpdate nation = new NationWideSpeedDBUpdate(
//				nationDao, 
//				linkUpdater, 
//				"C:/Temp", 
//				"nationWideSpeedData.zip");
//		nation.updateAllData();
//		
//		System.out.println("하루 업데이트 스케줄러 완료");
//	}
	
	
}