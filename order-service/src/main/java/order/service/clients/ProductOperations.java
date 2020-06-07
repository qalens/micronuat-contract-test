package order.service.clients;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.validation.Validated;
import order.service.models.LineItem;

import javax.validation.constraints.NotBlank;

@Validated
public interface ProductOperations {
    @Get("/{id}/quote")
    LineItem getQuote(@PathVariable long id,@QueryValue Double amount);
}
