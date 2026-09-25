package sistema.frotas.springboot.mapper;

import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.multa.MultaRequest;
import sistema.frotas.springboot.dto.multa.MultaResponse;
import sistema.frotas.springboot.entities.Multa;

@Mapper(componentModel = "spring")
public interface MultaMapper {

    MultaResponse toResponse(Multa multa);

    Multa toEntity(MultaRequest request);


}
