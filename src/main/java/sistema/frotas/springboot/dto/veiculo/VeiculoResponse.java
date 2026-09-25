package sistema.frotas.springboot.dto.veiculo;

import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VeiculoResponse(

        Long id,
        String placa,
        String marca,
        String modelo,
        String cor,
        Integer anoFabricacao,
        Long quilometragemAtual,
        TipoCombustivel tipoCombustivel,
        LocalDate dataAquisicao,
        BigDecimal valorAquisicao,
        StatusVeiculo status
)

{}
