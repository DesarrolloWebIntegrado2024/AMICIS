package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    //INYECCION DE DEPENDENCIA
    // UsuarioService -> Llama a la interfaz UsuarioRepository
    @Autowired
    private UsuarioRepository usuarioRepository;


    // Método para registrar usuarios
    public Usuario registrarUsuario(Usuario usuario) {
        // Validar que la contraseña no sea nula o vacía
        if (usuario.getContraseniaUsuario() == null || usuario.getContraseniaUsuario().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }

        // Validar que el email no esté ya registrado
        if (usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        // Guardar el usuario tal como está
        return usuarioRepository.save(usuario);
    }

    // Buscar usuario por email
    public Optional<Usuario> encontrarPorEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }


}


