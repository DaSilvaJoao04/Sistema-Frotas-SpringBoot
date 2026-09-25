package sistema.frotas.springboot.dto.abastecimento;

import sistema.frotas.springboot.enums.TipoCombustivel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AbastecimentoResponse(

          Long id,
          Long veiculoId,
          LocalDate dataAbastecimento,
          TipoCombustivel tipoCombustivel,
          BigDecimal quantidadeLitros,
          BigDecimal valorPorLitro,
          BigDecimal valorTotal

)

{}
