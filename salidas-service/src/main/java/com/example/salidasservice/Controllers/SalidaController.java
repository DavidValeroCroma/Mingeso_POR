package com.example.salidasservice.Controllers;


import com.example.salidasservice.Entities.SalidaEntity;
import com.example.salidasservice.Repositiory.SalidaRepository;
import com.example.salidasservice.Services.SalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/salida")
public class SalidaController {
    @Autowired
    SalidaService salidaService;

    @GetMapping
    public ResponseEntity<ArrayList<SalidaEntity>> obtenerSalidas(){
        ArrayList<SalidaEntity> lista = salidaService.obtenerSalidas();
        if (lista.isEmpty()){
            ResponseEntity.noContent().build();
        }
        ResponseEntity.ok(lista);
    }

    @PostMapping("/upload")
    public void guardarSalida(SalidaEntity salida){
        if (salida != null){
            salidaService.guardarDB(salida);
        }
    }
}
