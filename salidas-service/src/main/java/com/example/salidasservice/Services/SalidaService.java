package com.example.salidasservice.Services;

import com.example.salidasservice.Entities.SalidaEntity;
import com.example.salidasservice.Repositiory.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SalidaService {
    @Autowired
    SalidaRepository salidaRepository;

    public ArrayList<SalidaEntity> obtenerSalidas() {
        return (ArrayList<SalidaEntity>) salidaRepository.findAll();
    }

    public Date convertirString(String fechaString) {

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

    public ArrayList<SalidaEntity> obtenerPorIntervaloFecha(String fechaInicio, String fechaFin) {

        Date fechaI = convertirString(fechaInicio);
        Date fechaF = convertirString(fechaFin);

        ArrayList<SalidaEntity> salidas = obtenerSalidas();
        ArrayList<SalidaEntity> salidasAux = new ArrayList<>();
        if (fechaI != null && fechaF != null) {
            for (SalidaEntity salida : salidas) {
                if (salida.getFecha().compareTo(fechaI) >= 0 && salida.getFecha().compareTo(fechaF) <= 0) {
                    salidasAux.add(salida);
                }
            }

            return salidasAux;
        }

        return salidas;
    }

    public void guardarDB(SalidaEntity salida) {
        System.out.println("Guardando...");
        salidaRepository.save(salida);
        System.out.println("Guardado");
    }
}
