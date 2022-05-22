const API_URL = 'http://localhost:8080/clientes';

window.onload = () => {
    getUsers();
    const btnSubmit = document.querySelector('#submit');
    btnSubmit.addEventListener('click', submitUser);
}

async function getUsers() {

    try {
        let RESPONSE = await fetch(API_URL)
        RESPONSE = await RESPONSE.json();
        showUsers(RESPONSE);
        console.log(RESPONSE);
    } catch (error) {
        console.log(error);
    }

}

function showUsers(data) {
    let usersTable = document.querySelector("#users-cep");
    data.forEach(d => {
        usersTable.appendChild(createLine(d));
    });
    usersTable.classList.add("mt-20");
}

function createLine(data) {
    let tr = document.createElement("tr");
    let tdNome = document.createElement("td");
    let tdCep = document.createElement("td");
    let tdCidade = document.createElement("td");
    tdNome.innerText = data.nome;
    tdCep.innerText = data.endereco.cep;
    tdCidade.innerText = data.endereco.localidade;
    tr.appendChild(tdNome);
    tr.appendChild(tdCidade);
    tr.appendChild(tdCep);
    return tr;
}


async function submitUser() {

    const USER = {
        nome: document.querySelector('#user-name').value,
        endereco: {
            cep: document.querySelector('#user-cep').value
        }
    }

    const POST = {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(USER)
    }

    let response = await fetch(API_URL, POST);
    let data = await response.json();
    showUsers(data);

}