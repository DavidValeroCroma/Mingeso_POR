package com.example.resumenservice.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaModel {
    private Date fecha;
    private Long numero;
    private Double monto;
}
