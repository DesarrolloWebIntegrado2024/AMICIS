package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    //INYECCION DE DEPENDENCIA
    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario para crear un usuario
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("usuario", new Usuario()); // Agregar un nuevo objeto Usuario
        return "crear-cuenta"; // Nombre del archivo HTML sin la extensión
    }

    // Registrar un nuevo usuario
    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.registrarUsuario(usuario);
            model.addAttribute("mensaje", "Usuario creado exitosamente.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error inesperado: " + e.getMessage());
            // Opcionalmente, puedes registrar el error en los logs
            e.printStackTrace();
        }
        return "crear-cuenta"; // Volver al formulario
    }

    // Mostrar el formulario de login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login"; // Nombre de la vista login.html
    }

    // Manejar la lógica de autenticación
    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<Usuario> usuario = usuarioService.encontrarPorEmail(email);

        if (usuario.isPresent() && usuario.get().getContraseniaUsuario().equals(password)) {
            // Si las credenciales son correctas
            model.addAttribute("mensaje", "Inicio de sesión exitoso.");
            return "principal"; // Redirigir al dashboard o página de inicio
        } else {
            // Si las credenciales son incorrectas
            model.addAttribute("error", "Email o contraseña incorrectos.");
            return "login"; // Volver a la página de login
        }
    }


}

