package order.service.integration;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.micronaut.test.annotation.MicronautTest;
import order.service.models.Cart;
import order.service.services.CartService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

@MicronautTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "product-service",hostInterface = "localhost",port = "8081")
public class CartServicePactTest {
    @Inject
    CartService cartService;

    @Pact(provider = "product-service",consumer = "order-service")
    public RequestResponsePact quotePact(PactDslWithProvider builder){
        DslPart body= newJsonBody((root)->{
            root.object("product",(product)->{
                product.numberType("id",1);
                product.stringType("name","Milk");
            });
            root.numberType("quantity",1);
            root.object("amount",(amount)->{
                amount.numberType("calculatedPrice",480.0);
                amount.numberType("discountAmount",96.0);
                amount.numberType("discountPercentage",0.10);
                amount.numberType("tax",19.45);
                amount.numberType("shippingCharges",5.0);
                amount.numberType("finalCost",408.45);
            });
        }).build();
        return builder
                .given("Product With Id 1 exists")
                .uponReceiving("Get Quote for Product for quantity 1.0")
                .path("/product/1/quote")
                .query("amount=1.0")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }
    @Pact(provider = "product-service",consumer = "order-service")
    public RequestResponsePact quotePact2(PactDslWithProvider builder){
        DslPart body= newJsonBody((root)->{
            root.object("product",(product)->{
                product.numberType("id",1);
                product.stringType("name","Milk");
            });
            root.numberType("quantity",1);
            root.object("amount",(amount)->{
                amount.numberType("calculatedPrice",480.0);
                amount.numberType("discountAmount",96.0);
                amount.numberType("discountPercentage",0.10);
                amount.numberType("tax",19.45);
                amount.numberType("shippingCharges",5.0);
                amount.numberType("finalCost",408.45);
            });
        }).build();
        return builder
                .given("Product With Id 1 exists")
                .uponReceiving("Get Quote for Product for quantity 1.0")
                .path("/product/1/details")
                .query("amount=1.0")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    public void testAddToCart(){
        Cart cart =cartService.createNewCart();
        Cart resultantCart=cartService.addToCart(cart.id,1,1.0);
        assertEquals(1,resultantCart.lineItems.size());
        assertEquals("Milk",resultantCart.lineItems.get(0).product.name);
    }
}
