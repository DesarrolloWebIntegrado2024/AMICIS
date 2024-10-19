package com.DesWebInt_2024_2.PlatGesCapHum.repository;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailUsuario(String email);
    List<Usuario> findByTipoUsuario(String tipoUsuario); // Cambiar 'rol' por 'tipoUsuario'
}
