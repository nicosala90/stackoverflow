import {html_factory} from "./html_factory.js";
import {data_handler} from "./data_handler.js";

function main() {
    loadQuestionDetail();
}

function loadQuestionDetail() {
    let questionId = document.location.pathname.split("/question")[1];
    data_handler
        .apiGet(`api/questions/${questionId}`)
        .then(data => displayQuestion(data))
}

function displayQuestion(question) {
    const questionId = document.getElementById('question-id');
    questionId.textContent = question['question_id']
    const questionDate = document.getElementById('question-date');
    questionDate.textContent = question['posting_time']
    const questionText = document.getElementById('question-text');
    questionText.textContent = question['question_text']

    const answersTable = document.getElementById('result-table');
    const answersTbody = html_factory.createTableContent(Object.entries(question.answersList), ['posting_time', 'question_text']);
    answersTable.insertAdjacentElement('beforeend', answersTbody);
}

main();