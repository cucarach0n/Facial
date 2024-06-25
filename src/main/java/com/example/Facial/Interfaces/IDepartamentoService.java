package com.example.Facial.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.Facial.Clases.Departamento;

public interface IDepartamentoService {
    
    public List<Departamento> Listar();
    public Optional<Departamento> ConsultarId(int id);
    public void Guardar(Departamento depar);
    public void Eliminar(int id);
    public List<Departamento> BuscarAll(String desc);
    public Departamento BuscarPorId(int id);

}
