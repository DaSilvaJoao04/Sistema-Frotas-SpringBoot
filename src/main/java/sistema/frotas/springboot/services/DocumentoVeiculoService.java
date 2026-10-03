package sistema.frotas.springboot.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.documentoVeiculo.AtualizarDocumentoRequest;
import sistema.frotas.springboot.dto.documentoVeiculo.DocumentoVeiculoRequest;
import sistema.frotas.springboot.dto.documentoVeiculo.DocumentoVeiculoResponse;
import sistema.frotas.springboot.entities.DocumentoVeiculo;
import sistema.frotas.springboot.enums.TipoDocumento;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.DocumentoVeiculoMapper;
import sistema.frotas.springboot.repositories.DocumentoVeiculoRepository;
import sistema.frotas.springboot.repositories.VeiculoRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoVeiculoService {

    private final DocumentoVeiculoRepository documentoVeiculoRepository;
    private final VeiculoRepository veiculoRepository;

    private final DocumentoVeiculoMapper documentoVeiculoMapper;


    public List<DocumentoVeiculoResponse> buscarTodosDocumentos(){

        return documentoVeiculoRepository.findAll().stream()
                .map(documentoVeiculoMapper::toResponse)
                .toList();

    }


    public DocumentoVeiculoResponse buscarPorId(Long id){

        DocumentoVeiculo documentoVeiculo = documentoVeiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Documento não localizado"));


        return documentoVeiculoMapper.toResponse(documentoVeiculo);


    }

    public DocumentoVeiculoResponse criarDocumento(DocumentoVeiculoRequest request){

        DocumentoVeiculo criarDocumento = documentoVeiculoMapper.toEntity(request);

        DocumentoVeiculo documentoSalvo = documentoVeiculoRepository.save(criarDocumento);

        return documentoVeiculoMapper.toResponse(documentoSalvo);



    }


    public DocumentoVeiculoResponse atualizarDocumento(Long id, AtualizarDocumentoRequest request){

        DocumentoVeiculo documentoVeiculo = documentoVeiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Documento não localizado"));

        if (request.dataValidade().isBefore(documentoVeiculo.getDataEmissao())){
            throw new IllegalArgumentException("Erro: A data de validade não pode ser anterior à data de emissão");
        }

        documentoVeiculo.setDataValidade(request.dataValidade());

        DocumentoVeiculo documentoAtualizado = documentoVeiculoRepository.save(documentoVeiculo);

        return documentoVeiculoMapper.toResponse(documentoAtualizado);


    }


    public DocumentoVeiculoResponse buscarPorNumero(String numeroDocumento){

        DocumentoVeiculo documentoVeiculo = documentoVeiculoRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Documento não localizado"));


        return documentoVeiculoMapper.toResponse(documentoVeiculo);

    }

    public List<DocumentoVeiculoResponse> buscarPorTipo(TipoDocumento tipoDocumento){

        return documentoVeiculoRepository.findByTipoDocumento(tipoDocumento).stream()
                .map(documentoVeiculoMapper::toResponse)
                .toList();

    }

    public List<DocumentoVeiculoResponse> buscarPorVeiculo(Long veiculoId) {

        if (!veiculoRepository.existsById(veiculoId)){
            throw new ResourceNotFoundException("Erro: Não foi localizado veículo com ID:" + veiculoId);
        }

        return documentoVeiculoRepository.findByVeiculoId(veiculoId).stream()
                .map(documentoVeiculoMapper::toResponse)
                .toList();
    }

}
