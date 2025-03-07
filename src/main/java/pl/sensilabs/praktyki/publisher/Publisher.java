package pl.sensilabs.praktyki.publisher;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "publisher_id")
    private UUID id;

    @Column(name = "publisher_name")
    private String name;

    public Publisher(String name) {
        this.name = name;
    }
}