package com.example.Facial.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer>{
    
    //Aqui pueden ir consultas a BD adicionales
    @Query(value="SELECT * FROM usuario", nativeQuery = true)
    List<Usuario> buscarPorTodo(String desc);

    @Query(value="SELECT u.* FROM usuario u inner join persona p on u.persona_id = p.id where p.id = ?1 limit 1", nativeQuery = true)
   Usuario buscarPorId(int id);

   @Query(value="delete u FROM usuario u inner join persona p on u.persona_id = p.id where p.id = ?1", nativeQuery = true)
   void eliminarPorId(int id);

}
