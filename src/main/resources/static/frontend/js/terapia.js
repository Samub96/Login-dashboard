function registrarTerapia() {
    const numeroOrden = document.getElementById('registro-numero-orden-terapia').value;
    const nombreTerapia = document.getElementById('registro-nombre-terapia').value;
    const cantidad = document.getElementById('registro-cantidad-terapia').value;

    const datos = {
        numeroOrden: numeroOrden,
        nombreTerapia: nombreTerapia,
        cantidadSesiones: Number(cantidad)
    };

    fetchAuth('http://localhost:8080/api/terapias', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datos)
    })
    .then(response => {
        if (response.ok) {
            alert('Terapia registrada correctamente');
        } else {
            response.text().then(msg => alert('Error: ' + msg));
        }
    });
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
