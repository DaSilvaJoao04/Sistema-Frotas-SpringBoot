package sistema.frotas.springboot.dto.multa;

import sistema.frotas.springboot.enums.StatusMulta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MultaResponse(

      Long id,
      Long veiculoId,
      LocalDate dataInfracao,
      String codigoInfracao,
      BigDecimal custo,
      LocalDate dataVencimento,
      LocalDate dataPagamento,
      StatusMulta status

)

{}
