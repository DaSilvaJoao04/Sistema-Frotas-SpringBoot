package sistema.frotas.springboot.dto.documentoVeiculo;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarDocumentoRequest(

        @NotNull
        LocalDate dataValidade
) {
}
