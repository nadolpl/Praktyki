package pl.sensilabs;

import java.math.BigDecimal;
import java.util.UUID;

interface BookPriceFetcher {

  BigDecimal fetch(UUID orderId);
}
