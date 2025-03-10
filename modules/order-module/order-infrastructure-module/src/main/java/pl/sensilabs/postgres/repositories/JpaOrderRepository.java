package pl.sensilabs.postgres.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sensilabs.postgres.entities.Order;

public interface JpaOrderRepository extends JpaRepository<Order, UUID> {

}
