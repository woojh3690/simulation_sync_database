package com.einssnc.updater;

import java.util.Arrays;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.dao.DataIntegrityViolationException;

import com.einssnc.dao.LinkDao;
import com.einssnc.dao.NodeDao;
import com.einssnc.model.Link;
import com.einssnc.model.Node;

public class LinkUpdater
	implements AsynchronousUpdater {
	
	LinkDao linkDao;
	NodeUpdater updater;
	
	public LinkUpdater(LinkDao linkDao, NodeUpdater updater) {
		this.linkDao = linkDao;
		this.updater = updater;
	}

	@Override
	public void buildBean(String[] str, int i) {
		Node f_node = new Node();
		f_node.setNodeId(str[1]);
		
		Node t_node = new Node();
		t_node.setNodeId(str[2]);
		
		Link entity = new Link();
		entity.setLinkId(str[0]);
		entity.setNodeByFNode(t_node);
		entity.setNodeByTNode(t_node);
		entity.setRoadUse(strToChar(str[3]));
		entity.setLanes(strToInteger(str[4]));
		entity.setRoadRank(str[5]);
		entity.setRoadName(str[6]);
		entity.setMultiLink(strToChar(str[7]));
		entity.setConnect(str[8]);
		entity.setMaxSpd(strToInteger(str[9]));
		entity.setRestVeh(str[10]);
		entity.setRestW(strToInteger(str[11]));
		entity.setRestH(strToInteger(str[12]));
		entity.setRemark(str[13]);
		entity.setRoadType(str[14]);
		entity.setRoadNo(str[15]);
		
		try {
			linkDao.save(entity);
		} catch (DataIntegrityViolationException e) {
			System.out.println(i 
					+ " 에서 오류" 
					+ Arrays.toString(str));
			
			//f_node insert
			if (!updater.nodeDao.existsById(str[1])) {
				updater.buildBack(str[1]);
			}
			
			//t_node insert
			if (!updater.nodeDao.existsById(str[2])) {
				updater.buildBack(str[2]);
			}
			
			linkDao.save(entity);
		}
	}

	@Override
	public void buildBack(String str) {
		Link link = new Link();
		link.setLinkId(str);
		linkDao.save(link);
	}

}
