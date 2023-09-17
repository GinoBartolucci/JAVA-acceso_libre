// Obtener referencia a los elementos de los select
let provinciaSelect = document.getElementById("provincia_id");
let ciudadSelect = document.getElementById("ciudad_id");
let formulario = document.getElementById("lugar_form");

// Agregar un evento de envío al formulario
formulario.addEventListener("submit", function(event) {
    // Validar que los campos requeridos estén completos
    ciudadSelect.disabled = false;
    let nombre = document.getElementById("nombre").value;
    let direccion = document.getElementById("direccion").value;
    let capacidad = document.getElementById("capacidad").value;
    
    let provincia = document.getElementById("provincia_id").value;
	let ciudad = document.getElementById("ciudad_id").value;
	

    if (!nombre || !direccion || !capacidad || !provincia.trim() || !ciudad.trim()) {
        // Al menos uno de los campos requeridos no está completo, evita el envío del formulario
        event.preventDefault();
        alert("Por favor, complete todos los campos requeridos.");
    }
    
});

// Obtener las opciones originales del select de ciudad
let opcionesCiudad = ciudadSelect.innerHTML;

// Agregar un evento de cambio al select de provincia
provinciaSelect.addEventListener("change", function() {
    // Habilitar o deshabilitar el select de ciudad según la selección de provincia
    ciudadSelect.disabled = provinciaSelect.value === "";

    // Si la provincia se deselecciona, también reiniciamos el select de ciudad
    if (provinciaSelect.value === "") {
        ciudadSelect.innerHTML = opcionesCiudad; // Restaurar las opciones originales
    } else {
        // Filtrar las opciones de ciudad basadas en la provincia seleccionada
        let provinciaId = provinciaSelect.value;
        ciudadSelect.innerHTML = opcionesCiudad; // Restaurar las opciones originales

        // Recorrer las opciones y eliminar las que no coincidan con la provincia seleccionada
        for (let i = ciudadSelect.options.length - 1; i >= 0; i--) {
            if (ciudadSelect.options[i].getAttribute("provincia_id") !== provinciaId) {
                ciudadSelect.remove(i);
            }
        }
    }
});