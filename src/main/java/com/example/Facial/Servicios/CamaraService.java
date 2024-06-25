/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Facial.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facial.Clases.Camara;
import com.example.Facial.Interfaces.ICamaraService;
import com.example.Facial.Repositorios.ICamara;

@Service
public class CamaraService implements ICamaraService {
    @Autowired
    private ICamara data;
    
    @Override
    public List<Camara> Listar() {
        return (List<Camara>) data.findAll();
    }

    @Override
    public Optional<Camara> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Camara camara) {
        data.save(camara);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Camara> BuscarAll(String desc) {
        return data.buscarPorTodo(desc);
    }
    
}
