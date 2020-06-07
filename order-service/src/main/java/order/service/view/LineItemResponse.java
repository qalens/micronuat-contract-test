package order.service.view;

import order.service.models.Amount;

public class LineItemResponse {
    public long productId;
    public String productName;
    public Amount amount;
}
