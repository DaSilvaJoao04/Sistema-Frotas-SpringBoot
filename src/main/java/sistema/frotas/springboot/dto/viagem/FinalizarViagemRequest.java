package sistema.frotas.springboot.dto.viagem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record FinalizarViagemRequest(

        @NotNull
        LocalDate dataChegada,

        @NotNull
        @Positive
        Long kmFinal

) {
}
