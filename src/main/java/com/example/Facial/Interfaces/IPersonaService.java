package com.example.Facial.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.Facial.Clases.Persona;

public interface IPersonaService {
    
    public List<Persona> Listar();
    public Optional<Persona> ConsultarId(int id);
    public void Guardar(Persona per);
    public void Eliminar(int id);
    public List<Persona> BuscarAll();

}
