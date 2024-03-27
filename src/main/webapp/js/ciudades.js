// Obtener referencia a los elementos de los select
let formulario = document.getElementById("ciudad_form");

// Agregar un evento de envío al formulario
formulario.addEventListener("submit", function(event) {
    // Validar que los campos requeridos estén completos
    let nombre = document.getElementById("nombre").value;

    if (!nombre) {
        event.preventDefault();
        alert("Por favor, complete todos los campos requeridos.");
    }
});