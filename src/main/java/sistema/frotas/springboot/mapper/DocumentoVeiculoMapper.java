package sistema.frotas.springboot.mapper;


import org.mapstruct.Mapper;
import sistema.frotas.springboot.dto.documentoVeiculo.DocumentoVeiculoRequest;
import sistema.frotas.springboot.dto.documentoVeiculo.DocumentoVeiculoResponse;
import sistema.frotas.springboot.entities.DocumentoVeiculo;


@Mapper(componentModel = "spring")
public interface DocumentoVeiculoMapper {

    DocumentoVeiculoResponse toResponse(DocumentoVeiculo documentoVeiculo);


    DocumentoVeiculo toEntity(DocumentoVeiculoRequest request);


}
