package com.example.salidasservice.Services;

import com.example.salidasservice.Entities.SalidaEntity;
import com.example.salidasservice.Repositiory.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class SalidaService {
    @Autowired
    SalidaRepository salidaRepository;

    public ArrayList<SalidaEntity> obtenerSalidas(){
        return (ArrayList<SalidaEntity>) salidaRepository.findAll();
    }

    public ArrayList<SalidaEntity> obtenerPorIntervaloFecha(Date fechaInicio, Date fechaFin) {
        ArrayList<SalidaEntity> salidas = obtenerSalidas();
        ArrayList<SalidaEntity> salidasAux = new ArrayList<>();

        for (SalidaEntity salida : salidas){
            if(salida.getFecha().compareTo(fechaInicio)>= 0 && salida.getFecha().compareTo(fechaFin) <= 0){
                salidasAux.add(salida);
            }
        }

        return salidasAux;
    }

    public void guardarDB(SalidaEntity salida){
        if (salida.getMonto() > 0){
            salidaRepository.save(salida);
        }
    }

}
