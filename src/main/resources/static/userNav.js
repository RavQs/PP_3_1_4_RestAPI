const userUrl = document.getElementById('userHeader')

fetch('http://localhost:8080/api/user')
    .then(response => response.json())
    .then(user => {
        let role = "";

        for (let i = 0; i < user.roles.length; i++) {
            role += ((user.roles[i].role === "USER") ? "USER" : (user.roles[i].role === "ADMIN") ? "ADMIN" : '') + " "
        }

        let logout = "<a style='float: right' class=\"btn btn-dark\" href='/logout' methods='GET'>LOGOUT</a>"

        userUrl.innerHTML = user.email + " with Roles " + role + logout
    })
