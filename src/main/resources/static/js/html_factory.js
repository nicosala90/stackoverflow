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
            for (let i = 0; i < 2; i++) {

                let adminSetter = itemRow.insertCell();
                adminSetter.classList.add('center');
                adminSetter.innerHTML = '' +
                    '      <form id="userCheck"+i action="/action_user_status_check">\n' +
                    '            <select name="isAdmin" id="isAdmin">\n' +
                    '                <option value=""></option>\n' +
                    '                <option value="false">false</option>\n' +
                    '                <option value="true">true</option>\n' +
                    '            </select>\n' +
                    '        </form>';
            }

            let deleteTd = itemRow.insertCell();
            deleteTd.classList.add('center');
            deleteTd.innerHTML = `<i class="icon black fas fa-trash" data-id="${item[idKey]}"></i>`
            tbody.insertAdjacentElement('beforeend', itemRow);
        }
        return tbody;
    }
}