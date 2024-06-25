package com.example.Facial.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facial.Clases.Departamento;
import com.example.Facial.Interfaces.IDepartamentoService;
import com.example.Facial.Repositorios.IDepartamento;

@Service
public class DepartamentoService implements IDepartamentoService{
    
    @Autowired
    private IDepartamento data;
    
    @Override
    public List<Departamento> Listar() {
        return (List<Departamento>) data.findAll();
    }

    @Override
    public Optional<Departamento> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Departamento depar) {
        data.save(depar);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Departamento> BuscarAll(String desc) {
        return data.buscarPorTodo(desc);
    }

    @Override
    public Departamento BuscarPorId(int id) {
        return data.buscarPorId(id);
    }

}
