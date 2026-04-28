package pl.biblioteka.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Wolumen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksWolumen;
    
    private boolean statusWypozyczenia;

    @ManyToOne
    @JoinColumn(name = "indeks_tytul")
    private Tytul tytul;
}