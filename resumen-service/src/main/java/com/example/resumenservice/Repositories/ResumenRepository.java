package com.example.resumenservice.Repositories;

import com.example.resumenservice.Entities.ResumenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumenRepository extends JpaRepository<ResumenEntity, Long> {
}
