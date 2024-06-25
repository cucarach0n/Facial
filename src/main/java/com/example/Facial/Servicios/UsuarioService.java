package com.example.Facial.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facial.Clases.Usuario;
import com.example.Facial.Interfaces.IUsuarioService;
import com.example.Facial.Repositorios.IUsuario;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private IUsuario data;
    
    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }

    @Override
    public Optional<Usuario> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Usuario usu) {
        data.save(usu);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Usuario> BuscarAll(String desc) {
        return data.buscarPorTodo(desc);
    }

    @Override
    public Usuario BuscarPorId(int id) {
       return data.buscarPorId(id);
    }

    @Override
    public void eliminarPorId(int id) {
        data.eliminarPorId(id);
    }

}
