package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.manutencao.ManutencaoRequest;
import sistema.frotas.springboot.dto.manutencao.ManutencaoResponse;
import sistema.frotas.springboot.dto.motorista.MotoristaResponse;
import sistema.frotas.springboot.entities.Manutencao;


@Mapper(componentModel = "spring")
public interface ManutencaoMapper {

    ManutencaoResponse toResponse(Manutencao manutencao);

    Manutencao toEntity(ManutencaoRequest request);


}
