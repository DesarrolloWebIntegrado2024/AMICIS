package com.DesWebInt_2024_2.PlatGesCapHum.repository;

import com.DesWebInt_2024_2.PlatGesCapHum.model.VoluntarioGrupo;
import com.DesWebInt_2024_2.PlatGesCapHum.model.VoluntarioGrupoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoluntarioGrupoRepository extends JpaRepository<VoluntarioGrupo, VoluntarioGrupoId> {
    Optional<VoluntarioGrupo> findByVoluntario_IdUsuarioAndGrupo_Id(Long idUsuario, Long idGrupo);
}

