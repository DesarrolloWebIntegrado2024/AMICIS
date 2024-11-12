document.addEventListener('DOMContentLoaded', inciarSlider);

function inciarSlider() {
    const slider = document.querySelector('.slider');
    const slides = document.querySelectorAll('.slider__slide');
    const totalSlides = slides.length;
    const slideInterval = 5000;
    let currentIndex = 0;

    function cominezaSlider(index) {
        slider.style.transform = `translateX(-${index * 20}%)`;
    }

    function siguienteSlider() {
        currentIndex = (currentIndex + 1) % totalSlides;
        cominezaSlider(currentIndex);
    }

    function startSlider() {
        cominezaSlider(currentIndex);
        setInterval(siguienteSlider, slideInterval);
    }
    startSlider();
}
