package com.example.entradasservice.Services;

import com.example.entradasservice.Entities.EntradaEntity;
import com.example.entradasservice.Repositories.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EntradaService {
    @Autowired
    EntradaRepository entradaRepository;

    public ArrayList<EntradaEntity> obtenerEntradas(){return (ArrayList<EntradaEntity>)entradaRepository.findAll();}

    public void guardarDb(EntradaEntity entrada){
        if(entrada.getMonto() > 0){
            entradaRepository.save(entrada);
        }
    }
}
