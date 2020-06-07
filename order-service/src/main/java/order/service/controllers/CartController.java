package order.service.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import order.service.services.CartService;
import order.service.view.CartResponse;
import order.service.view.LineItemRequest;

@Controller("/cart")
public class CartController {
    CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Post(uri = "/",produces = MediaType.APPLICATION_JSON)
    public CartResponse newCart(){
        return cartService.createNewCart().toCartResponse();
    }
    @Post(uri = "/{id}/add",produces = MediaType.APPLICATION_JSON)
    public CartResponse addToCart(@PathVariable long id, @Body LineItemRequest lineItemRequest){
        return cartService.addToCart(id,lineItemRequest.productId,lineItemRequest.quantity).toCartResponse();
    }
}