package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.DocumentoVeiculo;

public interface DocumentoVeiculoRepository extends JpaRepository<DocumentoVeiculo, Long> {
}
