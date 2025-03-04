package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record PublisherRequest(
        @NotBlank(message = "Publisher name cannot be empty")
        @Size(max = 100, message = "Publisher name cannot be longer than 100 characters")
        String publisherName
) { }
