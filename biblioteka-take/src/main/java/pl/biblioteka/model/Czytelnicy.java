package pl.biblioteka.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Czytelnicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksCzytelnicy;

    private String nazwa;
    private String email;

    @OneToMany(mappedBy = "czytelnicy")
    @JsonIgnore
    private List<Wypozyczenia> wypozyczenia;

    public Czytelnicy() {}

    public Long getIndeksCzytelnicy() { return indeksCzytelnicy; }
    public void setIndeksCzytelnicy(Long indeksCzytelnicy) { this.indeksCzytelnicy = indeksCzytelnicy; }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Wypozyczenia> getWypozyczenia() { return wypozyczenia; }
    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) { this.wypozyczenia = wypozyczenia; }
}
