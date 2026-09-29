package sistema.frotas.springboot.repositories;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Abastecimento;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.time.LocalDate;
import java.util.List;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    List<Abastecimento> findByVeiculoId(Long veiculoId);
    List<Abastecimento> findByDataAbastecimento(LocalDate dataAbastecimento);
    List<Abastecimento> findByTipoCombustivel(TipoCombustivel tipoCombustivel);

}
