const userUrl = document.getElementById('userHeader')

fetch('http://localhost:8080/test/user')
    .then(response => response.json())
    .then(user => {
        let role = "";

        for (let i = 0; i < user.roles.length; i++) {
            role += ((user.roles[i].role === 'USER') ? 'USER' : (user.roles[i].role === 'ADMIN') ? 'ADMIN' : '') + " "
        }

        userUrl.innerHTML = user.username + " with Roles " + role
    })
