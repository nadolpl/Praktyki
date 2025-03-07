package pl.sensilabs.praktyki.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record BookTypeRequest (
    @NotNull
    @Length(min = 3,max=20)
    String bookTypeName)
{}
