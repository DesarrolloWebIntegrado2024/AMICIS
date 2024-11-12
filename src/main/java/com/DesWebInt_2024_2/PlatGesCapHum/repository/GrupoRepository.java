package com.DesWebInt_2024_2.PlatGesCapHum.repository;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
