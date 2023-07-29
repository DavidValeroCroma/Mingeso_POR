package com.example.resumenservice.Services;

import com.example.resumenservice.Entities.ResumenEntity;
import com.example.resumenservice.Repositories.ResumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResumenService {
    @Autowired
    ResumenRepository resumenRepository;

    public ArrayList<ResumenEntity> obtenerResumenes(){
        return (ArrayList<ResumenEntity>) resumenRepository.findAll();
    }

    public ResumenEntity obtenerUltimoResumen(){
        ArrayList<ResumenEntity> resumenes = obtenerResumenes();
        resumenes.get(resumenes.size()-1); //ver si efectivamente es el ultimo
        return resumenes.get(obtenerResumenes().size()-1);
    }

    private void sendEntrada(){}

    private void sendSalida(){}

    public void guardaDB(ResumenEntity resumen){
        //realizar verificaciones de datos

        //mandar al service correspondiente
        if (resumen.getMonto_entrada() > 0){
            sendEntrada();
        }else{
            sendSalida();
        }
        resumenRepository.save(resumen);
    }
}
