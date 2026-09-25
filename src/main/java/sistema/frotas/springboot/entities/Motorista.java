package sistema.frotas.springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.frotas.springboot.enums.CategoriaCNH;
import sistema.frotas.springboot.enums.StatusMotorista;

import java.time.LocalDate;

@Entity
@Table(name = "motoristas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String numeroCNH;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCNH categoria;

    @Column
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private LocalDate dataValidadeCNH;

    @Column
    private String telefone;

    @Column
    private LocalDate dataContratacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMotorista status;


}
