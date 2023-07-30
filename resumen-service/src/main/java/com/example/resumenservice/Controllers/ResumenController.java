package com.example.resumenservice.Controllers;

import com.example.resumenservice.Entities.ResumenEntity;
import com.example.resumenservice.Services.ResumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/resumen")
public class ResumenController {

    @Autowired
    ResumenService resumenService;

    @GetMapping
    public ResponseEntity<ArrayList<ResumenEntity>> obtenerResumenes(){
        ArrayList<ResumenEntity> resumenes = resumenService.obtenerResumenes();
        if (resumenes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumenes);
    }

    @GetMapping("/select")
    public ResponseEntity<ArrayList<ResumenEntity>> obtenerPorIntervalo(Date fechaInicio, Date fechaFinal){
        ArrayList<ResumenEntity> resumenes = resumenService.obtenerPorIntervaloFecha(fechaInicio,fechaFinal);
        if (resumenes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumenes);
    }

    @PostMapping("/upload")
    public void guardarResumen(Date fechaInicio, Date fechaFinal){
        if (fechaInicio != null && fechaFinal != null){
            resumenService.crearResumen(fechaInicio,fechaFinal);
        }
    }
}
