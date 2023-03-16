import {html_factory} from "./html_factory.js";
import {data_handler} from "./data_handler.js";

function main() {
    loadQuestionsList();
}

function loadQuestionsList() {
    data_handler
        .apiGet('api/questions/all')
        .then(data => displayQuestions(data))
        .then(() => addClickListeners())
}

async function displayQuestions(questionsList) {
    for (const question of questionsList) {
        await data_handler
            .apiGet(`api/answers/${question.questionId}/answerCount`)
            .then(data => question['answersNr']=data)
    }
    questionsList.map(element => element[Object.keys(element)[3]] = Object.values(element)[3].toString().substring(0, 10));
    const questionsTable = document.getElementById('result-table');
    const questionsTbody = html_factory.createTableContent(questionsList, ['postingTime', 'questionText','answersNr'], 'questionId');
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
    for (const deleteButton of deleteButtons) {
        deleteButton.addEventListener('click',
            (event) => handleItemDeletion(event));
    }
}

function handleItemDeletion(event) {
    console.log(event.target.id)
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