package com.example.entradasservice.Services;

import com.example.entradasservice.Entities.EntradaEntity;
import com.example.entradasservice.Repositories.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class EntradaService {
    @Autowired
    EntradaRepository entradaRepository;

    public ArrayList<EntradaEntity> obtenerEntradas(){return (ArrayList<EntradaEntity>)entradaRepository.findAll();}

    public Date convertirString(String fechaString ){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            // Parsear el String a un objeto Date
            fecha = sdf.parse(fechaString);
            System.out.println("Fecha convertida: " + fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public ArrayList<EntradaEntity> obtenerPorIntervaloFecha(String fechaInicio, String fechaFin) {

        Date fechaI = convertirString(fechaInicio);
        Date fechaF = convertirString(fechaFin);

        ArrayList<EntradaEntity> entradas = obtenerEntradas();
        ArrayList<EntradaEntity> entradasAux = new ArrayList<>();
        if(fechaI != null && fechaF != null) {
            for (EntradaEntity entrada : entradas) {
                if (entrada.getFecha().compareTo(fechaI) >= 0 && entrada.getFecha().compareTo(fechaF) <= 0) {
                    entradasAux.add(entrada);
                }
            }

            return entradasAux;
        }

        return entradas;
    }

    public void guardarDb(EntradaEntity entrada){
        if(entrada.getMonto() > 0){
            entradaRepository.save(entrada);
        }
    }
}
