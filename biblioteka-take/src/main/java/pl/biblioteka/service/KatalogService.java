package pl.biblioteka.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.biblioteka.model.Kategoria;
import pl.biblioteka.model.Tytul;
import pl.biblioteka.model.Wolumen;
import pl.biblioteka.repository.KategoriaRepository;
import pl.biblioteka.repository.TytulRepository;
import pl.biblioteka.repository.WolumenRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class KatalogService {
    private final KategoriaRepository kategoriaRepository;
    private final TytulRepository tytulRepository;
    private final WolumenRepository wolumenRepository;

    public KatalogService(KategoriaRepository kategoriaRepository, TytulRepository tytulRepository, WolumenRepository wolumenRepository) {
        this.kategoriaRepository = kategoriaRepository;
        this.tytulRepository = tytulRepository;
        this.wolumenRepository = wolumenRepository;
    }

    @Transactional
    public Kategoria dodajKategorie(String nazwa) {
        Kategoria kategoria = new Kategoria();
        kategoria.setNazwa(nazwa);
        return kategoriaRepository.save(kategoria);
    }

    @Transactional
    public Tytul dodajTytul(String nazwaTytulu, String autor, LocalDateTime dataWydania, Long indeksKategoria) {
        Kategoria kategoria = kategoriaRepository.findById(indeksKategoria)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii"));
        Tytul tytul = new Tytul();
        tytul.setTytul(nazwaTytulu);
        tytul.setAutor(autor);
        tytul.setDataWydania(dataWydania);
        tytul.setKategoria(kategoria);
        return tytulRepository.save(tytul);
    }

    @Transactional
    public Wolumen dodajWolumen(Long indeksTytul) {
        Tytul tytul = tytulRepository.findById(indeksTytul)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono tytułu"));
        Wolumen wolumen = new Wolumen();
        wolumen.setTytul(tytul);
        wolumen.setStatusWypozyczenia(false);
        return wolumenRepository.save(wolumen);
    }

    public Wolumen getWolumen(Long id) {
        return wolumenRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono wolumenu"));
    }

    public List<Kategoria> pobierzWszystkieKategorie() {
        return kategoriaRepository.findAll();
    }

    public List<Tytul> pobierzWszystkieTytuly() {
        return tytulRepository.findAll();
    }

    public List<Wolumen> pobierzWszystkieWolumeny() {
        return wolumenRepository.findAll();
    }
}
