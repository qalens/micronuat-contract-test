package order.service.services.impl;

import order.service.clients.ProductClient;
import order.service.models.Cart;
import order.service.models.LineItem;
import order.service.services.CartService;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;

@Singleton
public class CartServiceImpl implements CartService {
    private ProductClient productClient;

    public CartServiceImpl(ProductClient productClient) {
        this.productClient = productClient;
    }

    static long nextId=1;
    static HashMap<Long, Cart> carts=new HashMap<Long, Cart>();

    @Override
    public Cart createNewCart() {
        Cart cart=new Cart();
        cart.id = nextId++;
        cart.lineItems = new ArrayList<>();
        carts.put(cart.id,cart);
        return cart;
    }

    @Override
    public Cart addToCart(long cartId, long productId, Double quantity) {
        LineItem lineItem = productClient.getQuote(productId,quantity);
        Cart cart=carts.get(cartId);
        cart.lineItems.add(lineItem);
        return cart;
    }
}
