package com.example.Facial.Clases;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public Date fechaAsistencia;
    public String tipoAsistencia;
    public String EstadoAsistencia;

    @ManyToOne()
    @JoinColumn(name="camara_id")
    public Camara camara;

    @ManyToOne()
    @JoinColumn(name="usuario_id")
    public Usuario usuario;

}
