package com.example.Facial.Controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.Facial.Clases.Departamento;
import com.example.Facial.Clases.Persona;
import com.example.Facial.Clases.Usuario;
import com.example.Facial.Interfaces.IDepartamentoService;
import com.example.Facial.Interfaces.IPersonaService;
import com.example.Facial.Interfaces.IUsuarioService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@RequestMapping("/persona/") // localhost/cliente/
@Controller
public class PersonaControlador {

    String carpeta = "Persona/";

    @Autowired
    private IPersonaService service;

    @Autowired 
    private IUsuarioService serviceUsuario;

    @Autowired
    private IDepartamentoService service_depar;

    @GetMapping("/") // localhost/cliente/
    public String Mostrar(Model model) {
        List<Usuario> usuarios = serviceUsuario.Listar();
        
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getPersona().getNombre());
        }
        model.addAttribute("usuarios", usuarios);
        return carpeta + "listaPersona";
    }

    @GetMapping("/nuevo")
    public String Nuevo(Model model) {

        List<Departamento> departamentos = service_depar.Listar();
        model.addAttribute("departamentos", departamentos);
        return carpeta + "nuevaPersona";
    }

    @PostMapping("/registrarPersona")
    public String Registrar(@RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("fech_nac") @DateTimeFormat(pattern = "dd-MM-yyyy") String fech_nac,
            @RequestParam("gene") String gene,
            @RequestParam("tip_doc") String tip_doc,

            @RequestParam("tip_usu") String tip_usu,
            @RequestParam("departamento_id") String departamento_id,
            @RequestParam("contra") String password,
            @RequestParam("imagen") MultipartFile imagen,
            Model model) throws ParseException {

        // Formato de fecha esperado para fecha de nacimiento
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Obtener la fecha y hora actual
        Date fechaHoraActual = new Date();

        // Crear un formateador para la fecha y hora en formato completo
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Imprimir la fecha y hora actual formateada (opcional)
        String fechaHoraFormateada = formatoFechaHora.format(fechaHoraActual);
        System.out.println("Fecha y hora actual: " + fechaHoraFormateada);
        // Crear el objeto Persona y establecer sus propiedades
        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        persona.setNombre(nom);
        persona.setApellido(ape);
        persona.setDni(dni);
        persona.setFechaNacimiento(dateFormat.parse(fech_nac));
        persona.setGenero(gene);
        persona.setFechaCreacion(fechaHoraActual);  // Usar directamente fechaHoraActual
        persona.setFechaModificacion(fechaHoraActual);  // Usar directamente fechaHoraActual
        persona.setTipoDocumento(tip_doc);

        usuario.setTipoUsuario(tip_usu);

        usuario.setContraseña(password);   
        usuario.setFechaCreacion(fechaHoraActual);  // Usar directamente fechaHoraActual
        usuario.setFechaModificacion(fechaHoraActual);  // Usar directamente fechaHoraActual
        Departamento departamento = new Departamento();
        departamento = service_depar.BuscarPorId(Integer.parseInt(departamento_id));
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
            }
        }

        service.Guardar(persona);

        usuario.setPersona(persona);
        serviceUsuario.Guardar(usuario);
        

        return Mostrar(model); // nuevoVenta.html
    }

    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id,@RequestParam("idusuario") int idusuario,
            Model model) {
        serviceUsuario.Eliminar(idusuario);
        service.Eliminar(id);
    
        return Mostrar(model);
    }

    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id,
            Model model) {
        Optional<Persona> persona = service.ConsultarId(id);
        Usuario usuario = serviceUsuario.BuscarPorId(id);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(persona.get().getFechaNacimiento());  
        
        System.out.println(formattedDate);
        model.addAttribute("fechanac", formattedDate);
        model.addAttribute("persona", persona);
        model.addAttribute("usuario", usuario);

        return carpeta + "editarPersona";
    }
    
    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
                             @RequestParam("nombre") String nom,
                             @RequestParam("apellido") String ape,
                             @RequestParam("dni") String dni,
                             @RequestParam("fechaNacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") String fech_nac,
                             @RequestParam("genero") String gene,
                             @RequestParam("tipoDocumento") String tip_doc,
                             @RequestParam("imagen") MultipartFile imagen,
                             Model model) throws ParseException {

        // Formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha y hora actual
        Date fechaHoraActual = new Date();
        Persona persona = new Persona();
        Usuario usuarioOptional = serviceUsuario.BuscarPorId(id);

        System.out.println("Actualizar post "+usuarioOptional.id);
        
        
        if (!imagen.isEmpty()) {
            String rutaImagenes = "src/main/resources/static/uploads/";
            try {
                byte[] bytesImagen = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaImagenes + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImagen);
                usuarioOptional.setImagen("/uploads/" + imagen.getOriginalFilename());
                
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error al guardar la imagen.");
                return Mostrar(model);
            }
        }

        persona.setId(id);
        persona.setNombre(nom);
        persona.setApellido(ape);
        persona.setDni(dni);
        persona.setFechaNacimiento(dateFormat.parse(fech_nac));
        persona.setGenero(gene);
        persona.setFechaCreacion(fechaHoraActual); // Convertir String a Date
        persona.setFechaModificacion(fechaHoraActual); // Convertir String a Date
        persona.setTipoDocumento(tip_doc);

        service.Guardar(persona);
        serviceUsuario.Guardar(usuarioOptional);
        return Mostrar(model);
    }
    
    /* 
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("desc") String desc,
                         Model model)
    {
        List<Cliente> clientes = service.BuscarAll(desc);
        model.addAttribute("clientes", clientes);
        return carpeta + "listaClientes";
    }*/

}
