function registrarPaciente() {
    const paciente = {
        documentoIdentidad: document.getElementById('doc').value,
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        entidadSalud: document.getElementById('entidad').value
    };

    const token = localStorage.getItem('token');

    fetch('http://localhost:8080/api/pacientes', {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        },
        body: JSON.stringify(paciente)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al registrar paciente');
        }
        return response.json();
    })
    .then(data => alert('Paciente registrado'))
    .catch(error => console.error('Error:', error));
}


function buscarPaciente() {
    const id = document.getElementById('buscar-id').value;

    fetch(`http://localhost:8080/api/pacientes/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('resultado').innerText = JSON.stringify(data, null, 2);
        })
        .catch(error => console.error('Error:', error));
}

function editarPaciente() {
    const id = document.getElementById('editar-id').value;

    const pacienteActualizado = {
        documentoIdentidad: document.getElementById('editar-doc').value,
        nombre: document.getElementById('editar-nombre').value,
        apellido: document.getElementById('editar-apellido').value,
        entidadSalud: document.getElementById('editar-entidad').value
    };

    fetch(`http://localhost:8080/api/pacientes/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(pacienteActualizado)
    })
    .then(response => response.json())
    .then(data => alert('Paciente actualizado'))
    .catch(error => console.error('Error:', error));
}

function buscarPorDocumento() {
    const doc = document.getElementById('buscar-doc').value;

    fetch(`http://localhost:8080/api/pacientes/documento/${doc}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('resultado-doc').innerText = JSON.stringify(data, null, 2);
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
