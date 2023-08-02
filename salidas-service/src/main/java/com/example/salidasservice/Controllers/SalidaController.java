package com.example.salidasservice.Controllers;


import com.example.salidasservice.Entities.SalidaEntity;
import com.example.salidasservice.Repositiory.SalidaRepository;
import com.example.salidasservice.Services.SalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/salida")
public class SalidaController {
    @Autowired
    SalidaService salidaService;

    @GetMapping
    public ResponseEntity<ArrayList<SalidaEntity>> listarPorFecha(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFinal") String fechaFinal){
        ArrayList<SalidaEntity> lista = salidaService.obtenerPorIntervaloFecha(fechaInicio,fechaFinal);
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/listar")
    public ResponseEntity<ArrayList<SalidaEntity>> obtenerSalidas(){
        ArrayList<SalidaEntity> lista = salidaService.obtenerSalidas();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/upload")
    public void guardarSalida(@RequestBody SalidaEntity salida){
        if (salida != null){
            salidaService.guardarDB(salida);
        }
    }
}
