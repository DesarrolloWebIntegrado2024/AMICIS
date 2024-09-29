<%@ page import="com.DesWebInt_2024_2.PlatGesCapHum.controllers.AOSAnimation" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <div class="container">
        <div class="slider">
            <div class="slider__slide">
                <div class="slider__contenido">
                    <h1 class="texto-fluido">AMICIS</h1>
                    <p class="slider__texto">Únete a AMICIS y sé parte de un cambio positivo en la vida de quienes
                        más lo necesitan.
                        Descubre cómo puedes contribuir y marcar una diferencia con tu tiempo y habilidades.</p>
                </div>
            </div>
            <div class="slider__slide">
                <div class="slider__contenido">
                    <h1 class="texto-fluido">AMICIS</h1>
                    <p class="slider__texto">Explora diversas áreas de voluntariado con AMICIS y elige el camino que
                        más resuene contigo.
                        Tu compromiso puede transformar vidas y construir comunidades más fuertes.</p>
                </div>
            </div>
            <div class="slider__slide">
                <div class="slider__contenido">
                    <h1 class="texto-fluido">AMICIS</h1>
                    <p class="slider__texto">En AMICIS creemos en el poder de la acción comunitaria. Ven y únete a
                        nuestro equipo de
                        voluntarios para enfrentar desafíos y brindar apoyo donde más se necesita.</p>
                </div>
            </div>
            <div class="slider__slide">
                <div class="slider__contenido">
                    <h1 class="texto-fluido">AMICIS</h1>
                    <p class="slider__texto">Descubre la alegría de ayudar con AMICIS. Ofrecemos oportunidades para
                        que te involucres en
                        proyectos significativos que impactan directamente a quienes enfrentan dificultades.</p>
                </div>
            </div>
            <div class="slider__slide">
                <div class="slider__contenido">
                    <h1 class="texto-fluido">AMICIS</h1>
                    <p class="slider__texto">Tu dedicación puede hacer una gran diferencia. Únete a AMICIS y
                        participa en iniciativas que
                        promuevan el bienestar y el progreso de comunidades necesitadas.</p>
                </div>
            </div>
        </div>
    </div>
</section>

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

<section class="contenedor-m seccion">
    <div class="presentacion">
        <h1>Nuestros proyectos</h1>
    </div>
    <div class="image-grid">
        <div class="image-grid__item image-grid__item--large-vertical" <%=AOSAnimation.getAnimation() %>>
            <img src="/images/img1.webp" alt="Image 1" class="image-grid__img">
        </div>
        <div class="image-grid__item image-grid__item--large-horizontal" <%=AOSAnimation.getAnimation() %>>
            <img src="/images/img3.webp" alt="Image 3" class="image-grid__img">
        </div>
        <div class="image-grid__item image-grid__item--small-horizontal" <%=AOSAnimation.getAnimation() %>>
            <img src="/images/img2.webp" alt="Image 2" class="image-grid__img">
        </div>
        <div class="image-grid__item image-grid__item--medium" <%=AOSAnimation.getAnimation() %>>
            <img src="/images/img4.webp" alt="Image 4" class="image-grid__img">
        </div>
    </div>
</section>

<section class="seccion stats__contenido">
    <h1>Nuestras estadísticas de apoyo</h1>
    <div class="stats__cuantificacion">
        <!-- Aplicando animación correctamente -->
        <div class="stats_info" <%= AOSAnimation.getAnimation() %> >
            <h3 class="stats__categoria">
                Voluntarios Registrados
            </h3>
            <p class="stats__categoria stats__categoria--cantidad">
                1,200
            </p>
        </div>
        <div class="stats_info" <%= AOSAnimation.getAnimation() %> >
            <h3 class="stats__categoria">
                Organizaciones Asociadas
            </h3>
            <p class="stats__categoria stats__categoria--cantidad">
                350
            </p>
        </div>
        <div class="stats_info" <%= AOSAnimation.getAnimation() %> >
            <h3 class="stats__categoria">
                Programas de Voluntariado Creados
            </h3>
            <p class="stats__categoria stats__categoria--cantidad">
                150
            </p>
        </div>
        <div class="stats_info" <%= AOSAnimation.getAnimation() %> >
            <h3 class="stats__categoria">
                Proyectos Completados
            </h3>
            <p class="stats__categoria stats__categoria--cantidad">
                500
            </p>
        </div>
    </div>
</section>

<div class="contenedor-m testimonialesAndblog seccion">
    <section class="testimonialesAndblog__blog">
        <h3>Historias recientes</h3>
        <article class="testimonialesAndblog__entrada">
            <div class="testimonialesAndblog__imagen">
                <img loading="lazy" src="/images/card1.jpg">
            </div>
            <div class="testimonialesAndblog__text">
                <h4>Historia Post viaje</h4>
                <a href="/blog">
                    <p class="texto-fluido__autor">Escrito el: <span>20/10/2024</span> por: <span>Juan Pablo</span>
                    </p>
                    <p class="texto-fluido__blog">Definitivamente una grata experiencia haber colaborado con este
                        gran grupo de trabajo, mucha
                        suerte!</p>
                </a>
            </div>
        </article>
        <article class="testimonialesAndblog__entrada">
            <div class="testimonialesAndblog__imagen">
                <img loading="lazy" src="/images/card2.jpg">
            </div>
            <div class="testimonialesAndblog__text">
                <h4>Trabajo de voluntarios</h4>
                <a href="/blog">
                    <p class="texto-fluido__autor">Escrito el: <span>20/10/2024</span> por: <span>Marco Gutierres</span></p>
                    <p class="texto-fluido__blog">Probablemente el mejor equipo de trabajo voluntario que tuve, una
                        experiencia muy bonita en
                        todo sentido</p>
                </a>
            </div>
        </article>
    </section>

    <section class="testimonialesAndblog__testimoniales"<%=AOSAnimation.getAnimation() %>>
        <h3>Testimoniales</h3>
        <div class="testimonialesAndblog__texto">
            <blockquote>En AMICIS, la experiencia supera expectativas. Desde el inicio, se siente un ambiente cálido
                y acogedor, con una energía que invita a disfrutar. Cada detalle refleja la excelencia y dedicación
                del lugar.</blockquote>
            <p>-Marco Gabriel</p>
        </div>
    </section>
</div>
