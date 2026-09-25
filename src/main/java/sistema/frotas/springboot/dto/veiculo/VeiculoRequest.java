package sistema.frotas.springboot.dto.veiculo;

import jakarta.validation.constraints.*;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.math.BigDecimal;
import java.time.LocalDate;


public record VeiculoRequest(

        @NotBlank
        @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$")
        String placa,

        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        @NotNull
        @Min(1980)
        Integer anoFabricacao,

        @NotBlank
        String cor,

        @NotNull
        @PositiveOrZero
        Long quilometragemAtual,

        @NotNull
        TipoCombustivel tipoCombustivel,

        @NotNull
        @Past
        LocalDate dataAquisicao,

        @NotNull
        @Positive
        BigDecimal valorAquisicao,

        @NotNull
        StatusVeiculo status
)

{}
