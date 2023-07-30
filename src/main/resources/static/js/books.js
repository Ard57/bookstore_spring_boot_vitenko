$(document).ready(function () {
    refresh();

    function refresh() {
        $.getJSON('/api/books/all', processBooks);
        $.getJSON('/api/cart/size', setCartSize);
    }

    function processBooks(data) {
        let table = $("tbody");
        table.empty();
        data.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(book, table) {
        let tableRow = $(`
        <tr>
            <td><a href="/book/${book.id}">${book.id}</a></td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td className="center-align">${book.isbn}</td>
            <td className="center-align">${book.pages}</td>
            <td className="center-align">${book.yearPublished}</td>
            <td className="center-align">${book.cover}</td>
            <td className="center-align">${book.price}</td>
            <td className="center-align">
                <button class="add-to-cart-button">Add to Cart</button>
            </td>
        </tr>
        `);

        tableRow.find(".add-to-cart-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + book.id,
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
})