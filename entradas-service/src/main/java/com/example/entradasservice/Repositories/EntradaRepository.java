package com.example.entradasservice.Repositories;

import com.example.entradasservice.Entities.EntradaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaEntity, Long> {

}
