package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
