package sistema.frotas.springboot.dto.manutencao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinalizarManutencaoRequest(

        @NotNull
        LocalDate dataConclusao,

        @NotNull
        @Positive
        BigDecimal custo,

        @NotNull
        @PositiveOrZero
        Long quilometragem
)

{}
