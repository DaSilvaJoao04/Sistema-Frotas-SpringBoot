package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long> {
}
