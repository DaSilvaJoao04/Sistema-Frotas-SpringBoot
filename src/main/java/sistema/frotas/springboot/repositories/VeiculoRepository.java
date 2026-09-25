package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
