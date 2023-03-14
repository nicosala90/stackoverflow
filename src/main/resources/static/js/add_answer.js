import {data_handler} from "./data_handler.js";

let questionId = document.location.pathname.split("/new-answer")[1];

function main() {
    const submitButton = document.getElementById('submit-button');
    submitButton.addEventListener('click', sendNewAnswerData)
    addNewAnswerClickListener(questionId);
}

function sendNewAnswerData() {
    const answerText = document.getElementById('new-answer').value;
    if (answerText !== "") {
        submitNewAnswer({'answerText': answerText, 'questionId': questionId});
    }
}

function submitNewAnswer(data) {
    data_handler
        .apiPost('api/answers/', data)
        .then(result => showResult(result, data.answerText));
}

function showResult(result, name) {
    const submitResult = document.getElementById('submit-result');
    submitResult.classList = [];
    if (result === 200) {
        submitResult.innerText = `Answer successfully submitted.`
        submitResult.classList.add('green');
        document.location = `/question${questionId}`;
    } else {
        submitResult.innerText = `Error during submission of ${name}`
        submitResult.classList.add('red');
    }
}

function addNewAnswerClickListener(answerId) {
    console.log(answerId)
    const deleteButtons = document.getElementsByClassName('fa-trash');
    for (let deleteButton of deleteButtons) {
        deleteButton.addEventListener('click', (event) => handleItemDeletion(event));
    }
}

function handleItemDeletion(event) {
    const answerId = event.target.dataset['id'];
    console.log(answerId)
    event.stopImmediatePropagation();
    data_handler.apiDelete(`/api/answers/${answerId}`)
        .then(response => {
            if (response === 200) removeDeletedItem(answerId)
        });
}

function removeDeletedItem(answerId) {
    const rowToDelete = document.getElementById(answerId);
    rowToDelete.outerHTML = '';
    /*    if(rowToDelete.childElementCount<1){
            const answersTable = document.getElementById('result-table');
            answersTable.classList.add('hide');
        }*/
}

main();