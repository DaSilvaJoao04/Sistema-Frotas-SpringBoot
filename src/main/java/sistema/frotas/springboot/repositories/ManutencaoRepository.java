package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Manutencao;
import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.TipoManutencao;

import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findByPlaca(String placa);
    List<Manutencao> findByStatus(StatusManutencao statusManutencao);
    List<Manutencao> findByPlacaAndTipo(String placa, TipoManutencao tipoManutencao);
}
