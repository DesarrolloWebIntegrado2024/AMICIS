package com.DesWebInt_2024_2.PlatGesCapHum.service;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public Optional<Voluntario> findById(Long id) {
        return voluntarioRepository.findById(id); // Busca el voluntario por ID
    }
}