package com.example.Facial.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Departamento;

public interface IDepartamento extends CrudRepository<Departamento, Integer>{
    
    //Aqui pueden ir consultas a BD adicionales
    @Query(value="SELECT * FROM departamento", nativeQuery = true)
    List<Departamento> buscarPorTodo(String desc);

    @Query(value="select * from departamento where id = ?1",nativeQuery=true)
    Departamento buscarPorId(int id);
}
