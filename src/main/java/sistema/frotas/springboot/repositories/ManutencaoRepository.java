package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}
