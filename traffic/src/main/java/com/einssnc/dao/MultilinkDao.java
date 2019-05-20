package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.Multilink;

@Repository
public interface MultilinkDao extends JpaRepository<Multilink, Integer> {

}
