package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected  Long idUsuario;

    @Column(nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String telefonoUsuario;

    @Column(unique = true, nullable = false)
    private String emailUsuario;

    @Column(nullable = false)
    private String contraseniaUsuario;

    @Column(name = "tipo_usuario") // Campo para el tipo de usuario
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombreUsuario, String emailUsuario, String telefonoUsuario, String contraseniaUsuario, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters y setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}