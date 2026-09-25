package sistema.frotas.springboot.dto.documentoVeiculo;

import jakarta.validation.constraints.*;
import sistema.frotas.springboot.enums.TipoDocumento;

import java.time.LocalDate;

public record DocumentoVeiculoRequest(

        @NotNull
        Long veiculoId,

        @NotNull
        TipoDocumento tipo,

        @NotBlank
        @Size(max = 12)
        String numeroDocumento,

        @NotNull
        @Past
        LocalDate dataEmissao,

        @NotNull
        @Future
        LocalDate dataValidade


)

{}
