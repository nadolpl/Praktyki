package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
public record AuthorRequest(
        @NotBlank(message = "First name cannot be empty")
        @Size(max = 100, message = "First name cannot be longer than 100 characters")
        String firstName,

        @NotBlank(message = "Last name cannot be empty")
        @Size(max = 100, message = "Last name cannot be longer than 100 characters")
        String lastName,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        String email
) { }
