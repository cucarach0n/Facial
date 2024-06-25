package com.example.Facial.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facial.Clases.Horario;
import com.example.Facial.Interfaces.IHorarioService;
import com.example.Facial.Repositorios.IHorario;

@Service
public class HorarioService implements IHorarioService{
    
    @Autowired
    private IHorario data;
    
    @Override
    public List<Horario> Listar() {
        return (List<Horario>) data.findAll();
    }

    @Override
    public Optional<Horario> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Horario hora) {
        data.save(hora);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Horario> BuscarAll(String desc) {
        return data.buscarPorTodo(desc);
    }

}
