package pl.sensilabs.praktyki.responses;

import lombok.Builder;

@Builder
public record BookCategoryResponse(String categoryName) {
}
