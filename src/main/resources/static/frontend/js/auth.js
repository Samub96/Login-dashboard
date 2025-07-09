function login() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en login');
        }
        return response.json();
    })
    .then(data => {
        localStorage.setItem('token', data.token);
        localStorage.setItem('username', data.username);
        localStorage.setItem('rol', data.rol);

        // Redirigir al dashboard
        window.location.href = 'dashboard.html';
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Login fallido');
    });
}
