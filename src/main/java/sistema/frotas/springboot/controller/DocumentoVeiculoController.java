package sistema.frotas.springboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.documentoVeiculo.AtualizarDocumentoRequest;
import sistema.frotas.springboot.dto.documentoVeiculo.DocumentoVeiculoRequest;
import sistema.frotas.springboot.dto.documentoVeiculo.DocumentoVeiculoResponse;
import sistema.frotas.springboot.enums.TipoDocumento;
import sistema.frotas.springboot.services.DocumentoVeiculoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/documentos")
@RequiredArgsConstructor
public class DocumentoVeiculoController {

    private final DocumentoVeiculoService documentoVeiculoService;


    @GetMapping
    public List<DocumentoVeiculoResponse> listarDocumentos(){
        return documentoVeiculoService.buscarTodosDocumentos();
    }

    @PostMapping
    public ResponseEntity<DocumentoVeiculoResponse> registrarDocumento(
            @Valid @RequestBody DocumentoVeiculoRequest request){

        DocumentoVeiculoResponse response = documentoVeiculoService.criarDocumento(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/atualizar")
    public DocumentoVeiculoResponse atualizarDocumento(@PathVariable Long id,
                                                       @Valid @RequestBody AtualizarDocumentoRequest request){

        return documentoVeiculoService.atualizarDocumento(id, request);
    }


    @GetMapping("/{id}")
    public DocumentoVeiculoResponse buscarPorId(@PathVariable Long id){
        return documentoVeiculoService.buscarPorId(id);

    }

    @GetMapping("/numero/{numeroDocumento}")
    public DocumentoVeiculoResponse buscarPorNumeroDocumento(@PathVariable String numeroDocumento){
        return documentoVeiculoService.buscarPorNumero(numeroDocumento);

    }

    @GetMapping("/tipo/{tipoDocumento}")
    public List<DocumentoVeiculoResponse> buscarPorTipoDocumento(@PathVariable TipoDocumento tipoDocumento){
        return documentoVeiculoService.buscarPorTipo(tipoDocumento);

    }

    @GetMapping("/veiculo/{veiculoId}")
    public List<DocumentoVeiculoResponse> buscarPorVeiculo(@PathVariable Long veiculoId){
        return documentoVeiculoService.buscarPorVeiculo(veiculoId);

    }

}
