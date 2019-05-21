package com.einssnc.updater;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.einssnc.dao.NodeDao;
import com.einssnc.model.Node;
import com.opencsv.CSVReader;

public interface AsynchronousUpdater {

	/**
	 * string을 int형으로 변환
	 * @param str
	 * @return
	 */
	public default Integer strToInteger(String str) {
		if (str.equals("")) {
			return null;
		} else {
			return Integer.parseInt(str);
		}
	}
	
	/**
	 * string을 char형 문자 하나로 변환
	 * @param str
	 * @return
	 */
	public default char strToChar(String str) {
		if (str.equals("")) {
			return Character.MIN_VALUE;
		}
		return str.toCharArray()[0];
	}
	
	/*public default CSVReader buildCSVReader(String path) {
		final CSVParser parser =
				new CSVParserBuilder()
				.withSeparator(',')
				.withIgnoreQuotations(true)
				.build();
				final CSVReader reader =
				new CSVReaderBuilder(new StringReader(path))
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
				
		return reader;
	}*/
	
	/**
	 * 정의한 buildBean에 따라  csv파일에서 데이터를 읽어오고 데이터베이스에 저장한다.
	 * @param downloadPath
	 */
	
	@SuppressWarnings("deprecation")
	public default void saveData(
			String downloadPath) {
		
		int i = 0;
		String[] str = null;
		CSVReader reader = null;
		long startTime = System.currentTimeMillis();
		
		try {
			FileInputStream steam = new FileInputStream(downloadPath);
			reader = new CSVReader(new InputStreamReader(
					steam ,"EUC-KR"), ',', '"');
			
			reader.readNext(); //첫뻔째 라인은 컬럼 명이므로 버리기
			//System.out.println(reader.readAll().size());
			while ((str = reader.readNext()) != null) {
				i++;
				buildBean(str, i);
			}
			
		} catch (Exception e) {
			System.out.println(i + " 에서 오류" + Arrays.toString(str));
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		long endTime = System.currentTimeMillis();
		long duringTime = endTime - startTime;
		System.out.print("걸린 시간 : " + duringTime);
	}
	
	/**
	 * str에서 데이터를 받아서 bean 객체를 return 하겨 설게하면
	 * saveData함수를 호출할 때 데이터를 알아서 저장함
	 * @param str
	 * 			csv에서 넘겨진 값들이 저장되어 있다.
	 * @return
	 * 			저장하고 싶은 모델의 bean
	 */
	abstract void buildBean(String[] str, int i);
	
	abstract void buildBack(String str);
}
