// Obtener referencia a los elementos de los select
let nombre = document.getElementById("nombre");
let apellido = document.getElementById("apellido");
let documento = document.getElementById("documento");
let formulario = document.getElementById("show_form");

formulario.addEventListener("submit", function(event) {
    // Validar que todos los campos requeridos estén completos
    let nombreI = nombre.value;
    let apellidoI = apellido.value;
    let documentoI = documento.value;

    if (!nombreI.trim() || !apellidoI.trim() || !documentoI.trim()) {
        // Al menos uno de los campos requeridos no está completo, evita el envío del formulario
        event.preventDefault();
        alert("Por favor, complete todos los campos requeridos.");
    }
   });
