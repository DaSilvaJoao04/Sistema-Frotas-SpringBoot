package sistema.frotas.springboot.dto.motorista;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import sistema.frotas.springboot.enums.CategoriaCNH;
import sistema.frotas.springboot.enums.StatusMotorista;

import java.time.LocalDate;

public record MotoristaRequest(

        @NotBlank
        String nomeCompleto,

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        String numeroCNH,

        @NotNull
        CategoriaCNH categoria,

        @NotNull
        @Past
        LocalDate dataNascimento,

        @NotNull
        @Future
        LocalDate dataValidadeCNH,

        @NotBlank
        @Size(min = 10, max = 11)
        String telefone,

        @NotNull
        LocalDate dataContratacao,

        @NotNull
        StatusMotorista status
)

{}
