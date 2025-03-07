package pl.sensilabs.praktyki.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record PublisherRequest(
        @NotBlank(message = "Publisher name cannot be empty")
        @Size(max = 100, message = "Publisher name cannot be longer than 100 characters")
        String name
) { }
