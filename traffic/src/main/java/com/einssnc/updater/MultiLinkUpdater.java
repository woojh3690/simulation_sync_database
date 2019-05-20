package com.einssnc.updater;

import com.einssnc.model.Link;
import com.einssnc.model.Multilink;

public class MultiLinkUpdater 
	implements AsynchronousUpdater<Multilink, Integer> {

	@Override
	public Multilink buildBean(String[] str) {
		Link link = new Link();
		link.setLinkId(str[0]);
		
		Multilink entity = new Multilink();
		entity.setLink(link);
		entity.setMultiId(strToInteger(str[1]));
		entity.setRoadRank(str[2]);
		entity.setRoadType(str[3]);
		entity.setRoadNo(str[4]);
		entity.setRoadName(str[5]);
		entity.setRemark(str[6]);
		return entity;
	}

}
