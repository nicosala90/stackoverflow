import {html_factory} from "./html_factory.js";
import {data_handler} from "./data_handler.js";

function main() {
    let questionId = document.location.pathname.split("/question")[1];
    loadQuestionDetail(questionId);
    loadAnswerList(questionId);
}

function loadQuestionDetail(questionId) {
    data_handler
        .apiGet(`api/questions/${questionId}`)
        .then(data => displayQuestion(data))
}

function loadAnswerList(questionId) {
    data_handler
        .apiGet(`api/answers/${questionId}/all`)
        .then(data => displayAnswers(data))
}

function displayQuestion(question) {
    const questionId = document.getElementById('question-id');
    questionId.textContent = question['question_id']
    const questionDate = document.getElementById('question-date');
    questionDate.textContent = question['posting_time']
    const questionText = document.getElementById('question-text');
    questionText.textContent = question['question_text']
}

function displayAnswers(answers) {
    const answersTable = document.getElementById('result-table');
    const answersTbody = html_factory.createTableContent(answers, ['answerId', 'questionId', 'userId', 'answerText', 'postingTime'],'answerId');
    answersTable.insertAdjacentElement('beforeend', answersTbody);
}

main();