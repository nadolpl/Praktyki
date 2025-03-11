package pl.sensilabs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sensilabs.exceptions.InvalidOrderStateException;
import pl.sensilabs.exceptions.OrderNotFoundException;

@ControllerAdvice
public class OrderControllerAdvice {

  @ExceptionHandler(OrderNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ProblemDetail handleOrderNotFoundException(OrderNotFoundException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(InvalidOrderStateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProblemDetail handleOrderStateException(InvalidOrderStateException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
  }
}
