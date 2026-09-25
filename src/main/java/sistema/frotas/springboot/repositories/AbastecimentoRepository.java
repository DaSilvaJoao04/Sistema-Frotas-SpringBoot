package sistema.frotas.springboot.repositories;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Abastecimento;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}
