package com.einssnc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einssnc.model.Node;

@Repository
public interface NodeDao extends JpaRepository<Node, String> {

}
