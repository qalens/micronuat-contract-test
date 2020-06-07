package order.service.view;

import java.util.List;

public class CartResponse {
    public long id;
    public List<LineItemResponse> lineItems;
    public Double discount;
    public Double price;
}
