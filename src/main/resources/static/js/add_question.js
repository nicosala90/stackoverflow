import {data_handler} from "./data_handler.js";

function main() {
    const submitButton = document.getElementById('submit-button');
    submitButton.addEventListener('click', sendNewQuestionData)
}

function sendNewQuestionData() {
    const questionText = document.getElementById('new-question').value;
    submitNewQuestion({'questionText': questionText, 'userId': 1});
}

function submitNewQuestion(data) {
    data_handler
        .apiPost('api/questions/', data)
        .then(result => showResult(result, data.questionText));
}

function showResult(result, name) {
    const submitResult = document.getElementById('submit-result');
    submitResult.classList = [];
    if (result === 200) {
        submitResult.innerText = `Question successfully submitted.`
        submitResult.classList.add('green');
        document.location = '/question-list';
    } else {
        submitResult.innerText = `Error during submission of ${name}`
        submitResult.classList.add('red');
    }
}

main();