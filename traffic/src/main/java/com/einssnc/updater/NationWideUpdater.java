package com.einssnc.updater;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.einssnc.file.DeleteFile;
import com.einssnc.file.FileUrlDownload;
import com.einssnc.file.HttpCaller;
import com.einssnc.file.UnzipFile;

public class NationWideUpdater implements DayUpdater {

	private static final String baseUrl = "http://openapi.its.go.kr";
	private static final String eachFileParamUrl = "/data/getEachFileDownload.do?path=%s_5Min.zip&name=%s_5Min.zip&type=traffic";
	private static final String downloadParamUrl = "/data/getDownload.do?name=%s_5Min.zip&savename=%s";
	private static final String key = "resultMsg";
	
	private static final String[] columns = {"time", "link_id", "speed", "traffic_volume", "density", "travel_time", "delay_time", "vehicle_length", "sensor_share"};

	// 날짜형식
	private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	// 데이터를 가지고 있는 최소한의 날짜와 최대의 날짜
	private final Calendar minDate = getCalendar(2018, 3, 1);
	private final Calendar maxDate = Calendar.getInstance();

	private String downloadDir; // 압축파일과 압축푼파일이 저장된 디렉토리
	private String fileName; // 압축파일 이름

	public NationWideUpdater(String downloadDir, String filenName) {
		this.downloadDir = downloadDir;
		this.fileName = filenName;
		this.maxDate.add(Calendar.DATE, -1);
	}

	@Override
	public boolean insertOneDate(Calendar date) {
		String strDate = format.format(date.getTime());
		String fullFileName = downloadDir + "/" + strDate + "_5Min.csv";

		// 다운로드 받을 수 있는 url 요청
		String finalEachFileUrl = baseUrl + String.format(eachFileParamUrl, strDate, strDate);
		HttpCaller caller = new HttpCaller();
		String savename = caller.getUrlToJsonData(finalEachFileUrl, key);

		if (!savename.equals("NoData")) {
			// 압축 파일 다운로드
			String finalDownloadUrl = baseUrl + String.format(downloadParamUrl, strDate, savename);
			FileUrlDownload fud = new FileUrlDownload();
			fud.fileUrlReadAndDownload(finalDownloadUrl, fileName, downloadDir);

			// 압축파일 풀기
			UnzipFile unzipfile = new UnzipFile();
			unzipfile.unzip(downloadDir, fileName, downloadDir, date);

			// 파일 데이터 베이스에 업로드
			CsvToMySqlUpdater updater = new CsvToMySqlUpdater();
			updater.insert(fullFileName, "nation_wide_speed", "0", columns);

			// 완료한 파일 삭제
			DeleteFile delete = new DeleteFile();
			return delete.start(fullFileName);
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
