package com.einssnc.updater.realtime;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.einssnc.dao.BusRouteDao;
import com.einssnc.dao.CityCodeDao;
import com.einssnc.file.HttpCaller;
import com.einssnc.model.BusRoute;
import com.einssnc.model.CityCode;

public class BusRouteUpdater {

	private int count;
	private int numOfRows = 10000;

	private static final String baseUrl = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList?serviceKey=%s";
	private static final String param = "&cityCode=%s&numOfRows=%s&pageNo=%s";

	private CityCodeDao cityDao;
	private BusRouteDao dao;

	public BusRouteUpdater(CityCodeDao cityDao, BusRouteDao busDao) {
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

		List<BusRoute> daoList = new ArrayList<BusRoute>();

		count = nodeList.getLength();
		for (int i = 0; i < count; i++) {
			NodeList columns = nodeList.item(i).getChildNodes();
			BusRoute entity = new BusRoute();
			
			int len = columns.getLength();
			for (int j = 0; j < len; j++) {
				
				Node column = columns.item(j);
				if (column.getNodeName().equals("routeid")) {
					entity.setRouteid(column.getTextContent());
				} else if (column.getNodeName().equals("routeno")) {
					entity.setRouteno(column.getTextContent());
				} else if (column.getNodeName().equals("routetp")) {
					entity.setRoutetp(column.getTextContent());
				} else if (column.getNodeName().equals("startnodenm")) {
					entity.setStartnodenm(column.getTextContent());
				} else if (column.getNodeName().equals("endnodenm")) {
					entity.setEndnodenm(column.getTextContent());
				}
			}
			CityCode cityCode = new CityCode();
			cityCode.setCitycode(cityNum);
			entity.setCityCode(cityCode);
			
			daoList.add(entity);
		}
		dao.saveAll(daoList); // 전부 저장

		System.out.println("끝, insert 개수 : " + count);

		if (count < numOfRows) {
			return false;
		} else {
			return true;
		}
	}
}
