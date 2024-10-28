package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import com.DesWebInt_2024_2.PlatGesCapHum.model.MensajeContacto;
import com.DesWebInt_2024_2.PlatGesCapHum.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ContactoController {

    @Autowired
    private EmailService emailService;

    // Método para mostrar el formulario de contacto
    @GetMapping("/contacto")
    public String mostrarFormularioContacto(Model model) {
        model.addAttribute("mensajeContacto", new MensajeContacto()); // Inicializa el objeto para el formulario
        return "contacto"; // Nombre de la vista que contiene el formulario
    }

    @PostMapping("/contacto")
    public String procesarFormularioContacto(@ModelAttribute MensajeContacto mensajeContacto, Model model) {
        try {
            String destinatario = "amicisvoluntariado@gmail.com";  // Correo de la empresa
            String asunto = mensajeContacto.getNombre()+" ¡Se quiere comunicar con nosotros!: " + mensajeContacto.getAsunto();
            String rutaLogo = "src/main/resources/static/images/img2.jpg";  // Ruta del logo

            // Crear las variables que se pasarán a la plantilla
            Map<String, Object> variables = new HashMap<>();
            variables.put("nombre", mensajeContacto.getNombre());
            variables.put("telefono", mensajeContacto.getTelefono());
            variables.put("email", mensajeContacto.getEmail());
            variables.put("asunto", mensajeContacto.getAsunto());
            variables.put("mensaje", mensajeContacto.getMensaje());

            // Enviar el correo con la plantilla
            emailService.enviarCorreoConPlantilla(destinatario, asunto, variables,
                    mensajeContacto.getEmail(), mensajeContacto.getNombre(), rutaLogo);

            model.addAttribute("mensaje", "Tu mensaje ha sido enviado correctamente.");
        } catch (MessagingException e) {
            model.addAttribute("error", "Hubo un error al enviar el mensaje. Por favor, intenta de nuevo.");
            e.printStackTrace();
        }
        return "redirect:/contacto"; // Retorna a la vista del formulario
    }
}
