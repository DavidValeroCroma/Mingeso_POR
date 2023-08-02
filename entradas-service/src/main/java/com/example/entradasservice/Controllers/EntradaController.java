package com.example.entradasservice.Controllers;

import com.example.entradasservice.Entities.EntradaEntity;
import com.example.entradasservice.Services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/entrada")
public class EntradaController {
    @Autowired
    EntradaService entradaService;

    @GetMapping
    public ResponseEntity<ArrayList<EntradaEntity>> listarPorFecha(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFinal") String fechaFinal){
        ArrayList<EntradaEntity> listar = entradaService.obtenerPorIntervaloFecha(fechaInicio,fechaFinal);
        if (listar.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listar);
    }

    @GetMapping("/list")
    public ResponseEntity<ArrayList<EntradaEntity>> listar(){
        ArrayList<EntradaEntity> listar = entradaService.obtenerEntradas();
        if (listar.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listar);
    }

    @PostMapping("/upload")
    public void uploadEntrada(@RequestBody EntradaEntity entrada){
        if (entrada != null){
            entradaService.guardarDb(entrada);
        }
    }
}
