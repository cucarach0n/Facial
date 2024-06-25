/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.Facial.Repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Camara;
public interface ICamara extends CrudRepository<Camara, Integer> {
    //Aqui pueden ir consultas a BD adicionales
    @Query(value="SELECT * FROM Camara", nativeQuery = true)
    List<Camara> buscarPorTodo(String desc);
}
