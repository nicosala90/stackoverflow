import {data_handler} from "./data_handler.js";

function main() {
    const pageTitle = document.getElementById("page-title");
    pageTitle.style.color = "white";
    pageTitle.innerHTML = "USER LOGIN";
    const emailUnHide = document.getElementById("hide");
    emailUnHide.classList.remove("hide");
    const userMessage = document.getElementById('message');
    userMessage.classList.add("hide");
    const submitButtonUser = document.getElementById('submit-button');
    submitButtonUser.addEventListener('click', loadUsersList)
}

function loadUsersList() {
    data_handler
        .apiGet('api/users/all')
        .then(data => userDataInput(data));
}

function userDataInput(users) {
    const newUserName = document.getElementById('new-user').value;
    const newUserPassword = document.getElementById('new-password').value;
    userChecker(users, newUserName, newUserPassword);
}

function userChecker(users, userName, userPassword) {
    const userMessage = document.getElementById('message');
    let mistake = 0;
    for (const user of users) {
        if (Object.values(user)[1] === (newUserName) && Object.values(user)[3] === newUserEmail) {
            mistake = 1;
        }
    }
    if (mistake === 0) {
        if (/^[a-zA-Z ]+$/.test(newUserName) &&
            /^[a-zA-Z0-9+/@#\-$%^&*]+$/.test(newUserPassword) &&
            /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(newUserEmail)) {
            submitNewUser({'userName': newUserName, 'userPassword': newUserPassword, 'userEmail': newUserEmail});
            document.location = '/user-list-for-admin';
        } else {
            userMessage.classList.remove("hide");
            userMessage.innerHTML = "Wrong data, please correct name, password and email if it is needed!";
            onclick(() => userMessage.innerHTML ="");

        }
    } else {
        userMessage.classList.remove("hide");
        userMessage.innerHTML = "Already exists user the same name and email address!";
        onclick();
    }
}

function newUserDataInput(users) {
    const newUserName = document.getElementById('new-user').value;
    const newUserPassword = document.getElementById('new-password').value;
    const newUserEmail = document.getElementById('new-email').value;
    userChecker(users, newUserName, newUserPassword, newUserEmail);
}

function submitNewUser(data) {
    data_handler
        .apiPost('api/users/', data);
    document.location = '/';
}

main();