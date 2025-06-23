package ips.citas.services;

import ips.citas.entity.Usuario;

public interface UsuarioService {
    String registrarUsuario(Usuario usuario);
    String login(String username, String password);
    public Usuario buscarPorUsername(String username);
}

