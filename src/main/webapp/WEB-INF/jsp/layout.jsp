<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;700;900&display=swap" rel="stylesheet">
    <link rel="preload" href="${pageContext.request.contextPath}/css/style.css" as="style">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

</head>

<body>
    <header class="header">

        <div class="logo">
            <a href="/">
                <img src="/images/logo.svg" alt="Logo" class="Logo">
            </a>
            <p>Amicis</p>
        </div>

        <nav class="navegacion">
            <ul>
                <li><a href="/nosotros" class="navegacion__enlace">Nosotros</a></li>
                <li><a href="/proyectos" class="navegacion__enlace">Proyectos</a></li>
                <li><a href="/testimonios" class="navegacion__enlace">Testimonios</a></li>
                <li><a href="/contacto" class="navegacion__enlace">Contacto</a></li>
            </ul>
        </nav>

        <div class="callToAction">
            <ul>
                <li><a href="/crear-cuenta" class="navegacion__enlace boton">Unirse</a></li>
            </ul>
        </div>

    </header>



    <main>
        <jsp:include page="${pageContent}" />

    </main>

    <footer>
        <div class="content">
            <div class="top">
                <div class="logo-details">
                    <i class="fab fa-slack"></i>
                    <span class="logo_name">AMICIS</span>
                </div>
                <div class="media-icons">
                    <a href="#"><img src="/images/facebook.svg" alt="Facebook"></a>
                    <a href="#"><img src="/images/instagram.svg" alt="Instagram"></a>
                    <a href="#"><img src="/images/whatsapp.svg" alt="WhatsApp"></a>
                    <a href="#"><img src="/images/twitter.svg" alt="Twitter"></a>
                    <a href="#"><img src="/images/telegram.svg" alt="Telegram"></a>
                </div>

            </div>
            <div class="link-boxes">
                <nav class="navegacion">
                    <ul>
                        <li><a href="/nosotros.html" class="navegacion__enlace">Nosotros</a></li>
                        <li><a href="/proyectos.html" class="navegacion__enlace">Proyectos</a></li>
                        <li><a href="/testimonios.html" class="navegacion__enlace">Testimonios</a></li>
                        <li><a href="/contacto.html" class="navegacion__enlace">Contacto</a></li>
                    </ul>
                </nav>
                <ul class="box input-box">
                    <p class="link_name">Suscribete para notifiaciones</p>
                    <li><input type="text" placeholder="Enter your email"></li>
                    <li><input type="button" value="Subscribe" class="boton--sub"></li>
                </ul>
            </div>
        </div>
        <div class="bottom-details">
            <div class="bottom_text">
                <span class="copyright_text">Copyright © 2021 <a href="#">CodingLab.</a>All rights
                    reserved</span>
                <span class="policy_terms">
                    <a href="#">Privacy policy</a>
                    <a href="#">Terms & condition</a>
                </span>
            </div>
        </div>
    </footer>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>
    AOS.init({
        once:true
    });
    </script>
</body>

</html>