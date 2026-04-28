package pl.biblioteka.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Wypozyczenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksWypozyczenia;
    
    private LocalDateTime dataPoczatkowa;
    private LocalDateTime dataKoncowa;
    private LocalDateTime dataZwrotu; 

    @ManyToOne
    @JoinColumn(name = "indeks_czytelnicy")
    private Czytelnicy czytelnicy;

    @ManyToOne
    @JoinColumn(name = "indeks_wolumen")
    private Wolumen wolumen;
}