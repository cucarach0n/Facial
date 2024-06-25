package com.example.Facial.Clases;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="horario")
public class Horario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String diasLaborales;
    private String horaEntrada;
    private String horaSalida;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
