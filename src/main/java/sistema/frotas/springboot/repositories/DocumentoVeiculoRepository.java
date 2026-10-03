package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.DocumentoVeiculo;
import sistema.frotas.springboot.enums.TipoDocumento;

import java.util.List;
import java.util.Optional;

public interface DocumentoVeiculoRepository extends JpaRepository<DocumentoVeiculo, Long> {

    Optional<DocumentoVeiculo> findByNumeroDocumento(String numeroDocumento);
    List<DocumentoVeiculo> findByTipoDocumento(TipoDocumento tipoDocumento);
    List<DocumentoVeiculo> findByVeiculoId(Long veiculoId);
}
