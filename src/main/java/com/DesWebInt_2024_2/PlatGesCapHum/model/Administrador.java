package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {
    // Puedes agregar atributos específicos de Administrador si es necesario

    public Administrador() {
        super();
    }

}

