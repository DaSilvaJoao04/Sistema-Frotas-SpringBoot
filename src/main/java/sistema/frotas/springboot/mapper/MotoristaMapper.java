package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.motorista.MotoristaRequest;
import sistema.frotas.springboot.dto.motorista.MotoristaResponse;
import sistema.frotas.springboot.entities.Motorista;

@Mapper(componentModel = "spring")
public interface MotoristaMapper {

    MotoristaResponse toResponse(Motorista motorista);

    Motorista toEntity(MotoristaRequest request);


}
