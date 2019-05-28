package com.einssnc;

import java.util.Calendar;

import org.omg.CosNaming.NameComponentHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.einssnc.updater.CsvToMySqlUpdater;
import com.einssnc.updater.NationWideSpeedDBUpdate;

@Component
public class Scheduler {
	
	public Scheduler() {
		
	}
	
	@Scheduled(fixedDelay=1000 * 60 * 60 * 60 * 60)//(cron = "0 0/30 8-20 * * *")
	public void test() {
		Calendar date = Calendar.getInstance();
		date.set(2019, 4, 03);
		NationWideSpeedDBUpdate updater = new NationWideSpeedDBUpdate("C:/Temp", "nationWideSpeedData.zip");
		updater.insertOneDate(date);
	}
	
	
}