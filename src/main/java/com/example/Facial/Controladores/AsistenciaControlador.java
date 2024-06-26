package com.example.Facial.Controladores;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Facial.Clases.Asistencia;
import com.example.Facial.Interfaces.IAsistenciaService;

@RequestMapping("/asistensia/") // localhost/venta/
@Controller
public class AsistenciaControlador {
     String carpeta = "/asistensia/";

    @Autowired
    private IAsistenciaService serviceAsistencia;

    @GetMapping("/") // localhost/venta/
    public String Mostrar(Model model) {
        List<Asistencia> asistencias = serviceAsistencia.Listar();
        for (Asistencia asistencia : asistencias) {
            System.out.println(asistencia.usuario.persona.getNombre() );
            System.out.println(asistencia.usuario.departamento.nombreDepartamento );
            System.out.println(asistencia.usuario.departamento.horario.diasLaborales );
        }
        model.addAttribute("asistencias", asistencias);
        return carpeta + "reporteAsistencia";
    }
    
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("fechaInicio") String fechaInicio,@RequestParam("fechaFin") String fechaFin , Model model) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaAsistenciaInicio = new Date(formato.parse(fechaInicio).getTime());
        Date fechaAsistenciaFin = new Date(formato.parse(fechaFin).getTime());
        List<Asistencia> asistencias = serviceAsistencia.buscarPorFecha(fechaAsistenciaInicio,fechaAsistenciaFin);
        for (Asistencia asistencia : asistencias) {
            System.out.println(asistencia.usuario.persona.getNombre() );
            System.out.println(asistencia.usuario.departamento.nombreDepartamento );
            System.out.println(asistencia.usuario.departamento.horario.diasLaborales );
        }
        model.addAttribute("asistencias", asistencias);
        return carpeta + "reporteAsistencia";
    }

}
