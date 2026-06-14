package pl.biblioteka.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    public Wypozyczenia() {}

    public Long getIndeksWypozyczenia() { return indeksWypozyczenia; }
    public void setIndeksWypozyczenia(Long indeksWypozyczenia) { this.indeksWypozyczenia = indeksWypozyczenia; }

    public LocalDateTime getDataPoczatkowa() { return dataPoczatkowa; }
    public void setDataPoczatkowa(LocalDateTime dataPoczatkowa) { this.dataPoczatkowa = dataPoczatkowa; }

    public LocalDateTime getDataKoncowa() { return dataKoncowa; }
    public void setDataKoncowa(LocalDateTime dataKoncowa) { this.dataKoncowa = dataKoncowa; }

    public LocalDateTime getDataZwrotu() { return dataZwrotu; }
    public void setDataZwrotu(LocalDateTime dataZwrotu) { this.dataZwrotu = dataZwrotu; }

    public Czytelnicy getCzytelnicy() { return czytelnicy; }
    public void setCzytelnicy(Czytelnicy czytelnicy) { this.czytelnicy = czytelnicy; }

    public Wolumen getWolumen() { return wolumen; }
    public void setWolumen(Wolumen wolumen) { this.wolumen = wolumen; }
}
