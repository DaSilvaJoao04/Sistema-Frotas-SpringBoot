package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoRequest;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoResponse;
import sistema.frotas.springboot.entities.Abastecimento;

@Mapper(componentModel = "spring")
public interface AbastecimentoMapper {

    AbastecimentoResponse toResponse (Abastecimento abastecimento);

    Abastecimento toEntity(AbastecimentoRequest request);

}
