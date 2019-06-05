package com.einssnc.updater;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.einssnc.dao.LinkDao;
import com.einssnc.dao.RealTimeTrafficJejuDao;
import com.einssnc.file.HttpCaller;
import com.einssnc.model.Link;
import com.einssnc.model.RealTimeTrafficJeju;
import com.einssnc.model.RealTimeTrafficJejuId;

public class RealTimeTrafficJejuUpdater {
	private int count;

	private static final String url = "http://openapi.jejuits.go.kr/rfcapi/rest/jejuits/getTrafficInfo?authApiKey=qV1LziLSWQEOHoSfyNMNyC4f%2FyXOaW4Yo%2BmJjjBqxT5yOIXNhbkh5TtrfnKZbX97pYeBFGqKlMd0FQlCcTgnwg%3D%3D";
	private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	private RealTimeTrafficJejuDao dao;
	private LinkDao linkDao;

	public RealTimeTrafficJejuUpdater(RealTimeTrafficJejuDao dao, LinkDao nodeDao) {
		this.dao = dao;
		this.linkDao = nodeDao;
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
			XPathExpression expr = xpath.compile("//data/list");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

			List<RealTimeTrafficJeju> daoList = new ArrayList<RealTimeTrafficJeju>();
			
			count = nodeList.getLength();
			for (int i = 0; i < count; i++) {
				NodeList child = nodeList.item(i).getChildNodes();
				try {
					dao.save(getEntity(child));
				} catch (Exception e) {
					Link model = new Link();
					model.setLinkId(child.item(0).getTextContent());
					linkDao.save(model);
					
					dao.save(getEntity(child)); //다시시도
				}
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
	
	private RealTimeTrafficJeju getEntity(NodeList child) 
			throws DOMException, ParseException {
		
		RealTimeTrafficJejuId id = new RealTimeTrafficJejuId();
		id.setLinkId(child.item(0).getTextContent());
		id.setPrcnDt(format.parse(child.item(2).getTextContent()));

		RealTimeTrafficJeju entity = new RealTimeTrafficJeju();
		entity.setOcpyRate(childToInt(child, 1));
		entity.setSped(childToInt(child, 3));
		entity.setTfvl(childToInt(child, 4));
		entity.setTrvlHh(childToInt(child, 5));
		entity.setId(id);
		
		return entity;
	}
}
