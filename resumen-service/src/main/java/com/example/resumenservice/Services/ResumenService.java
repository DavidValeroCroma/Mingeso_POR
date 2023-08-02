package com.example.resumenservice.Services;

import com.example.resumenservice.Entities.ResumenEntity;
import com.example.resumenservice.Models.EntradaModel;
import com.example.resumenservice.Models.SalidaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResumenService {

    @Autowired
    RestTemplate restTemplate;


    private ArrayList<EntradaModel> obtenerEntrada(String fechaI, String fechaF){
        ResponseEntity<ArrayList<EntradaModel>> response = restTemplate.exchange(
                "http://localhost:8001/entrada?fechaInicio=" + fechaI + "&fechaFinal=" + fechaF,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<EntradaModel>>() {}
        );

        ArrayList<EntradaModel> entradas = response.getBody();
        return entradas;
    }

    private ArrayList<SalidaModel> obtenerSalida(String fechaI, String fechaF){
        ResponseEntity<ArrayList<SalidaModel>> response = restTemplate.exchange(
                "http://localhost:8003/salida?fechaInicio=" + fechaI + "&fechaFinal=" + fechaF,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<SalidaModel>>() {}
        );

        ArrayList<SalidaModel> salidas = response.getBody();
        return salidas;
    }



    public ResumenEntity calcularBalanceSalida(SalidaModel salida, Double balance) {
        ResumenEntity newResumen = new ResumenEntity();
        newResumen.setMonto_salida(salida.getMonto());
        newResumen.setFecha(salida.getFecha());
        newResumen.setNumero(salida.getNumero());
        newResumen.setMotivo(salida.getMotivo());
        newResumen.setMonto_entrada(0.0);
        newResumen.setTipo(salida.getTipo());

        //calculamos el balance
        newResumen.setBalance(balance - salida.getMonto());

        return newResumen;
    }

    public ResumenEntity calcularBalanceEntrada(EntradaModel entrada, Double balance){
        ResumenEntity newResumen = new ResumenEntity();
        newResumen.setMonto_salida(0.0);
        newResumen.setFecha(entrada.getFecha());
        newResumen.setNumero(entrada.getNumero());
        newResumen.setMotivo("Ingreso a Caja");
        newResumen.setMonto_entrada(entrada.getMonto());
        newResumen.setTipo("Recibo");

        //calculamos el balance
        newResumen.setBalance(balance + entrada.getMonto());

        return newResumen;
    }


    public ArrayList<ResumenEntity> crearResumen(String fechaInicio, String fechaFinal){

        //filtramos los resumenes
        ArrayList<SalidaModel> salidas = obtenerSalida(fechaInicio , fechaFinal);
        ArrayList<EntradaModel> entradas = obtenerEntrada(fechaInicio , fechaFinal);
        ArrayList<ResumenEntity> resumenes = new ArrayList<>();
        Double balance = 0.0;
        Long idAux = 1L;

        if (salidas == null && entradas == null){
            return resumenes;

        }else if (entradas == null) {
            int i = 0;
            while (i < salidas.size()) {
                ResumenEntity aux = calcularBalanceSalida(salidas.get(i), balance);
                aux.setId(idAux);
                resumenes.add(aux);
                balance = balance - aux.getMonto_salida();
                i++;
                idAux++;

            }

        }else if (salidas == null){
            int j = 0;
            while (j < entradas.size()) {
                ResumenEntity aux = calcularBalanceEntrada(entradas.get(j), balance);
                aux.setId(idAux);
                resumenes.add(aux);
                balance = balance + aux.getMonto_entrada();
                j++;
                idAux++;
            }

        } else {

            int i = 0; //contador para salidas
            int j = 0; //contador para entradas
            while (i < salidas.size() && j < entradas.size()) {
                if (salidas.get(i).getFecha().compareTo(entradas.get(j).getFecha()) > 0) {
                    ResumenEntity aux = calcularBalanceEntrada(entradas.get(j), balance);
                    aux.setId(idAux);
                    resumenes.add(aux);
                    balance = balance + aux.getMonto_entrada();
                    j++;
                    idAux++;
                } else {
                    ResumenEntity aux = calcularBalanceSalida(salidas.get(i), balance);
                    aux.setId(idAux);
                    resumenes.add(aux);
                    balance = balance - aux.getMonto_salida();
                    i++;
                    idAux++;
                }
            }

            // Agregamos los elementos restantes de salidas (si los hay)
            while (i < salidas.size()) {
                ResumenEntity aux = calcularBalanceSalida(salidas.get(i), balance);
                aux.setId(idAux);
                resumenes.add(aux);
                balance = balance - aux.getMonto_salida();
                i++;
                idAux++;
            }

            // Agregamos los elementos restantes de entradas (si los hay)
            while (j < entradas.size()) {
                ResumenEntity aux = calcularBalanceEntrada(entradas.get(j), balance);
                aux.setId(idAux);
                resumenes.add(aux);
                balance = balance + aux.getMonto_entrada();
                j++;
                idAux++;
            }
        }

        return resumenes;
    }

}
