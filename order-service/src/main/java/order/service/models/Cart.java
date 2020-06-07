package order.service.models;

import order.service.view.CartResponse;
import order.service.view.LineItemResponse;

import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    public long id;
    public List<LineItem> lineItems;
    public Double getDiscount(){
        return lineItems.stream().map((lineItem) -> lineItem.amount.discountAmount).reduce(0.0, (item1, item2) -> (item1 + item2));

    }
    public Double getPrice(){
        return lineItems.stream().map((lineItem) -> lineItem.amount.finalCost ).reduce(0.0,(item1,item2)-> (item1 + item2));
    }
    public CartResponse toCartResponse(){
        CartResponse cartResponse=new CartResponse();
        cartResponse.id = id;
        cartResponse.lineItems = lineItems.stream().map((lineItem)->{
            return lineItem.toLineItemResponse();
        }).collect(Collectors.toList());
        cartResponse.discount = getDiscount();
        cartResponse.price = getPrice();
        return cartResponse;
    }
}
