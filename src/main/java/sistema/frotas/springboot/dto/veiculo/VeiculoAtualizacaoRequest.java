package sistema.frotas.springboot.dto.veiculo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import sistema.frotas.springboot.enums.StatusVeiculo;

public record VeiculoAtualizacaoRequest(

        @NotNull
        @PositiveOrZero
        Long quilometragemAtual,

        @NotNull
        StatusVeiculo status

) {
}
