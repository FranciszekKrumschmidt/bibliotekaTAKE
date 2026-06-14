package pl.biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.biblioteka.model.Wypozyczenia;
import java.util.List;
public interface WypozyczeniaRepository extends JpaRepository<Wypozyczenia, Long> {
    List<Wypozyczenia> findByCzytelnicyIndeksCzytelnicy(Long indeksCzytelnicy);
}
