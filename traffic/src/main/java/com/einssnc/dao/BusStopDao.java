package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.BusStop;

@Repository
public interface BusStopDao extends JpaRepository<BusStop, String>{

}
