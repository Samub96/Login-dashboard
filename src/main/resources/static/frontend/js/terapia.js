function registrarTerapia() {
    const terapia = {
        nombreTerapia: document.getElementById('nombre-terapia').value,
        cantidadSesiones: document.getElementById('cantidad-sesiones').value,
        ordenId: document.getElementById('terapia-orden-id').value // Aquí es ordenId, no orden
    };

    fetchAuth('http://localhost:8080/api/terapias', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(terapia)
    })
    .then(response => response.json())
    .then(data => alert('Terapia registrada'))
    .catch(error => console.error('Error:', error));
}


function editarTerapia() {
    const id = document.getElementById('editar-terapia-id').value;

    const sesionesActualizadas = {
        sesionesRealizadas: document.getElementById('editar-sesiones-realizadas').value
    };

    fetchAuth(`http://localhost:8080/api/terapias/sesiones/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(sesionesActualizadas)
    })
    .then(response => response.json())
    .then(data => alert('Terapia actualizada'))
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
