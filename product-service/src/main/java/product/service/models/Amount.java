package product.service.models;

public class Amount {
    public Double calculatedPrice;
    public Double discountAmount;
    public Double discountPercentage;
    public Double tax;
    public Double shippingCharges;
    public Double finalCost;

    public Amount(Double calculatedPrice, Double discountAmount, Double discountPercentage, Double tax, Double shippingCharges, Double finalCost) {
        this.calculatedPrice = calculatedPrice;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.tax = tax;
        this.shippingCharges = shippingCharges;
        this.finalCost = finalCost;
    }

//    public Amount(Double calculatedPrice, Double discountAmount, Double tax, Double shippingCharges, Double finalCost) {
//        this.calculatedPrice = calculatedPrice;
//        this.discountAmount = discountAmount;
//        this.tax = tax;
//        this.shippingCharges = shippingCharges;
//        this.finalCost = finalCost;
//    }
}
