$(document).ready(function () {
    refresh();

    function refresh() {
        $.getJSON('/api/orders/all', processOrders);
    }

    function processOrders(orders) {
        let table = $("tbody");
        table.empty();
        orders.forEach(function (obj) {
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