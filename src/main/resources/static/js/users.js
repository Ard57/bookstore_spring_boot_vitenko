$(document).ready(function () {
    let req = '/api/users/all';
    refresh(req);

    function refresh(request) {
        if (request != null) {
            req = request;
        }
        $.getJSON(req, processUsers);
    }

    function processUsers(data) {
        let url = '/api/users/all';
        setPagination(data, url, refresh);

        let table = $("tbody");
        table.empty();
        data.content.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(user, table) {
        let tableRow = $(`
            <tr>
                <td class="center-align"><a href="/users/${user.id}">${user.id}</a></td>
                <td>${user.email}</td>
                <td>${user.lastName}</td>
                <td>${user.firstName}</td>
                <td>${user.password}</td>
                <td class="center-align">${user.role}</td>
                <td class="center-align">
                    <button class="edit-button">Edit</button>
                    <button class="delete-button">Delete</button>
                </td>
            </tr>
        `);

        tableRow.find(".delete-button").on("click", () => $.ajax({
            url: '/api/users/' + user.id,
            type: 'DELETE',
            success: () => {
                refresh(null);
            }
        }));
        tableRow.find(".edit-button").on("click", () => window.location.href = '/users/' + user.id + '/edit');


        table.append(tableRow);
    }
})