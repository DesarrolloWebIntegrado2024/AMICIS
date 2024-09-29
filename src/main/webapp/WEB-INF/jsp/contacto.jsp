<%@ page import="com.DesWebInt_2024_2.PlatGesCapHum.controllers.AOSAnimation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="informacion__contacto main contenedor-m">
        <h1>¿Deseas obtener información extra sobre AMICIS?</h1>
        <p>Estamos emocionados de conocer a personas apasionadas que quieran hacer una diferencia. Por favor, completa el siguiente formulario para que podamos conocerte mejor y discutir las oportunidades disponibles.</p>
    </main>

    <section class="seccion contenedor-sm" <%=AOSAnimation.getAnimation() %>>
        <form action="formulario" method="POST" class="formulario">
            <h2>Llena el formulario de contacto para recibir tu petición</h2>

            <div class="campo">
                <label for="nombre">Nombre</label>
                <input type="text" name="nombre" id="nombre" placeholder="Nombre completo" required>
            </div>

            <div class="campo">
                <label for="telefono">Teléfono</label>
                <input type="tel" name="telefono" id="telefono" placeholder="Tu teléfono" pattern="[0-9]{10}" required>
            </div>

            <div class="campo">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Tu email" required>
            </div>

            <div class="campo">
                <label for="mensaje">Mensaje</label>
                <textarea name="mensaje" id="mensaje" placeholder="Tu mensaje" rows="5" required></textarea>
            </div>

            <div class="campo">
                <label for="asunto">Asunto</label>
                <select name="asunto" id="asunto" required>
                    <option value="">Selecciona un asunto</option>
                    <option value="consulta">Consulta</option>
                    <option value="soporte">Soporte</option>
                    <option value="sugerencia">Sugerencia</option>
                    <option value="otro">Otro</option>
                </select>
            </div>

            <div class="accion">
                <input type="submit" value="Enviar" class="boton">
            </div>
        </form>
    </section>