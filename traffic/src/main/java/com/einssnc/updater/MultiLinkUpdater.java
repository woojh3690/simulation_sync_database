package com.einssnc.updater;

import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;

import com.einssnc.dao.MultilinkDao;
import com.einssnc.model.Link;
import com.einssnc.model.Multilink;
import com.einssnc.model.MultilinkId;
import com.einssnc.model.Node;

public class MultiLinkUpdater 
	implements AsynchronousUpdater {
	
	MultilinkDao multiDao;
	LinkUpdater updater;
	
	public MultiLinkUpdater(MultilinkDao multiDao, LinkUpdater updater) {
		this.multiDao = multiDao;
		this.updater = updater;
	}

	@Override
	public void buildBean(String[] str, int i) {
		Link link = new Link();
		link.setLinkId(str[0]);
		
		MultilinkId id = new MultilinkId();
		id.setLinkId(str[0]);
		id.setMultiId(strToInteger(str[1]));
		
		Multilink entity = new Multilink();
		entity.setId(id);
		entity.setRoadRank(str[2]);
		entity.setRoadType(str[3]);
		entity.setRoadNo(str[4]);
		entity.setRoadName(str[5]);
		entity.setRemark(str[6]);
		
		try {
			multiDao.save(entity);
		} catch (DataIntegrityViolationException e) {
			System.out.println(i 
					+ " 에서 오류" 
					+ Arrays.toString(str));
			updater.buildBack(str[0]);
			multiDao.save(entity);
		}
	}

	@Override
	public void buildBack(String str) {
		// TODO Auto-generated method stub
		
	}

}
