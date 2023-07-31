$(document).ready(function () {
    refresh();

    function refresh() {
        $.getJSON('/api/cart', processCart);
        $.getJSON('/api/cart/size', setCartSize);
        $.getJSON('/api/cart/totalprice', setTotalPrice);
        $.getJSON('/api/cart/validate', setPurchaseButton);
    }

    function processCart(data) {
        let table = $("tbody");
        table.empty();
        data.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(orderItem, table) {
        let subtotal = orderItem.amount * orderItem.bookPrice;
        let tableRow = $(`
        <tr>
                <td class="center-align"><a href="/book/${orderItem.bookId}">${orderItem.bookId}</a></td>
                <td>${orderItem.bookName}</td>
                <td>${orderItem.bookAuthor}</td>
                <td>${orderItem.bookIsbn}</td>
                <td class="center-align">${orderItem.bookPages}</td>
                <td class="center-align">${orderItem.bookYearPublished}</td>
                <td class="center-align">${orderItem.bookCover}</td>

                <td class="center-align">
                    <button class="remove-one-button">-</button>
                        ${orderItem.amount}
                    <button class="add-one-button">+</button>
                </td>

                <td> ${orderItem.amount} * ${orderItem.bookPrice} = ${orderItem.subTotal}</td>
                
            </tr>
        `);

        tableRow.find(".add-one-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + orderItem.bookId + '?amount=1',
            type: 'POST',
            success: refresh
        }));
        tableRow.find(".remove-one-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + orderItem.bookId + '?amount=-1',
            type: 'POST',
            success: refresh
        }));
        table.append(tableRow);
    }

    function setCartSize(size) {
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
        console.log(totalPrice);
        totalPriceTag.empty();
        totalPriceTag.append('Total price: ' + totalPrice);
    }

    function setPurchaseButton(settings) {
        let purchaseButton = $(".purchase");
        console.log(purchaseButton);
        if (settings.purchasePossible === "disabled") {
            purchaseButton.attr("disabled", settings.purchasePossible);
        } else {
            purchaseButton.removeAttr("disabled");
        }
        purchaseButton.attr("title", settings.purchaseHelp);
    }
})