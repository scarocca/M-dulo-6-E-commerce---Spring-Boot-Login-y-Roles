document.addEventListener('DOMContentLoaded', () => {
    const elementos = document.querySelectorAll('.reveal');

    const mostrarElementos = (entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('active');
                // Una vez que aparece, dejamos de observarlo para ahorrar recursos
                observer.unobserve(entry.target);
            }
        });
    };

    const observer = new IntersectionObserver(mostrarElementos, {
        root: null, // el viewport
        threshold: 0.15 // se activa cuando el 15% de la imagen es visible
    });

    elementos.forEach(el => observer.observe(el));
});