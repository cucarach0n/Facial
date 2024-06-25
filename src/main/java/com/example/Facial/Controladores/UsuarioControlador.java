package com.example.Facial.Controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Facial.Clases.Departamento;
import com.example.Facial.Clases.Persona;
import com.example.Facial.Clases.Usuario;
import com.example.Facial.Interfaces.IDepartamentoService;
import com.example.Facial.Interfaces.IPersonaService;
import com.example.Facial.Interfaces.IUsuarioService;

@RequestMapping("/usuario/") // localhost/venta/
@Controller
public class UsuarioControlador {

    String carpeta = "/usuario/";

    @Autowired
    private IUsuarioService service;

    @Autowired
    private IPersonaService service_per;

    @Autowired
    private IDepartamentoService service_depar;

    @GetMapping("/") // localhost/venta/
    public String Mostrar(Model model) {
        List<Usuario> usuarios = service.Listar();
        model.addAttribute("usuarios", usuarios);
        return carpeta + "listaUsuario";
    }

    @GetMapping("/nuevo")
    public String Nuevo(Model model) {
        List<Persona> personas = service_per.Listar();
        List<Departamento> departamentos = service_depar.Listar();
        
        model.addAttribute("personas", personas);
        model.addAttribute("departamentos", departamentos);

        return carpeta + "nuevoUsuario"; // nuevoVenta.html
    }

    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model) {
        // Elimina el departamento
        service.Eliminar(id);

        return Mostrar(model);
    }

    @PostMapping("/registrarUsuario")
    public String Registrar(@RequestParam("tip_usu") String tip_usu,
            @RequestParam("contra") String contra,
            @RequestParam("persona_id") Persona persona,
            @RequestParam("departamento_id") Departamento departamento,
            @RequestParam("imagen") MultipartFile imagen,
            Model model) throws ParseException {

        
        
        // Formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Usuario usuario = new Usuario();

        usuario.setTipoUsuario(tip_usu);
        usuario.setContraseña(contra);

        Date fechaHoraActual = new Date();

        // Establecer las fechas en el objeto Departamento
        usuario.setFechaCreacion(fechaHoraActual);
        usuario.setFechaModificacion(fechaHoraActual);

        usuario.setPersona(persona);
        usuario.setDepartamento(departamento);

        if (!imagen.isEmpty()) {
            String rutaImagenes = "src/main/resources/static/uploads/";
            try {
                byte[] bytesImagen = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaImagenes + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImagen);
                usuario.setImagen("/uploads/" + imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error al guardar la imagen.");
                return "usuario/listaUsuario";
            }
        }


        // Guardar el departamento usando el servicio
        service.Guardar(usuario);

        return Mostrar(model);
    }

}
