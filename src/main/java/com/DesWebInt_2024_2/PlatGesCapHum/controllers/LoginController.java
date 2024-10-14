package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.Administrador;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Usuario;
import com.DesWebInt_2024_2.PlatGesCapHum.model.Voluntario;
import com.DesWebInt_2024_2.PlatGesCapHum.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
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
    public String mostrarLogin(HttpSession session) {
        // Verificar si ya existe una sesión activa
        if (session.getAttribute("usuario") != null) {
            session.invalidate(); // Invalidar la sesión actual si existe
        }
        return "login"; // Archivo HTML login.html
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Optional<Usuario> usuario = usuarioService.encontrarPorEmail(email);

        if (usuario.isPresent() && usuario.get().getContraseniaUsuario().equals(password)) {
            // Guardar información del usuario en la sesión
            session.setAttribute("usuario", usuario.get());

            if (usuario.get() instanceof Administrador) {
                return "redirect:/admin/tareas"; // Redirigir al dashboard del administrador
            } else if (usuario.get() instanceof Voluntario) {
                return "redirect:/"; // Redirigir al área de voluntario
            }
        }
        model.addAttribute("error", "Email o contraseña incorrectos.");
        return "login"; // Volver a la página de login
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Invalidar la sesión
        return "redirect:/login"; // Redirigir al login
    }

}
