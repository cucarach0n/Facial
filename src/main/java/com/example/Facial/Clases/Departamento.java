package com.example.Facial.Clases;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="departamento")
public class Departamento {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    public String nombreDepartamento;
    private String descripcionDepartamento;
    private Date fechaCreacion2;
    private Date fechaModificacion2;

    @ManyToOne()
    @JoinColumn(name="horario_id")
    private Horario horario;

    @OneToMany(mappedBy ="departamento", fetch = FetchType.LAZY)
    public List<Usuario> usuario;
}
