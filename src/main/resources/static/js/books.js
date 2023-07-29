$(document).ready(function () {
    $.getJSON('/api/books/all', processBooks)

    function processBooks(data) {
        let table = $("tbody");
        table.empty();
        data.forEach(function (obj) {
            processTableRow(obj, table)
        });
    }

    function processTableRow(book, table) {
        table.append(`
        <tr>
            <td><a href="/book/${book.id}">${book.id}</a></td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td className="center-align">${book.isbn}</td>
            <td className="center-align">${book.pages}</td>
            <td className="center-align">${book.yearPublished}</td>
            <td className="center-align">${book.cover}</td>
            <td className="center-align">${book.price}</td>
            <td className="center-align"><a href="/cart/add/${book.id}">Add to Cart</a></td>
        </tr>
        `);
    }
})