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
    public int id;
    public String nombreDepartamento;
    public String descripcionDepartamento;
    public Date fechaCreacion2;
    public Date fechaModificacion2;

    @ManyToOne()
    @JoinColumn(name="horario_id")
    public Horario horario;

    @OneToMany(mappedBy ="departamento", fetch = FetchType.LAZY)
    public List<Usuario> usuario;

    // Métodos getters y setters
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }   

    public Date getFechaCreacion2() {
        return fechaCreacion2;
    }

    public void setFechaCreacion2(Date fechaCreacion2) {
        this.fechaCreacion2 = fechaCreacion2;
    }
}
