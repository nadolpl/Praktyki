package pl.sensilabs.controllers;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sensilabs.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/create")
  public ResponseEntity<UUID> createOrder() {
    var orderId = orderService.createOrder();
    return ResponseEntity.ok(orderId);
  }
}
