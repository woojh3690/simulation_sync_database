package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einssnc.model.BusRoute;

public interface BusRouteDao extends JpaRepository<BusRoute, String> {

}
