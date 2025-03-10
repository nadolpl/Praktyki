package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;

public interface BookPriceFetcher {

  BigDecimal fetch(UUID orderId);
}
