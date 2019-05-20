package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.Turninfo;

@Repository
public interface TurninfoDao extends JpaRepository<Turninfo, Integer> {

}
