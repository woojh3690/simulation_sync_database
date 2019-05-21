package com.einssnc.updater;

import com.einssnc.dao.NodeDao;
import com.einssnc.model.Node;

public class NodeUpdater 
	implements AsynchronousUpdater {
	
	NodeDao nodeDao;
	
	public NodeUpdater(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	@Override
	public void buildBean(String[] str, int i) {
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
	}

	@Override
	public void buildBack(String str) {
		Node node = new Node();
		node.setNodeId(str);
		node.setNodeType("-1");
		node.setNodeName("-");
		nodeDao.save(node);
	}

}
