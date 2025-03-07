package pl.sensilabs.praktyki.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface PublisherRepository extends JpaRepository<Publisher, UUID> {
}
