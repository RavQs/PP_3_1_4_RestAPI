userTable = document.getElementById("userInfo")
let userRow=""

fetch('http://localhost:8080/api/user')
    .then(response => response.json())
    .then(user => {
        let role = "";

        for (let i = 0; i < user.roles.length; i++) {
            role += ((user.roles[i].role === 'USER') ? 'USER' : (user.roles[i].role === 'ADMIN') ? 'ADMIN' : '') + '\n'
        }

        userRow = '<td>' + user.id + '</td>' +
            '<td>' + user.firstName + '</td>' +
            '<td>' + user.lastName + '</td>' +
            '<td>' + user.email + '</td>' +
            '<td>' + user.age + '</td>' +
            '<td>' + role + '</td>'

        userTable.innerHTML = userRow
    })