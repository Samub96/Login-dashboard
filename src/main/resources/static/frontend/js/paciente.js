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

    fetch(`http://localhost:8080/api/pacientes/buscar?documento=${id}`)
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

let pacientesGlobal = [];

function cargarListaPacientes() {
    fetchAuth('http://localhost:8080/api/pacientes')
        .then(response => {
            if (!response.ok) throw new Error('No hay pacientes');
            return response.json();
        })
        .then(data => {
            pacientesGlobal = data || [];
            mostrarPacientesFiltrados(pacientesGlobal);
        })
        .catch(() => {
            pacientesGlobal = [];
            mostrarPacientesFiltrados([]);
        });
}

function mostrarPacientesFiltrados(lista) {
    const tbody = document.getElementById('tabla-pacientes').querySelector('tbody');
    tbody.innerHTML = '';
    if (!lista || lista.length === 0) {
        tbody.innerHTML = `<tr><td colspan="5">Lista vacía</td></tr>`;
        return;
    }
    lista.forEach(paciente => {
        let nombreCompleto = `${paciente.nombre} ${paciente.apellido}`;
        let documento = paciente.documentoIdentidad;
        let ordenes = paciente.ordenes || [];
        if (ordenes.length === 0) {
            // Si el paciente no tiene órdenes, muestra una fila vacía
            tbody.innerHTML += `
                <tr>
                    <td>${nombreCompleto}</td>
                    <td>${documento}</td>
                    <td colspan="3">Sin órdenes</td>
                </tr>
            `;
        } else {
            ordenes.forEach(orden => {
                let numeroOrden = orden.numeroOrden || '';
                let terapias = orden.terapias || [];
                if (terapias.length === 0) {
                    // Si la orden no tiene terapias, muestra una fila vacía
                    tbody.innerHTML += `
                        <tr>
                            <td>${nombreCompleto}</td>
                            <td>${documento}</td>
                            <td>${numeroOrden}</td>
                            <td colspan="2">Sin terapias</td>
                        </tr>
                    `;
                } else {
                    terapias.forEach(terapia => {
                        let nombreTerapia = terapia.nombreTerapia || '';
                        let cantidad = terapia.cantidadDisponible || terapia.cantidad || 0;
                        tbody.innerHTML += `
                            <tr>
                                <td>${nombreCompleto}</td>
                                <td>${documento}</td>
                                <td>${numeroOrden}</td>
                                <td>${nombreTerapia}</td>
                                <td>${cantidad}</td>
                            </tr>
                        `;
                    });
                }
            });
        }
    });
}

function filtrarPacientes() {
    const filtro = document.getElementById('filtro-paciente').value.toLowerCase();
    const filtrados = pacientesGlobal.filter(p =>
        (p.nombre + ' ' + p.apellido).toLowerCase().includes(filtro) ||
        (p.documentoIdentidad + '').includes(filtro)
    );
    mostrarPacientesFiltrados(filtrados);
}
