package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Administrador;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping()
public class LoginController {

    //INYECCION DE DEPENDENCIA
    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario de login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login"; // Nombre de la vista login.html
    }

    // Manejar la lógica de autenticación
    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<Usuario> usuario = usuarioService.encontrarPorEmail(email);

        // Verificar que el usuario existe y la contraseña es correcta
        if (usuario.isPresent() && usuario.get().getContraseniaUsuario().equals(password)) {
            // Redirigir según el tipo de usuario
            if (usuario.get() instanceof Administrador) {
                model.addAttribute("mensaje", "Inicio de sesión exitoso.");
                return "redirect:/tarea";
            } else if (usuario.get() instanceof Voluntario) {
                model.addAttribute("mensaje", "Inicio de sesión exitoso.");
                return "principal"; // Redirige a la página de inicio para voluntarios
            }
        }
        // Si las credenciales son incorrectas o el usuario no está autorizado
        model.addAttribute("error", "Email o contraseña incorrectos.");
        return "login"; // Volver a la página de login
    }

}
