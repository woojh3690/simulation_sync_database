package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.RealTimeTrafficJeju;
import com.einssnc.model.RealTimeTrafficJejuId;

@Repository
public interface RealTimeTrafficJejuDao extends JpaRepository<RealTimeTrafficJeju, RealTimeTrafficJejuId> {

}
