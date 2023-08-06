$(document).ready(function () {
    let req = '/api/orders/all';
    refresh(req);

    function refresh(request) {
        if (request != null) {
            req = request;
        }
        $.getJSON(req, processOrders);
    }

    function processOrders(data) {
        let url = '/api/orders/all';
        setPagination(data, url, refresh);

        let table = $("tbody");
        table.empty();
        data.content.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(orderDto, table) {
        let tableRow = $(`
            <tr>
                <td class="center-align"><a href="/orders/${orderDto.id}">${orderDto.id}</a></td>
                <td>${orderDto.user.email}</td>
                <td class="center-align">${orderDto.status}</td>
            </tr>
        `);
        table.append(tableRow);
    }
})