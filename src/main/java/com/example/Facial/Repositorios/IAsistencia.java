package com.example.Facial.Repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Asistencia;

public interface IAsistencia extends CrudRepository<Asistencia, Integer> {
    
}
