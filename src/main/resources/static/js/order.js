$(document).ready(function () {
    refresh();

    function refresh() {
        let orderId = location.pathname.substring("/orders/".length);
        $.getJSON('/api/orders/' + orderId, processOrder);
    }

    function processOrder(orderDto) {
        let orderInfo = $(".order-info");

        let orderInfoContent = $(`
            <p>ID: ${orderDto.id}</p>
            <p>Buyer: ${orderDto.user.email}</p>
            <p class="total-price"></p>
            <p>Order status: ${orderDto.status}</p>
            
            <button className="proceed-button">Proceed</button>
            <button className="cancel-button">Cancel</button>
            
            <p>List of books</p>
        `);

        $.getJSON('/api/orders/' + orderDto.id + '/totalprice', setTotalPrice);

        orderInfo.append(orderInfoContent);

        let table = $("tbody");
        table.empty();
        orderDto.orderItems.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(orderItem, table) {
        let subtotal = new BigDecimal(orderItem.price).multiply(orderItem.amount);
        let tableRow = $(`
        <tr>
                <td class="center-align"><a href="/books/${orderItem.book.id}">${orderItem.book.id}</a></td>
                <td>${orderItem.book.name}</td>
                <td>${orderItem.book.author}</td>
                <td>${orderItem.book.isbn}</td>
                <td class="center-align">${orderItem.book.pages}</td>
                <td class="center-align">${orderItem.book.yearPublished}</td>
                <td class="center-align">${orderItem.book.cover}</td>
                <td class="center-align">${orderItem.amount}</td>
                <td> ${orderItem.amount} * ${orderItem.price} = ${subtotal}</td>
                
            </tr>
        `);
        table.append(tableRow);
    }

    function setTotalPrice(totalPrice) {
        let totalPriceTag = $('.total-price');
        totalPriceTag.empty();
        totalPriceTag.append('Total price: ' + totalPrice);
    }
})