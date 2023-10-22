package com.angular.basicclient.models.dao;

import com.angular.basicclient.models.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMotoDao extends JpaRepository<Moto, Integer> {
}
