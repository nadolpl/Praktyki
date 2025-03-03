package pl.sensilabs.praktyki.responses;


import lombok.Builder;

@Builder
public record BookTypeResponse(String bookTypeName) {
}
