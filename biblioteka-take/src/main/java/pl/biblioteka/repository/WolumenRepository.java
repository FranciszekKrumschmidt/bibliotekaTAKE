package pl.biblioteka.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.biblioteka.model.Wolumen;
public interface WolumenRepository extends JpaRepository<Wolumen, Long> {}
