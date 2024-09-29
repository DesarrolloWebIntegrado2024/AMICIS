<%@ page import="com.DesWebInt_2024_2.PlatGesCapHum.controllers.AOSAnimation" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="informacion__contacto contenedor-sm">
        <h1>Inicia Sesión en AMICIS</h1>

        <form action="formulario" method="POST" class="formulario">
            <div class="campo">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Tu email" required>
            </div>

            <div class="campo">
                <label for="password">Contraseña</label>
                <input type="password" name="password" id="password" placeholder="Tu Contraseña" required>
            </div>

            <div class="accion">
                <input type="submit" value="Iniciar Sesión" class="boton">
            </div>
        </form>
    </main>