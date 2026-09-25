package sistema.frotas.springboot.dto.multa;

import jakarta.validation.constraints.*;
import sistema.frotas.springboot.enums.StatusMulta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MultaRequest(

        @NotNull
        Long veiculoId,

        @NotNull
        @Past
        LocalDate dataInfracao,

        @NotBlank
        String codigoInfracao,

        @NotNull
        @Positive
        BigDecimal custo,

        @NotNull
        LocalDate dataVencimento,

        LocalDate dataPagamento,

        @NotNull
        StatusMulta status








)

{}
