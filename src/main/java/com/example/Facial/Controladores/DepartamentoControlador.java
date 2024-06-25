package com.example.Facial.Controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Facial.Clases.Departamento;
import com.example.Facial.Clases.Horario;
import com.example.Facial.Interfaces.IDepartamentoService;
import com.example.Facial.Interfaces.IHorarioService;
import java.util.Date;

@RequestMapping("/departamento/") // localhost/venta/
@Controller
public class DepartamentoControlador {

    String carpeta = "/departamento/";

    @Autowired
    private IDepartamentoService service;

    @Autowired
    private IHorarioService service_hora;

    @GetMapping("/") // localhost/venta/
    public String Mostrar(Model model) {
        List<Departamento> departamentos = service.Listar();
        model.addAttribute("departamentos", departamentos);
        return carpeta + "listaDepartamento";
    }

    @GetMapping("/nuevo")
    public String Nuevo(Model model) {
        List<Horario> horarios = service_hora.Listar();

        model.addAttribute("horarios", horarios);

        return carpeta + "nuevoDepartamento"; // nuevoVenta.html
    }

    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id,
            Model model) {
        // Llama al servicio para eliminar el departamento por ID
        service.Eliminar(id);

        return Mostrar(model);
    }


    @PostMapping("/registrarDepartamento")
    public String Registrar(@RequestParam("nom_depar") String nom_depar,
                            @RequestParam("desc_depar") String desc_depar,
                            @RequestParam("horario_id") Horario horario,
                            Model model) throws ParseException {

        // Formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaHoraActual = new Date();
        Departamento departamento = new Departamento();

        departamento.setNombreDepartamento(nom_depar);
        departamento.setDescripcionDepartamento(desc_depar);

        
        
        // Establecer las fechas en el objeto Departamento
        departamento.setFechaCreacion2(fechaHoraActual);
        departamento.setFechaModificacion2(fechaHoraActual);

        departamento.setHorario(horario);

        // Guardar el departamento usando el servicio
        service.Guardar(departamento);

        return Mostrar(model);
    }

}
