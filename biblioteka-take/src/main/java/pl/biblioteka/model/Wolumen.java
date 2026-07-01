package pl.biblioteka.model;

import jakarta.persistence.*;

@Entity
public class Wolumen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indeksWolumen;

    private boolean statusWypozyczenia;

    @ManyToOne
    @JoinColumn(name = "indeks_tytul")
    private Tytul tytul;

    public Wolumen() {}

    public Long getIndeksWolumen() { return indeksWolumen; }
    public void setIndeksWolumen(Long indeksWolumen) { this.indeksWolumen = indeksWolumen; }

    public boolean isStatusWypozyczenia() { return statusWypozyczenia; }
    public void setStatusWypozyczenia(boolean statusWypozyczenia) { this.statusWypozyczenia = statusWypozyczenia; }

    public Tytul getTytul() { return tytul; }
    public void setTytul(Tytul tytul) { this.tytul = tytul; }
}
