package com.einssnc.updater;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.einssnc.dao.BusStopDao;
import com.einssnc.dao.CityCodeDao;
import com.einssnc.file.HttpCaller;
import com.einssnc.model.BusStop;
import com.einssnc.model.CityCode;

public class BusStopUpdater {

	private int count;
	private int numOfRows = 10000;

	private static final String baseUrl = "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getSttnNoList?serviceKey=qV1LziLSWQEOHoSfyNMNyC4f%2FyXOaW4Yo%2BmJjjBqxT5yOIXNhbkh5TtrfnKZbX97pYeBFGqKlMd0FQlCcTgnwg%3D%3D";
	private static final String param = "&cityCode=%s&numOfRows=%s&pageNo=%s";

	private CityCodeDao cityDao;
	private BusStopDao dao;

	public BusStopUpdater(CityCodeDao cityDao, BusStopDao busDao) {
		this.cityDao = cityDao;
		this.dao = busDao;
	}

	public boolean start() {
		
		try {
			List<CityCode> citys = cityDao.findAll();
			for (CityCode city : citys) {
				for (int i = 1; getAndInsert(city.getCitycode(), i); i++) {
					System.out.print(city.getCityname() + " : " + i + "번째 완료.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	private boolean getAndInsert(int cityNum, int pageNum) throws Exception {
		HttpCaller caller = new HttpCaller();
		String finalUrl = baseUrl + String.format(param, cityNum, numOfRows, pageNum);
		String raw = caller.getUrlToData("GET", finalUrl);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc = null;

		// xml 파싱하기
		InputSource is = new InputSource(new StringReader(raw));
		builder = factory.newDocumentBuilder();
		doc = builder.parse(is);
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile("//body/items/item");
		NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

		List<BusStop> daoList = new ArrayList<BusStop>();

		count = nodeList.getLength();
		for (int i = 0; i < count; i++) {
			NodeList child = nodeList.item(i).getChildNodes();
			try {
				BusStop busStop = new BusStop();
				busStop.setNodeid(child.item(2).getTextContent());
				busStop.setNodenm(child.item(3).getTextContent());
				busStop.setLon(childToDouble(child, 1));
				busStop.setLat(childToDouble(child, 0));

				CityCode cityCode = new CityCode();
				cityCode.setCitycode(cityNum);
				busStop.setCityCode(cityCode);
				daoList.add(busStop);
			} catch (NullPointerException e) {
				System.out.println(child.toString());
			}
		}
		dao.saveAll(daoList); // 전부 저장

		System.out.println("끝, insert 개수 : " + count);

		if (count < numOfRows) {
			return false;
		} else {
			return true;
		}
	}

//	private int childToInt(NodeList child, int index) {
//		String temp = child.item(index).getTextContent();
//		return Integer.parseInt(temp);
//	}

	private double childToDouble(NodeList child, int index) {
		String temp = child.item(index).getTextContent();
		return Double.parseDouble(temp);
	}

}
