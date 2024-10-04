package com.DesWebInt_2024_2.PlatGesCapHum.repository;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailUsuario(String email);
}