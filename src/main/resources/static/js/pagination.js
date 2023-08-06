function setPagination(page, url, refresh) {
    let pageNumber = page.pageable.pageNumber + 1;
    let size = page.size;

    let paginationDiv = $(".pagination");
    paginationDiv.empty();
    let paginationButtons = $(`
            <div>
                <button class="first-page-button">1</button>
                <button class="prev-page-button"><</button>
                ${pageNumber}
                <button class="next-page-button">></button>
                <button class="last-page-button">${page.totalPages}</button>
                
                <select class="sizes-list">
                </select>
            </div>
        `);

    let sizeSet = new Set([5, 10, 20, 50]);
    sizeSet.add(size);

    let sizeSelector = paginationButtons.find(".sizes-list");

    console.log(sizeSelector);

    sizeSet.forEach(s => {
        if (s == size) {
            sizeSelector.append($(`
                <option value="${s}" selected>${s}</option>
            `));
        } else {
            sizeSelector.append($(`
                <option value="${s}">${s}</option>
            `));
        }
    })

    // for (let s in sizeSet) {
    //     console.log(s);
    // }

    paginationButtons.find(".first-page-button").on("click", () => refresh(
        url+'?page=' + 1 + '&size=' + size));
    paginationButtons.find(".prev-page-button").on("click", () => refresh(
        url+'?page=' + Math.max(pageNumber - 1, 1) + '&size=' + size));
    paginationButtons.find(".next-page-button").on("click", () => refresh(
        url+'?page=' + Math.min(pageNumber + 1, page.totalPages) + '&size=' + size));
    paginationButtons.find(".last-page-button").on("click", () => refresh(
        url+'?page=' + page.totalPages + '&size=' + size));

    sizeSelector.on("change",
        () => refresh(url + '?size=' + paginationButtons.find(".sizes-list").val()));


    paginationDiv.append(paginationButtons);
}