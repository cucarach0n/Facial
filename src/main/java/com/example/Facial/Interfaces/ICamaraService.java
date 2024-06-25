/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Facial.Interfaces;
import java.util.List;
import java.util.Optional;

import com.example.Facial.Clases.Camara;
public interface ICamaraService {
    public List<Camara> Listar();
    public Optional<Camara> ConsultarId(int id);
    public void Guardar(Camara depar);
    public void Eliminar(int id);
    public List<Camara> BuscarAll(String desc);
}
