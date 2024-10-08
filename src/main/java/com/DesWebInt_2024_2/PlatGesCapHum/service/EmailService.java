package com.DesWebInt_2024_2.PlatGesCapHum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void enviarCorreoConPlantilla(String destinatario, String asunto, Map<String, Object> variables, String replyTo, String nombreUsuario, String rutaLogo) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        // Cargar la plantilla y pasarle las variables dinámicas
        Context context = new Context();
        context.setVariables(variables);
        String cuerpoHtml = templateEngine.process("contacto-email", context);  // "contacto-email" es el nombre de la plantilla

        // Configuramos el remitente (From)
        helper.setFrom(nombreUsuario + " <" + replyTo + ">");
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(cuerpoHtml, true);  // true para indicar que el cuerpo es HTML
        helper.setReplyTo(replyTo);

        // Adjuntar el logo
        FileSystemResource logo = new FileSystemResource(rutaLogo);
        helper.addInline("logo", logo);  // El "cid:logo" debe coincidir con el src en el HTML

        mailSender.send(mensaje);
    }
}
