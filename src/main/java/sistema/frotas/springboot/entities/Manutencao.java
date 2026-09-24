package sistema.frotas.springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.TipoManutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "manutencoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoManutencao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusManutencao status;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    private LocalDate dataConclusao;

    private Long quilometragem;

    private BigDecimal custo;

}
