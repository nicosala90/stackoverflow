import {html_factory} from "./html_factory.js";
import {data_handler} from "./data_handler.js";

function main() {
    loadUsersList();
}

function loadUsersList() {
    data_handler
        .apiGet('api/users/all')
        .then(data => displayUsers(data))
        .then(() => addClickListeners());
}

function displayUsers(usersList) {
    usersList.map(element => element[Object.keys(element)[3]] = Object.values(element)[3].toString().substring(0, 10));
    const usersTable = document.getElementById('result-table');
    const usersTbody = html_factory.createTableContent(usersList, ['userId', 'userName', 'password', 'userEmail', 'registrationDateTime', 'isAdmin', 'isRejected'], 'userId');
    usersTable.insertAdjacentElement('beforeend', usersTbody);
}

function addClickListeners() {
    const userTitleElementList = document.getElementsByClassName('item, fa-trash');
    for (let nameElement of userTitleElementList) {
        nameElement.addEventListener('click', () => {
            const userId = nameElement.id;
            console.log('id clicked ' + userId);
            document.location = `/user${userId}`;
        })
    }

    const deleteButtons = document.getElementsByClassName('fa-trash');
    for (let deleteButton of deleteButtons) {
        deleteButton.addEventListener('click',
            (event) => handleItemDeletion(event));
    }
}

function handleItemDeletion(event) {
    const userId = event.target.dataset['id'];
    event.stopImmediatePropagation();
    data_handler.apiDelete(`api/users/${userId}`)
        .then(response => {
            if (response === 200)
                removeDeletedItem(userId)
        });
}

function removeDeletedItem(userId) {
    const rowToDelete = document.getElementById(userId);
    rowToDelete.outerHTML = '';
}

main();