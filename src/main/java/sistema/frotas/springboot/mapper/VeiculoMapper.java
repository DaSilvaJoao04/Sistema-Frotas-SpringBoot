package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.veiculo.VeiculoRequest;
import sistema.frotas.springboot.dto.veiculo.VeiculoResponse;
import sistema.frotas.springboot.entities.Veiculo;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    VeiculoResponse toResponse(Veiculo veiculo);

    Veiculo toEntity(VeiculoRequest request);

}
