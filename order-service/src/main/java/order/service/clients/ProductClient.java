package order.service.clients;

import io.micronaut.http.client.annotation.Client;

@Client("${product.url}")
public interface ProductClient extends ProductOperations{
}
