package com.einssnc;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.dao.NationWideSpeedDao;
import com.einssnc.dao.NodeDao;
import com.einssnc.dao.TurninfoDao;
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
    
	@Scheduled(fixedDelay=1000 * 60 * 60 * 60 * 60)//(cron = "0 0/30 8-20 * * *")//(fixedDelay=1000 * 60 * 60)//(cron = "30 * * * * *")
	public void alarm() {
//		System.out.println("시작");
//		Calendar date = Calendar.getInstance();
//		date.set(2019, 4, 14);
//		NationWideSpeedDBUpdate nation = new NationWideSpeedDBUpdate("C:/Temp", "nationWideSpeedData.zip");
//		nation.saveData(nationDao, "C:/Temp" + "//" + "20180305" + "_5Min.csv");
		
//		NodeUpdater updater = new NodeUpdater();
//		updater.saveData(nodeDao, baseUrl + "node.csv");
//		System.out.println("끝");
		
		TurnInfoUpdater turnInfoUpdater = new TurnInfoUpdater();
		turnInfoUpdater.saveData(turnDao, baseUrl + "turninfo.csv");
	}
}