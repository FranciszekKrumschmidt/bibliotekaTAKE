package pl.biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.biblioteka.model.Kategoria;
public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {}
