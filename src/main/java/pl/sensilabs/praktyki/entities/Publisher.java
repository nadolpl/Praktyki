package pl.sensilabs.praktyki.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publisherId;

    @Column(nullable = false)
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    List<PublishedBook> publishedBooks;
}
