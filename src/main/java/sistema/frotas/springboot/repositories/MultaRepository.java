package sistema.frotas.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.frotas.springboot.entities.Multa;
import sistema.frotas.springboot.enums.StatusMulta;


import java.util.List;

public interface MultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByVeiculoPlaca(String placa);
    List<Multa> findByStatus(StatusMulta statusMulta);

}
