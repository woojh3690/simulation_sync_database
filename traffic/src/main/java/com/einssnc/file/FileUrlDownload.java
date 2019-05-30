package com.einssnc.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUrlDownload {
	/**
	 * url에서 파일을 다운로드
	 * @param fileAddress
	 * @param localFileName
	 * @param downloadDir
	 */
	public void fileUrlReadAndDownload(String fileAddress, String localFileName, String downloadDir) {
		System.out.println("--------file downloading ---------");
		// 다운로드 받을 주소를 만들기 위한 변수
		URL fileUrl;
		// 데이터를 바이트다윈로 읽을 때 읽을 위치를 저장할 변수
		int read;
		
		HttpURLConnection conn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			// 다운로드 받을 URL 생성
			fileUrl = new URL(fileAddress);
			// 연결 객체 생성
			conn = (HttpURLConnection) fileUrl.openConnection();

			// 연결된 파일의 크기 가져오기
			int len = conn.getContentLength();
			// 데이터를 저장할 배열생성
			byte raster[] = new byte[len];
			// 웹에서 읽어올 스트림 생성 - 일반 파일을 읽는 경우
			is = conn.getInputStream();
			// 파일에 기록할 스트림 생성
			fos = new FileOutputStream(downloadDir + localFileName);

			while (true) {
				// is 에서 읽어서 raster에 젖ㅇ하고 읽은 마지막위치를 read에 ㅈ장
				read = is.read(raster);
				// 읽은데이터가 없다면
				if (read <= 0) {
					break;
				}
				// raster 배열에서 0부턴 read 만큼 읽어서 fos에 기록
				fos.write(raster, 0, read);
			}
			
			System.out.println("--------finish download ---------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				fos.close();
				conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	/**
	 * 
	 * @param fileAddress
	 * @param downloadDir
	 */
	public void fileUrlDownload(String fileAddress, String downloadDir) {

		int slashIndex = fileAddress.lastIndexOf('/');
		int periodIndex = fileAddress.lastIndexOf('.');

		// 파일 어드레스에서 마지막에 있는 파일이름을 취득
		String fileName = fileAddress.substring(slashIndex + 1);

		if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < fileAddress.length() - 1) {
			fileUrlReadAndDownload(fileAddress, fileName, downloadDir);
		} else {
			System.err.println("path or file name NG.");
		}
	}

}
