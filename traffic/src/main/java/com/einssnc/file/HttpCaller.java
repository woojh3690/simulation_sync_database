package com.einssnc.file;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class HttpCaller {
	private static String resultMsg;

	/**
	 * url에서 json 텍스트를 가져온다음 정해진 key 값의 데이터를 반환
	 * 
	 * @param url
	 * @param key
	 * @return
	 */
	public String getUrlToJsonData(String url, String key) {
		resultMsg = "NoData";
		URL obj;

		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// 패킷헤더 작성
			con.setRequestMethod("POST");
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

				// json 형식에 데이터 중애서 resultMsg에 값만 가져오기
				JsonParser json = new JsonParser();
				JsonElement element = json.parse(response.toString());
				resultMsg = element.getAsJsonObject().get(key).getAsString();
				System.out.println(resultMsg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMsg;
	}
}
