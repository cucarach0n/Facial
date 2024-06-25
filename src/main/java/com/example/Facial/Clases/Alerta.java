/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@Table(name="alerta")
public class Alerta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Date fechaAlerta;
    private String foto;
    private String status; 
    
    @ManyToOne()
    @JoinColumn(name="camara_id")
    private Camara camara;
}
