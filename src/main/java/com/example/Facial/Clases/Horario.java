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
    public int id;
    public String diasLaborales;
    public String horaEntrada;
    public String horaSalida;
    public Date fechaCreacion;
    public Date fechaModificacion;
}
