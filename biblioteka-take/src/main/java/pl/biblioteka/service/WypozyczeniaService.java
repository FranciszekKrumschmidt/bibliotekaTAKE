package pl.biblioteka.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.biblioteka.model.Czytelnicy;
import pl.biblioteka.model.Wolumen;
import pl.biblioteka.model.Wypozyczenia;
import pl.biblioteka.repository.WolumenRepository;
import pl.biblioteka.repository.WypozyczeniaRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WypozyczeniaService {
    private final WypozyczeniaRepository wypozyczeniaRepository;
    private final CzytelnicyService czytelnicyService;
    private final KatalogService katalogService;
    private final WolumenRepository wolumenRepository;

    public WypozyczeniaService(WypozyczeniaRepository wypozyczeniaRepository, CzytelnicyService czytelnicyService, KatalogService katalogService, WolumenRepository wolumenRepository) {
        this.wypozyczeniaRepository = wypozyczeniaRepository;
        this.czytelnicyService = czytelnicyService;
        this.katalogService = katalogService;
        this.wolumenRepository = wolumenRepository;
    }

    @Transactional
    public Wypozyczenia wypozyczWolumen(Long indeksCzytelnicy, Long indeksWolumen) {
        Wolumen wolumen = katalogService.getWolumen(indeksWolumen);
        if (wolumen.isStatusWypozyczenia()) {
            throw new RuntimeException("Wolumen jest już wypożyczony");
        }
        Czytelnicy czytelnik = czytelnicyService.getCzytelnik(indeksCzytelnicy);

        wolumen.setStatusWypozyczenia(true);
        wolumenRepository.save(wolumen);

        Wypozyczenia wypozyczenie = new Wypozyczenia();
        wypozyczenie.setCzytelnicy(czytelnik);
        wypozyczenie.setWolumen(wolumen);
        wypozyczenie.setDataPoczatkowa(LocalDateTime.now());
        wypozyczenie.setDataKoncowa(LocalDateTime.now().plusDays(14)); // max 14 dni
        return wypozyczeniaRepository.save(wypozyczenie);
    }

    @Transactional
    public Wypozyczenia zwrocWolumen(Long indeksWypozyczenia, LocalDateTime dataZwrotu) {
        Wypozyczenia wypozyczenie = wypozyczeniaRepository.findById(indeksWypozyczenia)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono wypożyczenia"));
        
        if (wypozyczenie.getDataZwrotu() != null) {
            throw new RuntimeException("Wolumen już został zwrócony");
        }

        wypozyczenie.setDataZwrotu(dataZwrotu != null ? dataZwrotu : LocalDateTime.now());
        
        Wolumen wolumen = wypozyczenie.getWolumen();
        wolumen.setStatusWypozyczenia(false);
        wolumenRepository.save(wolumen);

        return wypozyczeniaRepository.save(wypozyczenie);
    }

    public List<Wypozyczenia> pobierzHistorieCzytelnika(Long indeksCzytelnicy) {
        return wypozyczeniaRepository.findByCzytelnicyIndeksCzytelnicy(indeksCzytelnicy);
    }

    public List<Wypozyczenia> pobierzWszystkieWypozyczenia() {
        return wypozyczeniaRepository.findAll();
    }

    @Transactional
    public void usunWypozyczenie(Long id) {
        Wypozyczenia w = wypozyczeniaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono wypożyczenia"));
        if (w.getDataZwrotu() == null) {
            Wolumen wolumen = w.getWolumen();
            wolumen.setStatusWypozyczenia(false);
            wolumenRepository.save(wolumen);
        }
        wypozyczeniaRepository.delete(w);
    }
}
