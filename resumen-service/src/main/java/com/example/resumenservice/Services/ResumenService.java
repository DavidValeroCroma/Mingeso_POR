package com.example.resumenservice.Services;

import com.example.resumenservice.Entities.ResumenEntity;
import com.example.resumenservice.Models.EntradaModel;
import com.example.resumenservice.Models.SalidaModel;
import com.example.resumenservice.Repositories.ResumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void guardaDB(ResumenEntity resumen){
        resumenRepository.save(resumen);
    }

    private ArrayList<EntradaModel> obtenerEntrada(){}

    private ArrayList<SalidaModel> obtenerSalida(){}



    public Double calcularBalanceSalida(SalidaModel salida, Double balance) {
        ResumenEntity newResumen = new ResumenEntity();
        newResumen.setMonto_salida(salida.getMonto());
        newResumen.setFecha(salida.getFecha());
        newResumen.setNumero(salida.getNumero());
        newResumen.setMotivo(salida.getMotivo());
        newResumen.setMonto_entrada(0.0);
        newResumen.setTipo(salida.getTipo());

        //calculamos el balance
        newResumen.setBalance(balance - salida.getMonto());
        guardaDB(newResumen);

        return (balance - salida.getMonto());
    }

    public Double calcularBalanceEntrada(EntradaModel entrada, Double balance){
        ResumenEntity newResumen = new ResumenEntity();
        newResumen.setMonto_salida(0.0);
        newResumen.setFecha(entrada.getFecha());
        newResumen.setNumero(entrada.getNumero());
        newResumen.setMotivo("Ingreso a Caja");
        newResumen.setMonto_entrada(entrada.getMonto());
        newResumen.setTipo("Recibo");

        //calculamos el balance
        newResumen.setBalance(balance + entrada.getMonto());
        guardaDB(newResumen);

        return (balance + entrada.getMonto());
    }

    public void crearResumen(Date fechaInicio, Date fechaFinal){
        ArrayList<SalidaModel> salidas = obtenerSalida();
        ArrayList<EntradaModel> entradas = obtenerEntrada();
        Double balance = 0.0;

        int i = 0; //contador para salidas
        int j = 0; //contador para entradas
        while ( i < salidas.size() && j < entradas.size()){
            if (salidas.get(i).getFecha().compareTo(entradas.get(j).getFecha()) > 0){
                balance = calcularBalanceEntrada(entradas.get(j), balance);
                j = j+1;
            }else {
                balance = calcularBalanceSalida(salidas.get(i), balance);
                i=i+1;
            }
        }

        // Agregamos los elementos restantes de salidas (si los hay)
        while (i < salidas.size()) {
            balance = calcularBalanceSalida(salidas.get(i), balance);
            i++;
        }

        // Agregamos los elementos restantes de entradas (si los hay)
        while (j < entradas.size()) {
            balance = calcularBalanceEntrada(entradas.get(j), balance);
            j++;
        }
    }

}
