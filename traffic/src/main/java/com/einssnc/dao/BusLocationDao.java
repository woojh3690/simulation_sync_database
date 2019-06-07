package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einssnc.model.BusLocation;

public interface BusLocationDao extends JpaRepository<BusLocation, String> {

}
