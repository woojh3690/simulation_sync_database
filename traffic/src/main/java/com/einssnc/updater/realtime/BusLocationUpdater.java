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

import com.einssnc.dao.BusLocationDao;
import com.einssnc.dao.BusRouteDao;
import com.einssnc.file.HttpCaller;
import com.einssnc.model.BusLocation;
import com.einssnc.model.BusRoute;
import com.einssnc.model.BusStop;

public class BusLocationUpdater {
	private int count;
	private int numOfRows = 10000;

	private static final String baseUrl = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList?serviceKey=qV1LziLSWQEOHoSfyNMNyC4f%2FyXOaW4Yo%2BmJjjBqxT5yOIXNhbkh5TtrfnKZbX97pYeBFGqKlMd0FQlCcTgnwg%3D%3D";
	private static final String param = "&cityCode=%s&routeId=%s&numOfRows=%s&pageNo=%s";

	private BusRouteDao routeDao;
	private BusLocationDao dao;

	public BusLocationUpdater(BusRouteDao busRouteDao, BusLocationDao locationDao) {
		this.routeDao = busRouteDao;
		this.dao = locationDao;
	}

	public boolean start() {

		try {
			List<BusRoute> routes = routeDao.findAll();
			for (BusRoute route : routes) {
				for (int i = 1; getAndInsert(route.getCityCode().getCitycode(), route.getRouteid(), i); i++) {
					System.out.print(route.getRouteid() + " : " + i + "번째 완료.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	private boolean getAndInsert(int cityCode, String routeId, int pageNum) throws Exception {
		HttpCaller caller = new HttpCaller();
		String finalUrl = baseUrl + String.format(param, cityCode, routeId, numOfRows, pageNum);
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

		List<BusLocation> daoList = new ArrayList<BusLocation>();

		count = nodeList.getLength();
		for (int i = 0; i < count; i++) {
			NodeList columns = nodeList.item(i).getChildNodes();
			BusLocation entity = new BusLocation();

			int len = columns.getLength();
			for (int j = 0; j < len; j++) {

				Node column = columns.item(j);
				if (column.getNodeName().equals("vehicleno")) {
					entity.setVehicleno(column.getTextContent());
				} else if (column.getNodeName().equals("gpslati")) {
					entity.setGpslati(childToDouble(column));
				} else if (column.getNodeName().equals("gpslong")) {
					entity.setGpslong(childToDouble(column));
				} else if (column.getNodeName().equals("routenm")) {
					entity.setRoutenm(column.getTextContent());
				} else if (column.getNodeName().equals("routetp")) {
					entity.setRoutetp(column.getTextContent());
				} else if (column.getNodeName().equals("nodeid")) {
					BusStop busStop = new BusStop();
					busStop.setNodeid(column.getTextContent());
					entity.setBusStop(busStop);
				} else if (column.getNodeName().equals("nodeord")) {
					entity.setNodeord(childToInt(column));
				}
			}
			
			BusRoute busRoute = new BusRoute();
			busRoute.setRouteid(routeId);
			entity.setBusRoute(busRoute);
			
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

	private double childToDouble(Node child) {
		String temp = child.getTextContent();
		return Double.parseDouble(temp);
	}

	private int childToInt(Node child) {
		String temp = child.getTextContent();
		return Integer.parseInt(temp);
	}
}
