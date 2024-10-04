package com.DesWebInt_2024_2.PlatGesCapHum.repository;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /*Consultas Basadas en Condiciones
    Métodos personalizados en tu repositorio siguiendo la convención de nombres que
    usa Spring Data JPA.

    Busca una entidad por un campo específico (donde Campo es el nombre de un atributo en la entidad).
    Optional<T> findByCampo(String campo);
    */

    Optional<Usuario> findByEmailUsuario(String email);
}
