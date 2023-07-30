package com.example.entradasservice.Services;

import com.example.entradasservice.Entities.EntradaEntity;
import com.example.entradasservice.Repositories.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class EntradaService {
    @Autowired
    EntradaRepository entradaRepository;

    public ArrayList<EntradaEntity> obtenerEntradas(){return (ArrayList<EntradaEntity>)entradaRepository.findAll();}

    public ArrayList<EntradaEntity> obtenerPorIntervaloFecha(Date fechaInicio, Date fechaFin) {
        ArrayList<EntradaEntity> entradas = obtenerEntradas();
        ArrayList<EntradaEntity> entradasAux = new ArrayList<>();

        for (EntradaEntity entrada : entradas){
            if(entrada.getFecha().compareTo(fechaInicio)>= 0 && entrada.getFecha().compareTo(fechaFin) <= 0){
                entradasAux.add(entrada);
            }
        }

        return entradasAux;
    }

    public void guardarDb(EntradaEntity entrada){
        if(entrada.getMonto() > 0){
            entradaRepository.save(entrada);
        }
    }
}
