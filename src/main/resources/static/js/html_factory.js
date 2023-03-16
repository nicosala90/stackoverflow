export let html_factory = {
    createTableContent: function (dataList, keyList, idKey) {
        console.log(dataList)
        for (const data of dataList) {
          console.log(data)
        }

        const tbody = document.createElement('tbody');
        for (let item of dataList) {
            console.log(item.questionId)
            console.log(item.answerNr)
            let itemRow = document.createElement('tr');
            itemRow.id = item[idKey];
            itemRow.classList.add('item');
            for (let key of keyList) {
                let keyTd = itemRow.insertCell();
                // if(key === 'isAdmin'){keyTd.classList.add('isAdmin');};
                 if(key === 'isRejected'){keyTd.classList.add('isRejected')
                     keyTd.innerHTML = `<i class="icon black fas fa-trash" data-id="${itemRow.id}"></i>`
                 };
                if(item[key] === "TRUE"){keyTd.style.backgroundColor="lightGreen"};
                keyTd.textContent = item[key];
            }
            let deleteTd = itemRow.insertCell();
            deleteTd.classList.add('center');
            deleteTd.innerHTML = `<i class="icon black fas fa-trash" data-id="${item[idKey]}"></i>`
            tbody.insertAdjacentElement('beforeend', itemRow);
        }
        console.log(dataList)
        return tbody;
    }
}
