function mostrarVista(vistaId) {
    document.querySelectorAll('.vista').forEach(vista => vista.style.display = 'none');
    document.getElementById('vista-' + vistaId).style.display = 'block';
}
