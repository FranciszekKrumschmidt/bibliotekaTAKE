package pl.biblioteka.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
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
    @JsonIgnore
    private List<Wolumen> wolumeny;

    public Tytul() {}

    public Long getIndeksTytul() { return indeksTytul; }
    public void setIndeksTytul(Long indeksTytul) { this.indeksTytul = indeksTytul; }

    public String getTytul() { return tytul; }
    public void setTytul(String tytul) { this.tytul = tytul; }

    public LocalDateTime getDataWydania() { return dataWydania; }
    public void setDataWydania(LocalDateTime dataWydania) { this.dataWydania = dataWydania; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Kategoria getKategoria() { return kategoria; }
    public void setKategoria(Kategoria kategoria) { this.kategoria = kategoria; }

    public List<Wolumen> getWolumeny() { return wolumeny; }
    public void setWolumeny(List<Wolumen> wolumeny) { this.wolumeny = wolumeny; }
}
