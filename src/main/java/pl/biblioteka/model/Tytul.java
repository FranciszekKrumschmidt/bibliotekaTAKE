package pl.biblioteka.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Tytul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksTytul;
    
    private String tytul;
    private LocalDateTime dataWydania;
    private String autor;

    @ManyToOne
    @JoinColumn(name = "indeks_kategoria")
    private Kategoria kategoria;

    @OneToMany(mappedBy = "tytul")
    private List<Wolumen> wolumeny;
}