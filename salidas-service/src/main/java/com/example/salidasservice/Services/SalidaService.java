package com.example.salidasservice.Services;

import com.example.salidasservice.Entities.SalidaEntity;
import com.example.salidasservice.Repositiory.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SalidaService {
    @Autowired
    SalidaRepository salidaRepository;

    public ArrayList<SalidaEntity> obtenerSalidas(){
        return (ArrayList<SalidaEntity>) salidaRepository.findAll();
    }

    public void guardarDB(SalidaEntity salida){
        if (salida.getMonto() > 0){
            salidaRepository.save(salida);
        }
    }

}
