package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Viagem;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    List<Viagem> findByVeiculoId(Long veiculoId);
    List<Viagem> findByMotoristaId(Long motoristaId);
}
