package com.einssnc.updater;

import com.einssnc.model.Link;
import com.einssnc.model.Node;

public class LinkUpdater
	implements AsynchronousUpdater<Link, String> {

	@Override
	public Link buildBean(String[] str) {
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
		return entity;
	}
}
