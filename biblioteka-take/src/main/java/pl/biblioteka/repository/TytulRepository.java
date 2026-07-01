package pl.biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.biblioteka.model.Tytul;
public interface TytulRepository extends JpaRepository<Tytul, Long> {}
