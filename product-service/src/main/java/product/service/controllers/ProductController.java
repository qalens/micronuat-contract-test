package product.service.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import product.service.services.ProductService;
import product.service.view.ProductQuote;

@Controller("/product")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Get(uri="/{id}/quote", produces= MediaType.APPLICATION_JSON)
    public ProductQuote getQuote(@PathVariable long id, @QueryValue Double amount) {
        System.out.println("Called");
        return productService.getQuote(id,amount);
    }
}