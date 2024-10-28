package com.DesWebInt_2024_2.PlatGesCapHum.model;

public class MensajeContacto {

    private String nombre;
    private String telefono;
    private String email;
    private String mensaje;
    private String asunto;

    public MensajeContacto() {
    }

    public MensajeContacto(String nombre, String telefono, String email, String mensaje, String asunto) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.mensaje = mensaje;
        this.asunto = asunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
}
