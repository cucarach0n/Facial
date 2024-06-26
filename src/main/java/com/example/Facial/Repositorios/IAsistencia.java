package com.example.Facial.Repositorios;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Facial.Clases.Asistencia;
import com.example.Facial.Clases.Usuario;

public interface IAsistencia extends CrudRepository<Asistencia, Integer> {
   @Query(value="select * from Asistencia where fecha_asistencia between ?1 and ?2", nativeQuery = true)
   List<Asistencia> buscarPorFecha(Date fechaInicio, Date fechaFin);
}
