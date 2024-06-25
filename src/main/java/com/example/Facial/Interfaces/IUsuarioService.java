package com.example.Facial.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.Facial.Clases.Usuario;

public interface IUsuarioService {
    
    public List<Usuario> Listar();
    public Optional<Usuario> ConsultarId(int id);
    public void Guardar(Usuario usu);
    public void Eliminar(int id);
    public List<Usuario> BuscarAll(String desc);
    public Usuario BuscarPorId(int id);
    public void eliminarPorId(int id);
}
