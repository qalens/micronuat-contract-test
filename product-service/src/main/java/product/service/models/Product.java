package product.service.models;

import product.service.view.ProductQuote;
import product.service.view.ProductView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

public class Product {
    public long id;
    public String name;
    public Double price;
    public HashMap<Double,Double> discounts;
    public Double shippingPrice;
    public Double taxRate;

    public Product(long id, String name, Double price, HashMap<Double, Double> discounts, Double shippingPrice, Double taxRate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discounts = discounts;
        this.shippingPrice = shippingPrice;
        this.taxRate = taxRate;
    }

    public Double getApplicableDiscount(Double amount){
        List<Double> ranges=new ArrayList<>();
        ranges.addAll(discounts.keySet());
        ranges.sort((a,b)->{
            if(a==b)
                return 0;
            else if (a<b)
                return -1;
            else
                return 1;
        });
        Double discount=0.0;
        for (int i=0;i<ranges.size();i++){
            if(amount<ranges.get(i)){
                return discount;
            } else {
                discount = discounts.get(ranges.get(i));
            }
        }
        return discount;
    }
    public Amount getApplicableAmount(Double amount){
        Double basePrice=price*amount;
        Double discountPercentage= getApplicableDiscount(amount);
        Double discountAmount = basePrice * discountPercentage/100;
        Double shipping = shippingPrice;
        Double finalPrice = basePrice - discountAmount + shipping;
        Double tax= finalPrice * taxRate;
        Double finalCost = finalPrice + tax;

        return new Amount(basePrice,discountAmount,discountPercentage,tax,shipping,finalCost);
//        return new Amount(basePrice,discountAmount,tax,shipping,finalCost);
    }
    public ProductQuote getQuote(Double amount){
        ProductQuote productQuote=new ProductQuote();
        productQuote.product = new ProductView(id,name);
        productQuote.amount = getApplicableAmount(amount);
        productQuote.quantity = amount;
        return productQuote;
    }
}
