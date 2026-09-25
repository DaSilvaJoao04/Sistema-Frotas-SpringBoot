package sistema.frotas.springboot.dto.manutencao;

import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.TipoManutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ManutencaoResponse(

        Long id,
        Long veiculoId,
        TipoManutencao tipo,
        StatusManutencao status,
        LocalDate dataEntrada,
        LocalDate dataConclusao,
        Long quilometragem,
        BigDecimal custo
)

{}
