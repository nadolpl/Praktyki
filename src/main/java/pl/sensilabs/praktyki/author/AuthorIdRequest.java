package pl.sensilabs.praktyki.author;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AuthorIdRequest(@NotNull UUID id) { }
