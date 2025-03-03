package pl.sensilabs.praktyki.requests;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PublishedBookRequest {
  private UUID bookId;
  private UUID publisherId;
  private Integer bookTypeId;
  private Integer releaseNumber;
  private LocalDate publishDate;
}
