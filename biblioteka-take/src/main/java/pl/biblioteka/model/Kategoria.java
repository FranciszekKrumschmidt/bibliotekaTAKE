package pl.biblioteka.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksKategoria;

    private String nazwa;

    @OneToMany(mappedBy = "kategoria")
    @JsonIgnore
    private List<Tytul> tytuly;

    public Kategoria() {}

    public Long getIndeksKategoria() { return indeksKategoria; }
    public void setIndeksKategoria(Long indeksKategoria) { this.indeksKategoria = indeksKategoria; }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public List<Tytul> getTytuly() { return tytuly; }
    public void setTytuly(List<Tytul> tytuly) { this.tytuly = tytuly; }
}
