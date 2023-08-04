$(document).ready(function () {
    refresh();

    function refresh() {
        $.getJSON('/api/cart', processCart);
        refreshCartSize(setCartSize);
        $.getJSON('/api/cart/totalprice', setTotalPrice);
        $.getJSON('/api/cart/validate', setPurchaseButton);
    }

    function processCart(data) {
        let table = $("tbody");
        table.empty();
        data.orderItems.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(orderItem, table) {
        let subtotal = new BigDecimal(orderItem.book.price).multiply(orderItem.amount);
        let tableRow = $(`
        <tr>
                <td class="center-align"><a href="/books/${orderItem.book.id}">${orderItem.book.id}</a></td>
                <td>${orderItem.book.name}</td>
                <td>${orderItem.book.author}</td>
                <td>${orderItem.book.isbn}</td>
                <td class="center-align">${orderItem.book.pages}</td>
                <td class="center-align">${orderItem.book.yearPublished}</td>
                <td class="center-align">${orderItem.book.cover}</td>

                <td class="center-align">
                    <button class="remove-one-button">-</button>
                        ${orderItem.amount}
                    <button class="add-one-button">+</button>
                </td>

                <td> ${orderItem.amount} * ${orderItem.book.price} = ${subtotal}</td>
                
            </tr>
        `);

        tableRow.find(".add-one-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + orderItem.book.id + '?amount=1',
            type: 'POST',
            success: refresh
        }));
        tableRow.find(".remove-one-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + orderItem.book.id + '?amount=-1',
            type: 'POST',
            success: refresh
        }));
        table.append(tableRow);
    }

    function setCartSize(size) {
        setNavbarCartSizeCounter(size);
        let cart = $(".cart");
        cart.empty();
        if (size > 0) {
            cart.append('Cart(' + size + ')');
        } else {
            cart.append('Cart');
        }
    }

    function setTotalPrice(totalPrice) {
        let totalPriceTag = $('.total-price');
        totalPriceTag.empty();
        totalPriceTag.append('Total price: ' + totalPrice);
    }

    function setPurchaseButton(settings) {
        let purchaseButton = $(".purchase");
        if (settings.purchasePossible === "disabled") {
            purchaseButton.attr("disabled", settings.purchasePossible);
        } else {
            purchaseButton.removeAttr("disabled");
        }
        purchaseButton.attr("title", settings.purchaseHelp);
    }
})