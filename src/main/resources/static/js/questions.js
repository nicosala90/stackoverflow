import {html_factory} from "./html_factory.js";
import {data_handler} from "./data_handler.js";

function main() {
    loadQuestionsList();
}

function loadQuestionsList() {
    data_handler
        .apiGet('api/questions/all')
        .then(data => displayQuestions(data))
        .then(() => addClickListeners());
}

function displayQuestions(questionsList) {
    const questionsTable = document.getElementById('result-table');
    const questionsTbody = html_factory.createTableContent(questionsList, ['posting_time', 'question_text']);
    questionsTable.insertAdjacentElement('beforeend', questionsTbody);
}

function addClickListeners() {
    const questionTitleElementList = document.getElementsByClassName('item');
    for (let nameElement of questionTitleElementList) {
        nameElement.addEventListener('click', () => {
            const questionId = nameElement.id;
            console.log('id clicked ' + questionId);
            document.location = `/question${questionId}`;
        })
    }

    const deleteButtons = document.getElementsByClassName('fa-trash');
    for (let deleteButton of deleteButtons) {
        deleteButton.addEventListener('click',
            (event) => handleItemDeletion(event));
    }
}

function handleItemDeletion(event) {
    const questionId = event.target.dataset['id'];
    event.stopImmediatePropagation();
    data_handler.apiDelete(`api/questions/${questionId}`)
        .then(response => {
            if (response === 200)
                removeDeletedItem(questionId)
        });
}

function removeDeletedItem(questionId) {
    const rowToDelete = document.getElementById(questionId);
    rowToDelete.outerHTML = '';
}

main();