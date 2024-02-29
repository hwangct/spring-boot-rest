package com.example.pgapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pgapi.model.Address;

@Repository
public interface PostgresRepository extends JpaRepository<Address, Integer>{

}
