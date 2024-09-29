 <%@ page import="com.DesWebInt_2024_2.PlatGesCapHum.controllers.AOSAnimation" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

 <main class="informacion__contacto main contenedor-m">
        <h1>Únete al equipo de AMICIS creando tu cuenta ahora</h1>
        <p>¡Estás a un paso de marcar la diferencia! Regístrate hoy y comienza a colaborar con nuestra comunidad de
            voluntarios comprometidos. Juntos, podemos llevar esperanza y ayuda a quienes más lo necesitan.</p>
    </main>

    <section class="contenedor-sm">
            <form action="formulario" method="POST" class="formulario">
                <h2>Crea tu cuenta</h2>

                <div class="campo">
                    <label for="nombre">Nombre</label>
                    <input type="text" name="nombre" id="nombre" placeholder="Nombre completo" required>
                </div>

                <div class="campo">
                    <label for="telefono">Teléfono</label>
                    <input type="tel" name="telefono" id="telefono" placeholder="Tu teléfono" pattern="[0-9]{10}"
                        required>
                </div>

                <div class="campo">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" placeholder="Tu email" required>
                </div>

                <div class="campo">
                    <label for="password">Contraseña</label>
                    <input type="password" name="password" id="password" placeholder="Tu Contraseña" required>
                </div>

                <div class="campo">
                    <label for="password2">Repite tu Contraseña</label>
                    <input type="password" name="password2" id="password2" placeholder="Repite tu Contraseña" required>
                </div>

                <div class="accion">
                    <input type="submit" value="Crear cuenta" class="boton">
                </div>
                <div class="acciones">
                    <a href="/login" class="acciones__enlace">¿Ya tienes cuenta? Inicia Sesión</a>
                </div>
            </form>

    </section>