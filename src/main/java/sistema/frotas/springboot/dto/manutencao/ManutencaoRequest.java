package sistema.frotas.springboot.dto.manutencao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
        LocalDate dataEntrada

)

{}
