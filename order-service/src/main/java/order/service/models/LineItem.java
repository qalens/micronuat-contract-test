package order.service.models;

import order.service.view.LineItemResponse;

public class LineItem {
    public Product product;
    public Double quantity;
    public Amount amount;
    public LineItemResponse toLineItemResponse(){
        LineItemResponse lineItemResponse=new LineItemResponse();
        lineItemResponse.productId = this.product.id;
        lineItemResponse.productName = this.product.name;
        lineItemResponse.amount = this.amount;
        return lineItemResponse;
    }
}
