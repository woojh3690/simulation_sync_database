package com.einssnc.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCaller {

	/**
	 * url에서 json 텍스트를 가져온다음 정해진 key 값의 데이터를 반환
	 * 
	 * @param url
	 * @return
	 */
	public String getUrlToData(String type, String url) {
		String resultMsg = "NoData";
		URL obj;

		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// 패킷헤더 작성
			con.setRequestMethod(type);
			con.setRequestProperty("Content-Type", "application/json");

			// 전송
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				// local에 저장
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				resultMsg = response.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(resultMsg);
		return resultMsg;
	}

}
