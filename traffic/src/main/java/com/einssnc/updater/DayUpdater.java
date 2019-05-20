package com.einssnc.updater;

import java.util.Calendar;

public interface DayUpdater {
	
	/**
	 * 파라미터로 넘긴 하루 데이터만 가져오기
	 * 
	 * @param date
	 * @return
	 */
	abstract boolean insertOneDate(Calendar date);
	
	/**
	 * 지정한 날짜 사이에 데이터만 가져오기
	 * @param startDate
	 * @param endDate
	 */
	abstract void insertPastData(Calendar startDate, Calendar endDate);
	
	/**
	 * 가져올 수 있는 전체 데이터 가져오기
	 */
	abstract void updateAllData();
}
