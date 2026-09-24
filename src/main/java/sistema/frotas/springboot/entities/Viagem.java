package sistema.frotas.springboot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "viagens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "id_motorista")
    private Motorista motorista;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Column
    private LocalDate dataChegada;

    private String origem;

    private String destino;

    @Column(nullable = false)
    private Long kmInicial;

    @Column
    private Long kmFinal;

}
