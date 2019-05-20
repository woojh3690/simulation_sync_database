package com.einssnc.updater;

import com.einssnc.model.Turninfo;
import com.einssnc.model.TurninfoId;

public class TurnInfoUpdater
	implements AsynchronousUpdater<Turninfo, Integer> {

	@Override
	public Turninfo buildBean(String[] str) {
		
		TurninfoId id = new TurninfoId();
		id.setNodeId(str[0]);
		id.setTurnId(strToInteger(str[1]));
		
		Turninfo entity = new Turninfo();
		entity.setId(id);
		entity.setStLink(str[2]);
		entity.setEdLink(str[3]);
		entity.setTurnType(str[4]);
		entity.setTurnOper(strToChar(str[5]));
		entity.setRemark(str[6]);
		return entity;
	}
}
