package com.example.salidasservice.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "salida")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false, unique = true)
    private Long id;
    private Date fecha;
    private Long numero;
    private Double monto;
    private String tipo;
    private String motivo;
}
