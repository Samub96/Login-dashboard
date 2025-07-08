function registrarOrden() {
    const orden = {
        numeroOrden: document.getElementById('numero-orden').value,
        pacienteId: document.getElementById('orden-paciente-id').value // CAMBIO AQUÍ
    };

    fetchAuth('http://localhost:8080/api/ordenes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(orden)
    })
    .then(response => response.json())
    .then(data => alert('Orden registrada'))
    .catch(error => console.error('Error:', error));
}

function buscarPorOrden() {
    const numero = document.getElementById('buscar-orden').value;

    fetch(`http://localhost:8080/api/ordenes/${numero}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('resultado-orden').innerText = JSON.stringify(data, null, 2);
        })
        .catch(error => console.error('Error:', error));
}
function registrarPaciente() {
    const paciente = {
        documentoIdentidad: document.getElementById('doc').value,
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        entidadSalud: document.getElementById('entidad').value
    };

    fetchAuth('http://localhost:8080/api/pacientes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(paciente)
    })
    .then(response => response.json())
    .then(data => alert('Paciente registrado'))
    .catch(error => console.error('Error:', error));
}
