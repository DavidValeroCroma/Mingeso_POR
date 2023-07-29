package com.example.resumenservice.Controllers;

import com.example.resumenservice.Entities.ResumenEntity;
import com.example.resumenservice.Services.ResumenService;
import org.apache.http.protocol.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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

    @PostMapping("/upload")
    public void guardarResumen(ResumenEntity resumen){
        if (resumen != null){
            resumenService.guardaDB(resumen);
        }
    }
}
