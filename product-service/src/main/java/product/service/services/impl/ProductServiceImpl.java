package product.service.services.impl;

import product.service.models.Product;
import product.service.services.ProductService;
import product.service.view.ProductQuote;

import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class ProductServiceImpl implements ProductService {
    static HashMap<Long, Product> products=new HashMap<>();
    static {
        HashMap<Double,Double> discountsTrimmer=new HashMap<>();
        discountsTrimmer.put(5.0,5.0);
        discountsTrimmer.put(10.0,10.0);
        discountsTrimmer.put(100.0,30.0);

        HashMap<Double,Double> discountsMilk=new HashMap<>();
        discountsMilk.put(5.0,10.0);
        discountsMilk.put(10.0,20.0);
        discountsMilk.put(100.0,30.0);

        HashMap<Double,Double> discountsMiA2=new HashMap<>();
        discountsMiA2.put(2.0,10.0);
        discountsMiA2.put(3.0,15.0);
        discountsMiA2.put(5.0,30.0);

        HashMap<Double,Double> discountsTissue=new HashMap<>();
        discountsTissue.put(5.0,10.0);
        discountsTissue.put(10.0,15.0);
        discountsTissue.put(20.0,30.0);

        products.put(1L,new Product(1,"Trimmer",1200.0,discountsTrimmer,50.0,0.05));
        products.put(2L,new Product(2,"Milk",48.0,discountsMilk,5.0,0.05));
        products.put(3L,new Product(3,"Mi A2",12000.0,discountsMiA2,0.0,0.10));
        products.put(4L,new Product(4,"Tissue Paper Box",20.0,discountsTissue,10.0,0.08));
    }
    @Override
    public ProductQuote getQuote(long id, Double amount) {
        return products.get(id).getQuote(amount);
    }
}
