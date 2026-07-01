package pl.biblioteka.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.biblioteka.model.Czytelnicy;
import pl.biblioteka.repository.CzytelnicyRepository;
import java.util.List;

@Service
public class CzytelnicyService {
    private final CzytelnicyRepository czytelnicyRepository;

    public CzytelnicyService(CzytelnicyRepository czytelnicyRepository) {
        this.czytelnicyRepository = czytelnicyRepository;
    }

    @Transactional
    public Czytelnicy rejestrujCzytelnika(String nazwa, String email) {
        Czytelnicy czytelnik = new Czytelnicy();
        czytelnik.setNazwa(nazwa);
        czytelnik.setEmail(email);
        return czytelnicyRepository.save(czytelnik);
    }

    @Transactional
    public Czytelnicy edytujCzytelnika(Long id, String nazwa, String email) {
        Czytelnicy czytelnik = getCzytelnik(id);
        czytelnik.setNazwa(nazwa);
        czytelnik.setEmail(email);
        return czytelnicyRepository.save(czytelnik);
    }

    public Czytelnicy getCzytelnik(Long id) {
        return czytelnicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono czytelnika o id: " + id));
    }

    public List<Czytelnicy> pobierzWszystkichCzytelnikow() {
        return czytelnicyRepository.findAll();
    }

    @Transactional
    public void usunCzytelnika(Long id) {
        czytelnicyRepository.deleteById(id);
    }
}
