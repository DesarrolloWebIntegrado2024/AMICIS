package com.DesWebInt_2024_2.PlatGesCapHum.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "lider_id")
    private Lider lider;

    @ManyToOne // Nueva relación con Grupo
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTarea estadoTarea;

    @Column(nullable = false)
    private int cantidadVoluntariosInscritos = 0;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
    private Set<Grupo> grupos = new HashSet<>();

    public enum EstadoTarea {
        PENDIENTE,
        COMPLETA
    }

    public Tarea() {
        this.estadoTarea = EstadoTarea.PENDIENTE; // Estado inicial
    }

    public Tarea(Long id, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Lider lider, Grupo grupo, EstadoTarea estadoTarea, int cantidadVoluntariosInscritos, Set<Grupo> grupos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lider = lider;
        this.grupo = grupo; // Inicializar grupo
        this.estadoTarea = estadoTarea;
        this.cantidadVoluntariosInscritos = cantidadVoluntariosInscritos;
        this.grupos = grupos;
    }

    // Método para crear y agregar un grupo a la tarea
    public Grupo crearGrupo() {
        Grupo grupo = new Grupo();
        grupo.setTarea(this);
        grupos.add(grupo);
        this.grupo = grupo; // Asignar el nuevo grupo a la tarea
        return grupo;
    }

    // Método para inscribir un voluntario en la tarea
    public boolean inscribirVoluntario(Voluntario voluntario) {
        if (cantidadVoluntariosInscritos < 10) {
            cantidadVoluntariosInscritos++; // Incrementar la cantidad de voluntarios inscritos
            return true; // Inscripción exitosa
        }
        return false; // No se pudo inscribir porque ya hay 10 voluntarios
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
        if (lider != null) {
            lider.getTareasAsignadas().add(this); // Mantiene la consistencia bidireccional
        }
    }

    public Grupo getGrupo() { // Método para acceder al grupo
        return grupo;
    }

    public void setGrupo(Grupo grupo) { // Método para establecer el grupo
        this.grupo = grupo;
    }

    public EstadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(EstadoTarea estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public int getCantidadVoluntariosInscritos() {
        return cantidadVoluntariosInscritos;
    }

    public void setCantidadVoluntariosInscritos(int cantidadVoluntariosInscritos) {
        this.cantidadVoluntariosInscritos = cantidadVoluntariosInscritos;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }
}
