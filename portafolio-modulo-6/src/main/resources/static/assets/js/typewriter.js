document.addEventListener('DOMContentLoaded', () => {
    const elemento = document.getElementById('maquina-texto');
    const frases = [
        "Diseñamos la cita perfecta frente al mar.",
		"CitaIdeal.cl",
        "Creamos experiencias mágicas para dos.",
		"CitaIdeal.cl",
        "Tu historia de amor merece un escenario único.",
		"CitaIdeal.cl"
    ];
    
    let fraseIndice = 0;
    let caracterIndice = 0;
    let estaBorrando = false;
    let velocidadEscribir = 100;

    function animarMaquina() {
        const fraseActual = frases[fraseIndice];
        
        if (estaBorrando) {
            elemento.textContent = fraseActual.substring(0, caracterIndice - 1);
            caracterIndice--;
            velocidadEscribir = 50; // Borra más rápido
        } else {
            elemento.textContent = fraseActual.substring(0, caracterIndice + 1);
            caracterIndice++;
            velocidadEscribir = 100; // Escribe a ritmo normal
        }

        // Lógica de pausas
        if (!estaBorrando && caracterIndice === fraseActual.length) {
            velocidadEscribir = 3000; // Pausa al final de la frase
            estaBorrando = true;
        } else if (estaBorrando && caracterIndice === 0) {
            estaBorrando = false;
            fraseIndice = (fraseIndice + 1) % frases.length;
            velocidadEscribir = 500;
        }

        setTimeout(animarMaquina, velocidadEscribir);
    }

    animarMaquina();
});

// Este código hace que la página se desvanezca antes de ir a la otra
document.querySelector('.btn-romance').addEventListener('click', function(e) {
    e.preventDefault(); // Detiene el salto inmediato
    const destino = this.href;
    
    document.body.style.opacity = '0';
    document.body.style.transition = 'opacity 0.5s ease';
    
    setTimeout(() => {
        window.location.href = destino;
    }, 500);
});