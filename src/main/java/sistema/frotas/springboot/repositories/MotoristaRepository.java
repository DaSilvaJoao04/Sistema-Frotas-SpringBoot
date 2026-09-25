package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Motorista;
import sistema.frotas.springboot.enums.CategoriaCNH;
import sistema.frotas.springboot.enums.StatusMotorista;

import java.util.List;

public interface MotoristaRepository extends JpaRepository <Motorista, Long>{

    List<Motorista> findMotoristaByStatus(StatusMotorista statusMotorista);
    List<Motorista> findMotoristaByCategoria(CategoriaCNH categoria);

}
