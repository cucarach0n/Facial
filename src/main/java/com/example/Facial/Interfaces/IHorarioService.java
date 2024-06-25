package com.example.Facial.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.Facial.Clases.Horario;

public interface IHorarioService {
    
    public List<Horario> Listar();
    public Optional<Horario> ConsultarId(int id);
    public void Guardar(Horario hora);
    public void Eliminar(int id);
    public List<Horario> BuscarAll(String desc);
}
