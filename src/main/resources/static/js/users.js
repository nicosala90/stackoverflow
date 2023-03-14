import {data_handler} from "./data_handler.js";
import {html_factory} from "./html_factory.js";

function main() {
    loadUsersList();
}

function loadUsersList(sorterColumn) {

    //TODO data sorter
    //let columnSorter = sorterColumn === "" ? "userId" : sorterColumn;
    // .then(data => displayUsers(data.stream().sort(columnSorter)))

    data_handler
        .apiGet('api/users/all')
        .then(data => {
            displayUsers(data)
            addClickListeners(data)
        })
        //.then(() => addClickListeners());
}

function displayUsers(usersList) {
    const pageTitle = document.getElementById("page-title");
    pageTitle.style.color = "white";
    pageTitle.innerHTML = "USERS LIST (sorted by userId)";
    usersList.map(element => element[Object.keys(element)[4]] = Object.values(element)[4].toString().substring(0, 10));
    usersList.map(element => element[Object.keys(element)[5]] = Object.values(element)[5].toString().toUpperCase());
    const usersTable = document.getElementById('result-table');
    const usersTbody = html_factory.createTableContent(usersList, ['userId', 'userName', 'userPassword', 'userEmail', 'registrationDateTime', 'isAdmin', 'isRejected'], 'userId');
    usersTable.insertAdjacentElement('beforeend', usersTbody);
}

function addClickListeners(data) {
    const deleteButtons = document.getElementsByClassName('fa-trash');
    for (let deleteButton of deleteButtons) {
        deleteButton.addEventListener('click',
            (event) => handleItemDeletion(event,data));
    }
}

function handleItemDeletion(event,data) {
    let item = Object.keys(data).length;
    const userId = event.target.dataset['id'];
    event.stopImmediatePropagation();
    data_handler.apiDelete(`api/users/${userId}`)
        .then(response => {
            if (response === 200 && item>1 && data) {
                removeDeletedItem(userId);
                document.location = `/user-list-for-admin`;
            }
        });
}

function removeDeletedItem(userId) {
    const rowToDelete = document.getElementById(userId);
    rowToDelete.outerHTML = '';
}

main();