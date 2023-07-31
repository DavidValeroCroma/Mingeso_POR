package com.example.resumenservice.Controllers;

import com.example.resumenservice.Entities.ResumenEntity;
import com.example.resumenservice.Services.ResumenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/resumen")
public class ResumenController {

    @Autowired
    ResumenService resumenService;

    @GetMapping
    public ResponseEntity<ArrayList<ResumenEntity>> obtenerResumenes(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFinal") String fechaFinal){

        ArrayList<ResumenEntity> resumenes = resumenService.crearResumen(fechaInicio, fechaFinal);
        if (resumenes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resumenes);
    }

}
