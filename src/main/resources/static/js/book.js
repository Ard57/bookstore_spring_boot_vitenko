$(document).ready(function () {
    refresh();

    function refresh() {
        let bookId = location.pathname.substring("/book/".length);
        $.getJSON('/api/books/' + bookId, processBook);
    }

    function processBook(book) {
        renderBookFields(book);
        renderActions(book);
    }

    function renderBookFields(book) {
        let fieldsDiv = $(".book-fields");
        fieldsDiv.empty();

        let fieldsDivContent = $(`
            <p>ID: ${book.id}</p>
            <p>Name: ${book.name}</p>
            <p>Author: ${book.author}</p>
            <p>ISBN: ${book.isbn}</p>
            <p>Pages: ${book.pages}</p>
            <p>Year published: ${book.yearPublished}</p>
            <p>Cover: ${book.cover}</p>
            <p>Price: ${book.price}</p>
        `);

        fieldsDiv.append(fieldsDivContent);
    }

    function renderActions(book) {
        let actionsDiv = $(".actions");
        actionsDiv.empty();

        let actionsDivContent = $(`
            <div>
                <button class="add-to-cart-button">Add to Cart</button>
                <button class="edit-button">Edit</button>
                <button class="delete-button">Delete</button>
            </div>        
        `);

        actionsDivContent.find(".add-to-cart-button").on("click", () => $.ajax({
            url: '/api/cart/add/' + book.id,
            type: 'POST',
            success: refresh
        }));
        actionsDivContent.find(".delete-button").on("click", () => $.ajax({
            url: '/api/books/' + book.id,
            type: 'DELETE',
            success: () => window.location.href = '/book/all'
        }));
        actionsDivContent.find(".edit-button").on("click", () => window.location.href = '/book/' + book.id + '/edit');

        actionsDiv.append(actionsDivContent);
    }
})