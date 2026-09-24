package sistema.frotas.springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "abastecimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @Column(nullable = false)
    private LocalDate dataAbastecimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCombustivel tipoCombustivel;

    @Column(nullable = false)
    private BigDecimal quantidadeLitros;

    @Column(nullable = false)
    private BigDecimal valorPorLitro;

    @Column(nullable = false)
    private BigDecimal valorTotal;


}
