package com.example.resumenservice.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reumen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false, unique = true)
    private Long id;
    private Date fecha;
    private Long numero;
    private Double monto_entrada;
    private Double monto_salida;
    private Double balance;
    private String tipo;
    private String motivo;

}
