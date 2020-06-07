package order.service.services;

import order.service.models.Cart;

public interface CartService {
    Cart createNewCart();
    Cart addToCart(long cartId,long productId,Double quantity);
}
