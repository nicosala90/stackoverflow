export let html_factory = {
    createTableContent: function (dataList, keylist) {
        const tbody = document.createElement('tbody');
        for (let item of dataList) {
            let itemRow = document.createElement('tr');
            itemRow.id = item['question_id'];
            itemRow.classList.add('item');
            for (let key of keylist) {
                let keyTd = itemRow.insertCell();
                keyTd.textContent = item[key];
            }
            let deleteTd = itemRow.insertCell();
            deleteTd.classList.add('center');
            deleteTd.innerHTML = `<i class="icon black fas fa-trash" data-id="${item['question_id']}"></i>`
            tbody.insertAdjacentElement('beforeend', itemRow);
        }
        return tbody;
    }
}