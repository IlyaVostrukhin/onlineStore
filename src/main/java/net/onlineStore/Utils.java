package net.onlineStore;

import net.onlineStore.entities.Product;
import net.onlineStore.form.ProductForm;
import net.onlineStore.model.ShoppingCart;
import net.onlineStore.model.ShoppingCartItem;
import net.onlineStore.services.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class Utils {
    public static void setCurrentShoppingCart(HttpServletRequest request, ProductService productService) {
        Cookie cookie = findCookie(request);
        if (cookie != null) {
            ShoppingCart shoppingCart = deserializeShoppingCart(cookie.getValue(), productService);
            if (shoppingCart != null) {
                request.getSession().setAttribute("currentShoppingCart", shoppingCart);
            }
        }
    }

    public static void updateShoppingCart(HttpServletResponse response, ShoppingCart shoppingCart) {
        setCookie(serializeShoppingCart(shoppingCart), 60 * 60 * 24 * 365, response);
    }

    public static void clearCurrentShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("currentShoppingCart");
        setCookie(null, 0, response);
    }

    private static Cookie findCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentShoppingCart")) {
                    if (cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        return cookie;
                    }
                }
            }
        }
        return null;
    }

    private static String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    private static void setCookie(String value, int age, HttpServletResponse resp) {
        Cookie cookie = new Cookie("currentShoppingCart", value);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    private static ShoppingCart deserializeShoppingCart(String string, ProductService productService) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = string.split("\\|");
        for (String item : items) {
            try {
                String[] data = item.split("-");
                long idProduct = Long.parseLong(data[0]);
                int count = Integer.parseInt(data[1]);
                ProductForm productForm = new ProductForm(idProduct, count);
                Product product = productService.findById(productForm.getIdProduct());
                shoppingCart.addProduct(product, productForm.getCount());
            } catch (RuntimeException e) {
//                LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
            }
        }
        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
    }
}
