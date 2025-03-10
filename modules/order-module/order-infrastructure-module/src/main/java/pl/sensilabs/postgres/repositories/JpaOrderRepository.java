package pl.sensilabs.postgres.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sensilabs.postgres.entities.OrderEntity;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID> {

}
