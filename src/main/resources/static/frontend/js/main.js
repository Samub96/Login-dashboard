// Este archivo puede quedar vacío o lo puedes usar para inicializar vistas automáticamente si lo deseas
window.onload = () => {
    console.log('Sistema cargado correctamente.');
}
// Agrega automáticamente el token a los headers
function fetchAuth(url, options = {}) {
    const token = localStorage.getItem('token');
    if (!options.headers) options.headers = {};
    options.headers['Authorization'] = `Bearer ${token}`;
    return fetch(url, options);
}

// Proteger el dashboard al cargar
window.onload = () => {
    const token = localStorage.getItem('token');
    const rol = localStorage.getItem('rol');

    if (!token) {
        alert("Acceso denegado. Inicia sesión primero.");
        window.location.href = 'login.html';
        return;
    }

    if (location.pathname.includes('dashboard') && rol === 'DOCTOR') {
        alert("Acceso denegado para DOCTOR.");
        window.location.href = 'consulta.html';
        return;
    }

    if (location.pathname.includes('consulta') && rol !== 'DOCTOR') {
        window.location.href = 'dashboard.html';
        return;
    }

    mostrarOpcionesPorRol(rol);
};


function mostrarOpcionesPorRol(rol) {
    if (rol === 'DOCTOR') {
        ocultarElementos([
            'btn-registrar-paciente',
            'btn-registrar-orden',
            'btn-registrar-terapia',
            'btn-editar-paciente',
            'btn-editar-terapia'
        ]);
    } else if (rol === 'SECRETARIA') {
        ocultarElementos([
            'btn-editar-terapia'
        ]);
    }
}

function ocultarElementos(listaIds) {
    listaIds.forEach(id => {
        const elemento = document.getElementById(id);
        if (elemento) elemento.style.display = 'none';
    });
}

function validarAccesoVista(vista) {
    const rol = localStorage.getItem('rol');

    const permisos = {
        'registro': ['ADMIN', 'SECRETARIA'],
        'orden': ['ADMIN', 'SECRETARIA'],
        'terapia': ['ADMIN', 'SECRETARIA'],
        'editar-paciente': ['ADMIN', 'SECRETARIA'],
        'editar-terapia': ['ADMIN'],
        'buscar': ['ADMIN', 'SECRETARIA', 'DOCTOR']
    };

    if (!permisos[vista].includes(rol)) {
        alert('No tienes permisos para acceder a esta sección.');
        return false;
    }

    return true;
}

function mostrarVista(vistaId) {
    if (!validarAccesoVista(vistaId)) return;

    document.querySelectorAll('.vista').forEach(v => v.style.display = 'none');
    document.getElementById('vista-' + vistaId).style.display = 'block';
}


function cerrarSesion() {
    localStorage.removeItem('token');
    localStorage.removeItem('rol');
    window.location.href = 'login.html';
}

function mostrarUsuario() {
    const username = localStorage.getItem('username');
    const rol = localStorage.getItem('rol');

    const infoDiv = document.getElementById('usuario-info');
    infoDiv.innerHTML = `<strong>Usuario:</strong> ${username} <br> <strong>Rol:</strong> ${rol}`;
}

// Llama la función cuando cargue el dashboard
window.onload = mostrarUsuario;
