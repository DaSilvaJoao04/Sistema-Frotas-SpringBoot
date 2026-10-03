package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca(String placa);
    Boolean existsByPlaca(String placa);
    List<Veiculo> findByStatus(StatusVeiculo statusVeiculo);
    List<Veiculo> findByTipoCombustivel(TipoCombustivel tipoCombustivel);
    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);
    List<Veiculo> findByAnoFabricacao(Integer ano);
    List<Veiculo> findByStatusAndTipoCombustivel(StatusVeiculo statusVeiculo, TipoCombustivel tipoCombustivel);
    List<Veiculo> findByAnoFabricacaoGreaterThanEqual(Integer ano);
    List<Veiculo> findByQuilometragemAtualGreaterThan(Long quilometragem);
}
