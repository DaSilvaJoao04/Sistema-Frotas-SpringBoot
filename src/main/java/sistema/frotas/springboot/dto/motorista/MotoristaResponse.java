package sistema.frotas.springboot.dto.motorista;


import sistema.frotas.springboot.enums.StatusMotorista;

import java.time.LocalDate;

public record MotoristaResponse(

        Long id,
        String nomeCompleto,
        String cpf,
        String numeroCNH,
        String categoria,
        LocalDate dataNascimento,
        LocalDate dataValidadeCNH,
        String telefone,
        LocalDate dataContratacao,
        StatusMotorista status
)

{}
