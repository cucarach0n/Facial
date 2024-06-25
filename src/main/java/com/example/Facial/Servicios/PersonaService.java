package com.example.Facial.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facial.Clases.Persona;
import com.example.Facial.Interfaces.IPersonaService;
import com.example.Facial.Repositorios.IPersona;

@Service
public class PersonaService implements IPersonaService {
    
    @Autowired
    private IPersona data;
    
    @Override
    public List<Persona> Listar() {
        return (List<Persona>) data.findAll();
    }

    @Override
    public Optional<Persona> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Persona per) {
        data.save(per);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Persona> BuscarAll() {
        return data.buscarPorTodo();
    }
}
