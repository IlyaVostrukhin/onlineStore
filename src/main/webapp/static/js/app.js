;$(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });

    var init = function () {
        initBuyBtn();
        $('#addToCart').click(addProductToCart);
        $('#addProductPopup .count').change(calculateCost);
        $('#loadMore').click(loadMoreProducts);
        $('#loadMoreProductsFromManager').click(loadMoreProductsFromManager);
        $('#loadMoreMyOrders').click(loadMoreMyOrders);
        $('#loadMoreUsers').click(loadMoreUsers);
        initSearchForm();
        $('#goSearch').click(goSearch);
        $('.remove-product').click(removeProductFromCart);
        $('.post-request').click(function () {
            postRequest($(this).attr('data-url'));
        });
    };

    var showAddProductPopup = function () {
        var idProduct = $(this).attr('data-id-product');
        var product = $('#product' + idProduct);
        $('#addProductPopup').attr('data-id-product', idProduct);
        $('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
        $('#addProductPopup .name').text(product.find('.name').text());
        var price = product.find('.price').text();
        $('#addProductPopup .price').text(price);
        $('#addProductPopup .amount').text(product.find('.amount').text());
        $('#addProductPopup .category').text(product.find('.category').text());
        $('#addProductPopup .producer').text(product.find('.producer').text());
        $('#addProductPopup .description').text(product.find('.description').text());
        $('#addProductPopup .count').val(1);
        $('#addProductPopup .cost').text(price);
        $('#addToCart').removeClass('hidden');
        $('#addToCartIndicator').addClass('hidden');
        $('#addProductPopup').modal({
            show: true
        });
    };
    var initBuyBtn = function () {
        $('.buy-btn').click(showAddProductPopup);
    };
    var postRequest = function (url) {
        var form = '<form id="postRequestForm" action="' + url + '" method="post"></form>';
        $('body').append(form);
        $('#postRequestForm').submit();
    };
    var addProductToCart = function () {
        var idProduct = $('#addProductPopup').attr('data-id-product');
        var count = $('#addProductPopup .count').val();
        var btn = $('#addToCart');
        convertButtonToLoader(btn, 'btn-primary');
        $.ajax({
            url: '/ajax/json/product/add',
            method: 'POST',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                $('#currentShoppingCart .total-count').text(data.totalCount);
                $('#currentShoppingCart .total-cost').text(data.totalCost);
                $('#currentShoppingCart').removeClass('hidden');
                $('#currentShoppingCartSmall').removeClass('hidden');
                convertLoaderToButton(btn, 'btn-primary', addProductToCart);
                $('#addProductPopup').modal('hide');
            },
            error: function (xhr) {
                convertLoaderToButton(btn, 'btn-primary', addProductToCart);
                if (xhr.status === 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        });
    };
    var calculateCost = function () {
        var priceStr = $('#addProductPopup .price').text();
        var price = parseFloat(priceStr.replace('₽', ' ')).toFixed(2);
        var count = parseInt($('#addProductPopup .count').val());
        var min = parseInt($('#addProductPopup .count').attr('min'));
        var max = parseInt($('#addProductPopup').find('.amount').text().slice(0, -4));
        if (count >= min && count <= max) {
            var cost = (price * count).toFixed(2);
            $('#addProductPopup .cost').text('₽ ' + cost);
        } else {
            $('#addProductPopup .count').val(max);
            $('#addProductPopup .cost').text('₽ ' + (price * max).toFixed(2));
        }
    };

    var convertButtonToLoader = function (btn, btnClass) {
        btn.removeClass(btnClass);
        btn.removeClass('btn');
        btn.addClass('load-indicator');
        var text = btn.text();
        btn.text('');
        btn.attr('data-btn-text', text);
        btn.off('click');
    };
    var convertLoaderToButton = function (btn, btnClass, actionClick) {
        btn.removeClass('load-indicator');
        btn.addClass('btn');
        btn.addClass(btnClass);
        btn.text(btn.attr('data-btn-text'));
        btn.removeAttr('data-btn-text');
        btn.click(actionClick);
    };

    var initSearchForm = function () {
        $('#allCategories').click(function () {
            $('.categories .search-option').prop('checked', $(this).is(':checked'));
        });
        $('.categories .search-option').click(function () {
            $('#allCategories').prop('checked', false);
        });
        $('#allProducers').click(function () {
            $('.producers .search-option').prop('checked', $(this).is(':checked'));
        });
        $('.producers .search-option').click(function () {
            $('#allProducers').prop('checked', false);
        });
    };
    var loadMoreProducts = function () {
        var btn = $('#loadMore');
        convertButtonToLoader(btn, 'btn-success');
        var pageNumber = parseInt($('#productList').attr('data-page-number'));
        var url = '/ajax/html/more' + location.pathname + '?page=' + (pageNumber + 1) + '&' + location.search.substring(1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#productList .row').append(html);
                pageNumber++;
                var pageCount = parseInt($('#productList').attr('data-page-count'));
                $('#productList').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-success', loadMoreProducts);
                } else {
                    btn.remove();
                }
                initBuyBtn();
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-success', loadMoreProducts);
                alert('Error');
            }
        });
    };

    var loadMoreProductsFromManager = function () {
        var btn = $('#loadMoreProductsFromManager');
        convertButtonToLoader(btn, 'btn-success');
        var pageNumber = parseInt($('#products').attr('data-page-number'));
        var url = '/manager/ajax/html/more' + location.pathname.substring(8, location.pathname.length) + '?page=' + pageNumber + '&' + location.search.substring(1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#products tbody').append(html);
                pageNumber++;
                var pageCount = parseInt($('#products').attr('data-page-count'));
                $('#products').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-success', loadMoreProductsFromManager);
                } else {
                    btn.remove();
                }
            },
            error: function (xhr) {
                convertLoaderToButton(btn, 'btn-success', loadMoreProductsFromManager);
                if (xhr.status == 401) {
                    window.location.href = '/sign-in';
                } else {
                    alert('Error');
                }
            }
        });
    };

    var goSearch = function () {
        var isAllSelected = function (selector) {
            var unchecked = 0;
            $(selector).each(function (index, value) {
                if (!$(value).is(':checked')) {
                    unchecked++;
                }
            });
            return unchecked === 0;
        };
        if (isAllSelected('.categories .search-option')) {
            $('.categories .search-option').prop('checked', false);
        }
        if (isAllSelected('.producers .search-option')) {
            $('.producers .search-option').prop('checked', false);
        }
        $('form.search').submit();
    };
    var confirm = function (msg, okFunction) {
        if (window.confirm(msg)) {
            okFunction();
        }
    };
    var removeProductFromCart = function () {
        var btn = $(this);
        confirm('Вы уверены?', function () {
            executeRemoveProduct(btn);
        });
    };
    var refreshTotalCost = function () {
        var total = 0;
        $('#shoppingCart .item').each(function (index, value) {
            var count = parseInt($(value).find('.count').text());
            var price = parseFloat($(value).find('.price').text().replace('₽', ' '));
            var val = price * count;
            total = total + val;
        });
        $('#shoppingCart .total').text('₽ ' + total);
    };
    var executeRemoveProduct = function (btn) {
        var idProduct = btn.attr('data-id-product');
        var count = btn.attr('data-count');
        convertButtonToLoader(btn, 'btn-danger');

        $.ajax({
            url: '/ajax/json/product/remove?',
            method: 'POST',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                $('#currentShoppingCart .total-count').text(data.totalCount);
                $('#currentShoppingCart .total-cost').text(data.totalCost);
                if (data.totalCount == 0) {
                    window.location.href = '/products';
                } else {
                    var prevCount = parseInt($('#product' + idProduct + ' .count').text());
                    var remCount = parseInt(count);
                    if (remCount >= prevCount) {
                        $('#product' + idProduct).remove();
                        $('#product' + idProduct).remove();
                    } else {
                        convertLoaderToButton(btn, 'btn-danger', removeProductFromCart);
                        $('#product' + idProduct + ' .count').text(prevCount - remCount);
                        if (prevCount - remCount == 1) {
                            $('#product' + idProduct + ' a.remove-all').remove();
                        }
                    }
                    refreshTotalCost();
                }
            },
            error: function (data) {
                convertLoaderToButton(btn, 'btn-danger', removeProductFromCart);
                alert('Error');
            }
        });
    };

    var loadMoreMyOrders = function () {
        var btn = $('#loadMoreMyOrders');
        convertButtonToLoader(btn, 'btn-success');
        var pageNumber = parseInt($('#myOrders').attr('data-page-number'));
        var url = '/ajax/html/more/orders?page=' + pageNumber;
        $.ajax({
            url: url,
            success: function (html) {
                $('#myOrders tbody').append(html);
                pageNumber++;
                var pageCount = parseInt($('#myOrders').attr('data-page-count'));
                $('#myOrders').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-success', loadMoreMyOrders);
                } else {
                    btn.remove();
                }
            },
            error: function (xhr) {
                convertLoaderToButton(btn, 'btn-success', loadMoreMyOrders);
                if (xhr.status == 401) {
                    window.location.href = '/sign-in';
                } else {
                    alert('Error');
                }
            }
        });
    };

    var loadMoreUsers = function () {
        var btn = $('#loadMoreUsers');
        convertButtonToLoader(btn, 'btn-success');
        var pageNumber = parseInt($('#users').attr('data-page-number'));
        var url = '/admin/ajax/html/more' + location.pathname.substring(6, location.pathname.length) + '?page=' + pageNumber + '&' + location.search.substring(1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#users tbody').append(html);
                pageNumber++;
                var pageCount = parseInt($('#users').attr('data-page-count'));
                $('#users').attr('data-page-number', pageNumber);
                if (pageNumber < pageCount) {
                    convertLoaderToButton(btn, 'btn-success', loadMoreUsers);
                } else {
                    btn.remove();
                }
            },
            error: function (xhr) {
                convertLoaderToButton(btn, 'btn-success', loadMoreUsers);
                if (xhr.status == 401) {
                    window.location.href = '/sign-in';
                } else {
                    alert('Error');
                }
            }
        });
    };

    init();
});

$(document).ready(function () {
    $("#phone").mask("+7-999-999-99-99");
});

var store = {
    postReq: function (url, csrfToken) {
        var form = document.createElement("form");
        form.setAttribute("method", 'post');
        form.setAttribute("action", url);

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "_csrf");
        hiddenField.setAttribute("value", csrfToken);
        form.appendChild(hiddenField);

        document.body.appendChild(form);
        form.submit();
    },

    post: function (path, params) {
        var form = document.createElement("form");
        form.setAttribute("method", 'post');
        form.setAttribute("action", path);
        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                var value = params[key];
                if (value != undefined) {
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", key);
                    hiddenField.setAttribute("value", params[key]);
                    form.appendChild(hiddenField);
                }
            }
        }
        document.body.appendChild(form);
        form.submit();
    },

    logout: function (csrfToken) {
        store.post('/sign-out', {
            _csrf: csrfToken.toString().replace('-srf32a', '').replace('83anbx76-', '')
        });
    }
}