package com.example.salidasservice.Repositiory;

import com.example.salidasservice.Entities.SalidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<SalidaEntity,Long> {}
