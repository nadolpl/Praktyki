package pl.sensilabs.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record BookAddRequest(
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    UUID bookId,

    @Positive(message = "Quantity cannot be equal and less than 0")
    @Max(value = 10, message = "Cannot order more than 10 copies of the same book.")
    int quantity
) {

}
