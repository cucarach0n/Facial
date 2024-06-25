package com.example.Facial.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Persona;

public interface IPersona extends CrudRepository<Persona, Integer> {
    
    @Query(value="SELECT * FROM persona", nativeQuery = true)
    List<Persona> buscarPorTodo();
}
