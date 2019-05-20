package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.CityCode;

@Repository
public interface CityCodeDao extends JpaRepository<CityCode, Integer> {

}
