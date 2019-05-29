package com.einssnc;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.updater.LinkUpdater;
import com.einssnc.updater.MultiLinkUpdater;
import com.einssnc.updater.NationWideUpdater;
import com.einssnc.updater.NodeUpdater;
import com.einssnc.updater.TurnInfoUpdater;

@Component
public class Scheduler {

	public Scheduler() {

	}

	@Scheduled(fixedDelay = 1000 * 60 * 60 * 60 * 60) // (cron = "0 0/30 8-20 * * *")
	public void test() {
		nationInsert();
	}
	
	private void nationInsert() {
		
		new NodeUpdater().start();
		new TurnInfoUpdater().start();
		new LinkUpdater().start();
		new MultiLinkUpdater().start();
		
//		Calendar start = Calendar.getInstance();
//		start.set(2019, 4, 1);
//
//		Calendar end = Calendar.getInstance();
//		end.set(2019, 4, 1);
//		
//		NationWideUpdater nationUpdater = new NationWideUpdater("C:/Temp", "nationWideSpeedData.zip");
//		nationUpdater.insertPastData(start, end);
	}

}