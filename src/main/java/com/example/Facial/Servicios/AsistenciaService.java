package com.example.Facial.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facial.Clases.Asistencia;
import com.example.Facial.Clases.Camara;
import com.example.Facial.Interfaces.IAsistenciaService;
import com.example.Facial.Interfaces.ICamaraService;
import com.example.Facial.Repositorios.IAsistencia;
import com.example.Facial.Repositorios.ICamara;

@Service
public class AsistenciaService implements IAsistenciaService{
    @Autowired
    private IAsistencia data;

    @Override
    public List<Asistencia> Listar() {
        return (List<Asistencia>) data.findAll();
    }

    @Override
    public Optional<Asistencia> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Asistencia depar) {
        data.save(depar);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }
    
}
