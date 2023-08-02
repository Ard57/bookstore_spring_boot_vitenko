$(document).ready(function () {
    refresh();

    function refresh() {
        $.getJSON('/api/users/all', processUsers);
    }

    function processUsers(data) {
        let table = $("tbody");
        table.empty();
        data.forEach(function (obj) {
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
            url: '/api/users/'+user.id+'/delete',
            type: 'DELETE',
            success: refresh
        }));
        tableRow.find(".edit-button").on("click", () => window.location.href = '/users/'+user.id+'/edit');


        table.append(tableRow);
    }
})