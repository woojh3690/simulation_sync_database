package com.einssnc;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.updater.NationWideUpdater;
import com.einssnc.updater.NodeUpdater;

@Component
public class Scheduler {

	public Scheduler() {

	}

	@Scheduled(fixedDelay = 1000 * 60 * 60 * 60 * 60) // (cron = "0 0/30 8-20 * * *")
	public void test() {
		NodeUpdater updater = new NodeUpdater();
		updater.start();
		//nationInsert();
	}
	
	private void nationInsert() {
		Calendar start = Calendar.getInstance();
		start.set(2019, 4, 1);

		Calendar end = Calendar.getInstance();
		end.set(2019, 4, 1);
		
		NationWideUpdater updater = new NationWideUpdater("C:/Temp", "nationWideSpeedData.zip");
		updater.insertPastData(start, end);
	}

}