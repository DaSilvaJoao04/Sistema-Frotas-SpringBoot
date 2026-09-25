package sistema.frotas.springboot.dto.documentoVeiculo;


import sistema.frotas.springboot.enums.TipoDocumento;

import java.time.LocalDate;

public record DocumentoVeiculoResponse(

        Long id,
        Long veiculoId,
        TipoDocumento tipo,
        String numeroDocumento,
        LocalDate dataEmissao,
        LocalDate dataValidade


)

{}
