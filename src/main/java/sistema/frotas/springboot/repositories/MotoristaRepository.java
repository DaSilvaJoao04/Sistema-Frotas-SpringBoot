package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Motorista;

public interface MotoristaRepository extends JpaRepository <Motorista, Long>{
}
