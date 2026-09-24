package sistema.frotas.springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.frotas.springboot.enums.StatusMulta;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @Column(nullable = false)
    private LocalDate dataInfracao;

    @Column(nullable = false)
    private String codigoInfracao;

    @Column(nullable = false)
    private BigDecimal custo;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMulta status;



}
