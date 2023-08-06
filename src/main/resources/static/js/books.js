$(document).ready(function () {
    let req = '/api/books/all'
    refresh(req);

    function refresh(request) {
        if (request != null) {
            req = request;
        }
        $.getJSON(req, processBooks);
        refreshCartSize(setCartSize);
    }

    function processBooks(data) {
        let url = '/api/books/all';
        setPagination(data, url, refresh);

        let table = $("tbody");
        table.empty();
        data.content.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(book, table) {
        let tableRow = $(`
        <tr>
            <td><a href="/books/${book.id}">${book.id}</a></td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td className="center-align">${book.isbn}</td>
            <td className="center-align">${book.pages}</td>
            <td className="center-align">${book.yearPublished}</td>
            <td className="center-align">${book.cover}</td>
            <td className="center-align">${book.price}</td>
            <td className="center-align">
                <button class="add-to-cart-button">Add to Cart</button>
                <button class="edit-button">Edit</button>
                <button class="delete-button">Delete</button>
            </td>
        </tr>
        `);

        tableRow.find(".add-to-cart-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + book.id,
            type: 'POST',
            success: ()=>{refresh(null)}
        }));
        tableRow.find(".delete-button").on("click", () => $.ajax({
            url: '/api/books/' + book.id,
            type: 'DELETE',
            success: ()=>{refresh(null)}
        }));
        tableRow.find(".edit-button").on("click", () => window.location.href = '/books/' + book.id + '/edit');

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
})