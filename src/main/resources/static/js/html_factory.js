export let html_factory = {
    createTableContent: function (dataList, keyList, idKey) {
        const tbody = document.createElement('tbody');
        for (let item of dataList) {
            let itemRow = document.createElement('tr');
            itemRow.id = item[idKey];
            itemRow.classList.add('item');
            for (let key of keyList) {
                let keyTd = itemRow.insertCell();
                keyTd.textContent = item[key];
            }
            let deleteTd = itemRow.insertCell();
            deleteTd.classList.add('center');
            deleteTd.innerHTML = `<i class="icon black fas fa-trash" data-id="${item[idKey]}"></i>`
            tbody.insertAdjacentElement('beforeend', itemRow);
        }
        return tbody;
    }
}