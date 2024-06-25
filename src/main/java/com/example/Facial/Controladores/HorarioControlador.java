package com.example.Facial.Controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Facial.Clases.Horario;
import com.example.Facial.Interfaces.IHorarioService;

@RequestMapping("/horario/") // localhost/cliente/
@Controller
public class HorarioControlador {

    String carpeta = "Horario/";

    @Autowired
    private IHorarioService service;

    @GetMapping("/") // localhost/cliente/
    public String Mostrar(Model model) {
        List<Horario> horarios = service.Listar();
        model.addAttribute("horarios", horarios);
        return carpeta + "listaHorario";
    }

    @GetMapping("/nuevo")
    public String Nuevo() {
        return carpeta + "nuevoHorario";
    }

    @PostMapping("/registrarHorario")
    public String Registrar(@RequestParam("dias_lab") String dias_lab,
            @RequestParam("horaEntrada") String horaEntrada,
            @RequestParam("horaSalida") String horaSalida,
            Model model) throws ParseException {

        // Formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Horario horario = new Horario();
        Date fechaHoraActual = new Date();
        horario.setDiasLaborales(dias_lab);
        horario.setHoraEntrada(horaEntrada);
        horario.setHoraSalida(horaSalida);
        horario.setFechaCreacion(fechaHoraActual); // Convertir String a Date
        horario.setFechaModificacion(fechaHoraActual); // Convertir String a Date

        service.Guardar(horario);

        return Mostrar(model);
    }

    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id,
            Model model) {
        service.Eliminar(id);

        return Mostrar(model);
    }

    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id,
            Model model) {
        Optional<Horario> horario = service.ConsultarId(id);
        model.addAttribute("horario", horario);
        return carpeta + "editarHorario";
    }

    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
            @RequestParam("diasLaborales") String dias_lab,
            @RequestParam("horaEntrada") String horaEntrada,
            @RequestParam("horaSalida") String horaSalida,

            Model model) throws ParseException {

        // Formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaHoraActual = new Date();
        Horario horario = new Horario();
        
        horario.setId(id);
        horario.setDiasLaborales(dias_lab);
        horario.setHoraEntrada(horaEntrada);
        horario.setHoraSalida(horaSalida);
        horario.setFechaCreacion(fechaHoraActual); // Convertir String a Date
        horario.setFechaModificacion(fechaHoraActual); // Convertir String a Date
        
        service.Guardar(horario);
        
        return Mostrar(model);
    }

}
