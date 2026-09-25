package sistema.frotas.springboot.dto.abastecimento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AbastecimentoRequest(

        @NotNull
        Long veiculoId,

        @NotNull
        @Past
        LocalDate dataAbastecimento,

        @NotNull
        TipoCombustivel tipoCombustivel,

        @NotNull
        @Positive
        BigDecimal quantidadeLitros,

        @NotNull
        @Positive
        BigDecimal valorPorLitro

)

{}
