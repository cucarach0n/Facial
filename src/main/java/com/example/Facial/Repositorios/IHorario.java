package com.example.Facial.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Horario;

public interface IHorario extends CrudRepository<Horario, Integer>{
    
    //Aqui pueden ir consultas a BD adicionales
    @Query(value="SELECT * FROM horario", nativeQuery = true)
    List<Horario> buscarPorTodo(String desc);
}
