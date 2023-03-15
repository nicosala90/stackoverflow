import {data_handler} from "./data_handler.js";

let questionId = document.location.pathname.split("/new-answer")[1];

function main() {
    const submitButton = document.getElementById('submit-button');
    submitButton.addEventListener('click', sendNewAnswerData)
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

main();