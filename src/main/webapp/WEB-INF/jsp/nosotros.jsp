<%@ page import="com.DesWebInt_2024_2.PlatGesCapHum.controllers.AOSAnimation" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="informacion__nosotros contenedor-m">
    <h1>Sobre Nosotros</h1>
    <div class="promocion__contacto" <%=AOSAnimation.getAnimation() %>>
        <picture>
            <source srcset="/images/img5.avif" type="image/avif">
            <source srcset="/images/img5.webp" type="image/webp">
            <img src="/images/img5.jpg" alt="Descripción de la imagen">
        </picture>
    </div>
    <p>En AMICIS, creemos en el poder del voluntariado para transformar vidas y comunidades. Nos dedicamos a ofrecer
        oportunidades para que personas apasionadas puedan contribuir a causas importantes, aprendiendo y creciendo
        junto a nuestras iniciativas. A través de nuestros proyectos, buscamos generar un impacto positivo y duradero,
        tanto para los beneficiarios como para nuestros voluntarios. Únete a nosotros y sé parte de un movimiento que
        hace la diferencia.</p>
</main>

<section class="seccion contenedor-m">
    <div class="nosotros">
        <h1>Nuestros principios</h1>
        <div class="nosotros__contenido">
            <div class="nosotros__card" <%=AOSAnimation.getAnimation() %>>

                <img src="/images/ayuda.svg" alt="Logo">
                <h2>Solidaridad</h2>
                <p class="nosotros__texto">Estamos aquí para ofrecer ayuda a quienes más lo necesitan.</p>
            </div>

            <div class="nosotros__card" <%=AOSAnimation.getAnimation() %>>
                <img src="/images/amr.svg" alt="amor">

                <h2>Amabilidad</h2>
                <p class="nosotros__texto">Nuestro compromiso es brindar amor y cariño a todos los necesitados.</p>
            </div>

            <div class="nosotros__card" <%=AOSAnimation.getAnimation() %>>
                <img src="/images/planeta.svg" alt="planeta">
                <h2>Empatia</h2>
                <p class="nosotros__texto">Llegamos a todos los rincones para apoyar a quienes más lo requieren.</p>
            </div>
        </div>
    </div>
</section>