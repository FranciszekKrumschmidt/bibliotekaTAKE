package pl.biblioteka.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Czytelnicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksCzytelnicy;
    
    private String nazwa;
    private String email;

    @OneToMany(mappedBy = "czytelnicy")
    private List<Wypozyczenia> wypozyczenia;
}