import {data_handler} from "./data_handler.js";

function main() {
    const submitButtonUser = document.getElementById('submit-button');
    submitButtonUser.addEventListener('click', loadUsersList)
}

function loadUsersList() {
    data_handler
        .apiGet('api/users/all')
        .then(data => newUserDataInput(data));
}

function newUserDataInput(users) {
    const newUserName = document.getElementById('new-user').value;
    const newUserPassword = document.getElementById('new-password').value;
    const newUserEmail = document.getElementById('new-email').value;
    userChecker(users, newUserName, newUserPassword, newUserEmail);
}

function userChecker(users, newUserName, newUserPassword, newUserEmail) {
    for (const user of users) {
    console.log(Object.values(user)[1]);
        if (Object.values(user)[1] === (newUserName) && Object.values(user)[2] === newUserEmail) {
            console.log("Already exist user the same name and email address!!!")
            document.location = '/new-user';
        }
    }
    submitNewUser({'userName': newUserName, 'password': newUserPassword, 'userEmail': newUserEmail});
}

function submitNewUser(data) {
    data_handler
        .apiPost('api/users/', data);
    document.location = '/';
}

main();