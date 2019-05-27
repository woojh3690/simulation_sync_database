package com.einssnc.updater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.dao.DataIntegrityViolationException;

import com.einssnc.dao.LinkDao;
import com.einssnc.dao.NationWideSpeedDao;
import com.einssnc.file.FileUrlDownload;
import com.einssnc.file.HttpCaller;
import com.einssnc.file.UnzipFile;
import com.einssnc.model.Link;
import com.einssnc.model.NationWideSpeed;
import com.einssnc.model.NationWideSpeedId;

public class NationWideSpeedDBUpdate implements DayUpdater {

	private static final String baseUrl = "http://openapi.its.go.kr";
	private static final String eachFileParamUrl = "/data/getEachFileDownload.do?path=%s_5Min.zip&name=%s_5Min.zip&type=traffic";
	private static final String downloadParamUrl = "/data/getDownload.do?name=%s_5Min.zip&savename=%s";
	private static final String key = "resultMsg";

	// 날짜형식
	private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	private final SimpleDateFormat tableDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	// 데이터를 가지고 있는 최소한의 날짜와 최대의 날짜
	private final Calendar minDate = getCalendar(2018, 3, 1);
	private final Calendar maxDate = Calendar.getInstance();

	private String downloadDir; // 압축파일과 압축푼파일이 저장된 디렉토리
	private String fileName; // 압축파일 이름
	
	public NationWideSpeedDBUpdate (
			String downloadDir, 
			String filenName) {
		this.downloadDir = downloadDir;
		this.fileName = filenName;

		this.maxDate.add(Calendar.DATE, -1);
	}
	
	@Override
	public boolean insertOneDate(Calendar date) {
		String strDate = format.format(date.getTime());

		// 다운로드 받을 수 있는 url 요청
		String finalEachFileUrl = baseUrl 
				+ String.format(eachFileParamUrl, strDate, strDate);
		HttpCaller caller = new HttpCaller();
		String savename = caller.getUrlToJsonData(finalEachFileUrl, key);

		if (!savename.equals("NoData")) {

			// 압축 파일 다운로드
			String finalDownloadUrl = baseUrl 
					+ String.format(downloadParamUrl, strDate, savename);
			FileUrlDownload fud = new FileUrlDownload();
			fud.fileUrlReadAndDownload(finalDownloadUrl, 
					fileName, 
					downloadDir);

			// 압축파일 풀기
			UnzipFile unzipfile = new UnzipFile();
			unzipfile.unzip(downloadDir, fileName, downloadDir);

			CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
			updater.insert(strDate + "_5Min");
			return true;
		} else {
			System.out.print(savename + " 날짜에 데이터 없음\n");
			return false;
		}

	}

	@Override
	public void insertPastData(Calendar startDate, Calendar endDate) {
		for (; startDate.compareTo(endDate) < 1; startDate.add(Calendar.DATE, 1)) {
			insertOneDate(startDate);
		}
	}

	@Override
	public void updateAllData() {
		insertPastData(minDate, maxDate);
	}

	public Calendar getCalendar(int year, int month, int day) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month - 1);
		date.set(Calendar.DAY_OF_MONTH, day);
		return date;
	}
}
