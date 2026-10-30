package sistema.frotas.springboot.dto.viagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record ViagemRequest(

        @NotNull
        Long veiculoId,

        @NotNull
        Long motoristaId,

        @NotNull
        LocalDate dataSaida,

        @NotBlank
        String origem,

        @NotBlank
        String destino,

        @NotNull
        @PositiveOrZero
        Long kmInicial


)

{}
