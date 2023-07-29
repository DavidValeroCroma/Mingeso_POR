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
    private Long codigo;
    private Long numero;
    private Long monto_entrada;
    private Long monto_salida;
    private Long balance;
    private String tipo;
    private String motivo;

}
