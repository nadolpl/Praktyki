package pl.sensilabs.praktyki.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BookTypeIdNotFoundException {
  private final String message;
  private final HttpStatus httpStatus;
  private final ZonedDateTime zonedDateTime;
}


