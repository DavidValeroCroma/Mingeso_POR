package com.example.entradasservice.Controllers;

import com.example.entradasservice.Entities.EntradaEntity;
import com.example.entradasservice.Services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/entrada")
public class EntradaController {
    @Autowired
    EntradaService entradaService;

    @GetMapping
    public ResponseEntity<ArrayList<EntradaEntity>> listar(){
        ArrayList<EntradaEntity> listar = entradaService.obtenerEntradas();
        if (listar.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listar);
    }

    @PostMapping("/upload")
    public void uploadEntrada(EntradaEntity entrada){
        if (entrada == null){
            entradaService.guardarDb(entrada);
        }
    }
}
