package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    public Administrador() {
        super();
        setTipoUsuario("Administrador"); // Establece el tipo de usuario como "Administrador"
    }
}

