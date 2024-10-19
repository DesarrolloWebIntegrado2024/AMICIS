package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Grupo;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    // Método para obtener un grupo por su ID
    public Grupo obtenerGrupoPorId(Long grupoId) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(grupoId);
        return grupoOptional.orElse(null); // Devuelve null si no se encuentra el grupo
    }

    // Método para guardar el grupo en la base de datos
    public Grupo guardarGrupo(Grupo grupo) {
        return grupoRepository.save(grupo); // Guarda y retorna el grupo
    }
}
