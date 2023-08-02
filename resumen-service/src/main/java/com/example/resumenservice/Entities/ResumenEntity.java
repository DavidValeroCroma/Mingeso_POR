package com.example.resumenservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenEntity {
    private Long id;
    private Date fecha;
    private String numero;
    private Double monto_entrada;
    private Double monto_salida;
    private Double balance;
    private String tipo;
    private String motivo;

}
