package com.einssnc.updater;

import com.einssnc.model.Node;

public class NodeUpdater 
	implements AsynchronousUpdater<Node, String> {

	@Override
	public Node buildBean(String[] str) {
		Node entity = new Node();
		entity.setNodeId(str[0]);
		entity.setNodeType(str[1]);
		entity.setNodeName(str[2]);
		entity.setTurnP(strToChar(str[3]));
		entity.setRemark(str[4]);
		entity.setUserId(str[5]);
		entity.setWorkstate(strToInteger(str[6]));
		entity.setDeptCode(strToInteger(str[7]));
		entity.setStnlReg(strToInteger(str[8]));
		entity.setTmpid(str[9]);
		entity.setUploadId(str[10]);
		return entity;
	}
}
