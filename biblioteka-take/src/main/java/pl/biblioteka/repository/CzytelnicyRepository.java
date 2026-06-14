package pl.biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.biblioteka.model.Czytelnicy;
public interface CzytelnicyRepository extends JpaRepository<Czytelnicy, Long> {}
