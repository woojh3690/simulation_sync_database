package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.NationWideSpeed;
import com.einssnc.model.NationWideSpeedId;

@Repository
public interface NationWideSpeedDao extends JpaRepository<NationWideSpeed, NationWideSpeedId> {

}
