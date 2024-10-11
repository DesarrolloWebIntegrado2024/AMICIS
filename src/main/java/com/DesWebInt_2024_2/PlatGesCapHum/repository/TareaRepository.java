package com.DesWebInt_2024_2.PlatGesCapHum.repository;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Puedes agregar métodos personalizados si es necesario
}