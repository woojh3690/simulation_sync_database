package com.einssnc.updater;

import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;

import com.einssnc.updater.NodeUpdater;
import com.einssnc.dao.TurninfoDao;
import com.einssnc.model.Turninfo;
import com.einssnc.model.TurninfoId;

public class TurnInfoUpdater
	implements AsynchronousUpdater {
	
	TurninfoDao turnDao;
	NodeUpdater updater;
	
	public TurnInfoUpdater(TurninfoDao turnDao, NodeUpdater updater) {
		this.turnDao = turnDao;
		this.updater = updater;
	}


	@Override
	public void buildBean(String[] str, int i) {
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
		
		try {
			turnDao.save(entity);
		} catch (DataIntegrityViolationException e) {
			System.out.println(i 
					+ " 에서 오류" 
					+ Arrays.toString(str));
			updater.buildBack(str[0]);
			turnDao.save(entity);
		}
	}

	@Override
	public void buildBack(String str) {
		// TODO Auto-generated method stub
		
	}
}
