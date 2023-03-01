export let data_handler = {
    apiGet: async function (url) {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    },
    apiPost: async function (url, body) {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        });
        return response.status;
    },
    apiDelete: async function (url) {
        const response = await fetch(url, {
            method: 'DELETE'
        });
        const data = response.status
        return data;
    },
}