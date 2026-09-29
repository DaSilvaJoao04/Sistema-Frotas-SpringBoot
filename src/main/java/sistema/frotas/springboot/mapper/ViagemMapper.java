package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.viagem.ViagemRequest;
import sistema.frotas.springboot.dto.viagem.ViagemResponse;
import sistema.frotas.springboot.entities.Viagem;

@Mapper(componentModel = "spring")
public interface ViagemMapper {

    ViagemResponse toResponse (Viagem viagem);

    Viagem toEntity(ViagemRequest request);


}
