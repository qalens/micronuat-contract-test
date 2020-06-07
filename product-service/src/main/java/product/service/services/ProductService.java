package product.service.services;

import product.service.view.ProductQuote;

public interface ProductService {
    ProductQuote getQuote(long id, Double amount);
}
