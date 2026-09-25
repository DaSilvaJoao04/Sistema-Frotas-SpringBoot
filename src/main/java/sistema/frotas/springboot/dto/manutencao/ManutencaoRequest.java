package sistema.frotas.springboot.dto.manutencao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.TipoManutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ManutencaoRequest(

        @NotNull
        Long veiculoId,

        @NotNull
        TipoManutencao tipo,

        @NotNull
        StatusManutencao status,

        @NotNull
        LocalDate dataEntrada,

        LocalDate dataConclusao,

        @NotNull
        @Positive
        Long quilometragem,

        @NotNull
        @Positive
        BigDecimal custo
)

{}
