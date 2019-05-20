package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.Link;

@Repository
public interface LinkDao extends JpaRepository<Link, String> {

}
