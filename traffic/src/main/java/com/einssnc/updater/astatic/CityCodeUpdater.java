package com.einssnc.updater.astatic;

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

import com.einssnc.dao.CityCodeDao;
import com.einssnc.file.HttpCaller;
import com.einssnc.model.CityCode;

public class CityCodeUpdater {
	
	private int count;

	private static final String url = "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCtyCodeList?serviceKey=qV1LziLSWQEOHoSfyNMNyC4f%2FyXOaW4Yo%2BmJjjBqxT5yOIXNhbkh5TtrfnKZbX97pYeBFGqKlMd0FQlCcTgnwg%3D%3D";
	private CityCodeDao dao;

	public CityCodeUpdater(CityCodeDao dao) {
		this.dao = dao;
	}

	public boolean start() {
		HttpCaller caller = new HttpCaller();
		String raw = caller.getUrlToData("GET", url);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc = null;

		try {
			// xml 파싱하기
			InputSource is = new InputSource(new StringReader(raw));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			XPathExpression expr = xpath.compile("//body/items/item");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

			List<CityCode> daoList = new ArrayList<CityCode>();
			
			count = nodeList.getLength();
			for (int i = 0; i < count; i++) {
				NodeList child = nodeList.item(i).getChildNodes();
				CityCode citycode = new CityCode();
				citycode.setCitycode(childToInt(child, 0));
				citycode.setCityname(child.item(1).getTextContent());
				daoList.add(citycode);
			}
			dao.saveAll(daoList); // 전부 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("끝, insert 개수 : " + count);
		
		return true;
	}

	private int childToInt(NodeList child, int index) {
		String temp = child.item(index).getTextContent();
		return Integer.parseInt(temp);
	}
}
