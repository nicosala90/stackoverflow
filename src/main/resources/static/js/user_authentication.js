import {data_handler} from "./data_handler.js";

function main() {
    const submitButtonUser = document.getElementById('submit-button');
    submitButtonUser.addEventListener('click', loadUsersList)
}

function loadUsersList() {
    data_handler
        .apiGet('api/users/all')
        .then(data => userDataInput(data));
}

function userDataInput(users) {
    const userName = document.getElementById('new-user').value;
    const userPassword = document.getElementById('new-password').value;
    if(/^[a-zA-Z ]+$/.test(userName) && /^[a-zA-Z0-9+/@#\-$%^&*]+$/.test(userPassword)){
        userChecker(users, userName, userPassword);
    }
}

function userChecker(users, userName, userPassword) {
    for (const user of users) {
        if (Object.values(user)[1] == userName && Object.values(user)[4] == userPassword) {
            console.log("Welcome on board " + userName + "!"+Object.values(user)[1]+Object.values(user)[2]+Object.values(user)[4])
            submitRegisteredUser(Object.values(user)[0]);
        }
    }
}

function submitRegisteredUser(userId) {
    data_handler
        .apiPost(`/api/users/${userId}`, userId);
    document.location = `/api/users/${userId}`;
}

main();