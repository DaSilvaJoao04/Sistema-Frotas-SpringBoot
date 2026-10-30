package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sistema.frotas.springboot.dto.viagem.ViagemRequest;
import sistema.frotas.springboot.dto.viagem.ViagemResponse;
import sistema.frotas.springboot.entities.Viagem;

@Mapper(componentModel = "spring")
public interface ViagemMapper {

    @Mapping(target = "veiculoId", source = "veiculo.id")
    @Mapping(target = "motoristaId", source = "motorista.id")

    ViagemResponse toResponse (Viagem viagem);

    Viagem toEntity(ViagemRequest request);


}
