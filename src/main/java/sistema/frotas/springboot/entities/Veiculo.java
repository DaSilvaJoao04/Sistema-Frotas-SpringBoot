package sistema.frotas.springboot.entities;


import jakarta.persistence.*;
import lombok.*;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer anoFabricacao;

    @Column
    private String cor;

    @Column(nullable = false)
    private Long quilometragemAtual;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoCombustivel tipoCombustivel;

    @Column
    private LocalDate dataAquisicao;

    @Column
    private BigDecimal valorAquisicao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVeiculo status;


}
