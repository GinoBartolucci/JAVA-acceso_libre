var productoraCheckbox = document.getElementById('productora');
var cuilInput = document.getElementById('cuil');
var telefonoInput = document.getElementById('telefono');
var nombreInput = document.getElementById('name');
// Agrega un evento de cambio al checkbox
productoraCheckbox.addEventListener('change', function() {
    // Verifica si el checkbox está marcado o desmarcado
    if (productoraCheckbox.checked) {
        // Habilita los campos si el checkbox está marcado
        cuilInput.disabled = false;
        cuilInput.required = true;
        telefonoInput.disabled = false;
        telefonoInput.required = true;
        nombreInput.disabled = false;
        nombreInput.required = true;
    } else {
        // Deshabilita los campos si el checkbox está desmarcado
        cuilInput.disabled = true;
        cuilInput.required = false;
        telefonoInput.required = false;
        telefonoInput.disabled = true;
        nombreInput.disabled = true;
        nombreInput.required = false;
    }
});