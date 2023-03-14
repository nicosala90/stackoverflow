import {html_factory} from "./html_factory.js";
import {data_handler} from "./data_handler.js";

function main() {
    let questionId = document.location.pathname.split("/question")[1];
    loadQuestionDetail(questionId);
    loadAnswerList(questionId);
    addNewAnswerClickListener(questionId);
}

function loadQuestionDetail(questionId) {
    data_handler
        .apiGet(`api/questions/${questionId}`)
        .then(data => displayQuestion(data))
 //       .then(() => addNewAnswerClickListener())
}

function loadAnswerList(questionId) {
    data_handler
        .apiGet(`api/answers/${questionId}/all`)
        .then(data => displayAnswers(data))
  //      .then(data => displayAnswers(data, questionId))
}

function displayQuestion(question) {
    console.log(question)
    const questionId = document.getElementById('question-id');
    questionId.textContent = question['questionId'];
    const userId = document.getElementById('user-id');
    userId.textContent = question['userId'];
    const questionDate = document.getElementById('question-date');
    questionDate.textContent = question['postingTime'].toString().substring(0, 10);
    const questionText = document.getElementById('question-text');
    questionText.textContent = question['questionText'];
}

function displayAnswers(answers) {

    answers.map(element => element[Object.keys(element)[4]] = Object.values(element)[4].toString().substring(0, 10));
    const answersTable = document.getElementById('result-table');
    if (Object.keys(answers).length > 0) answersTable.classList.remove("hide");
    const answersTbody = html_factory.createTableContent(answers, ['answerId', 'questionId', 'userId', 'answerText', 'postingTime'], 'answerId');
    answersTable.insertAdjacentElement('beforeend', answersTbody);
  //  addNewAnswerClickListener(questionId);
}

function addNewAnswerClickListener(questionId) {
    const newAnswerButton = document.getElementById('new-answer-button');
    newAnswerButton.addEventListener('click', () => {
        document.location = `/new-answer${questionId}`;
    })
    const deleteButtons = document.getElementsByClassName('fa-trash');
    for (let deleteButton of deleteButtons) {
        deleteButton.addEventListener('click', (event) => handleItemDeletion(event));
    }
}

function handleItemDeletion(event) {
    const answerId = event.target.dataset['id'];
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