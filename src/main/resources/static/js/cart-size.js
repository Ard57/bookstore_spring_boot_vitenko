$(document).ready(function () {
    refreshCartSize(setNavbarCartSizeCounter);
})

function refreshCartSize(func) {
    $.getJSON('/api/cart/size', func);
}

function setNavbarCartSizeCounter(size) {
    let counter = $(".cart-size-counter");
    counter.empty();
    if (size > 0) {
        counter.append('Cart(' + size + ')');
    } else {
        counter.append('Cart');
    }
}