package sistema.frotas.springboot.dto.multa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagarMultaRequest(

        @NotNull
        LocalDate dataPagamento,

        @NotNull
        @Positive
        BigDecimal valor
)

{}
