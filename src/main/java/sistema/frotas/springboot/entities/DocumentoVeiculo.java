package sistema.frotas.springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.frotas.springboot.enums.TipoDocumento;

import java.time.LocalDate;

@Entity
@Table(name = "documentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo;

    @Column
    private String numeroDocumento;

    @Column
    private LocalDate dataEmissao;

    @Column(nullable = false)
    private LocalDate dataValidade;

}
