package pl.sensilabs.responses;

import java.util.UUID;

public record OrderItemResponse(
    UUID bookId,
    Integer quantity
) {

}
