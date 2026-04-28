package pl.biblioteka.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Kategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksKategoria;
    
    private String nazwa;

    @OneToMany(mappedBy = "kategoria")
    private List<Tytul> tytuly;
}
