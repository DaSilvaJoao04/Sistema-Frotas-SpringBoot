package sistema.frotas.springboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.viagem.FinalizarViagemRequest;
import sistema.frotas.springboot.dto.viagem.ViagemRequest;
import sistema.frotas.springboot.dto.viagem.ViagemResponse;
import sistema.frotas.springboot.services.ViagemService;

import java.util.List;

@RestController
@RequestMapping("/viagens")
@RequiredArgsConstructor
public class ViagemController {

    private final ViagemService viagemService;


    @PostMapping
    public ResponseEntity<ViagemResponse> iniciarViagem(@Valid @RequestBody ViagemRequest request){

        ViagemResponse response = viagemService.criarViagem(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public List<ViagemResponse> listarViagens(){
        return viagemService.buscarTodasViagens();
    }

    @GetMapping("/{id}")
    public ViagemResponse buscarPorId(@PathVariable Long id){
        return viagemService.buscarViagemPorId(id);
    }

    @GetMapping("/veiculo/{veiculoId}")
    public List<ViagemResponse> buscarPorVeiculo(@PathVariable Long veiculoId){
        return viagemService.buscarViagemPorVeiculo(veiculoId);
    }

    @GetMapping("/motorista/{motoristaId}")
    public List<ViagemResponse> buscarPorMotorista(@PathVariable Long motoristaId){
        return viagemService.buscarViagemPorMotorista(motoristaId);
    }

    @PutMapping("/{id}/finalizar")
    public ViagemResponse finalizarViagem(@PathVariable Long id,
                                   @Valid @RequestBody FinalizarViagemRequest request){

        return viagemService.finalizarViagem(id, request);

    }


}
