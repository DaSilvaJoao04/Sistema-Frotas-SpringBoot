package sistema.frotas.springboot.dto.viagem;

import sistema.frotas.springboot.entities.Motorista;
import sistema.frotas.springboot.entities.Veiculo;

import java.time.LocalDate;

public record ViagemResponse(

        Long id,
        Veiculo veiculoId,
        Motorista motoristaId,
        LocalDate dataSaida,
        LocalDate dataChegada,
        String origem,
        String destino,
        Long kmInicial,
        Long kmFinal
)

{}
