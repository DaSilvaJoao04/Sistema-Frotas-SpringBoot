package sistema.frotas.springboot.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.manutencao.FinalizarManutencaoRequest;
import sistema.frotas.springboot.dto.manutencao.ManutencaoRequest;
import sistema.frotas.springboot.dto.manutencao.ManutencaoResponse;
import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.TipoManutencao;
import sistema.frotas.springboot.services.ManutencaoService;

import java.util.List;

@RestController
@RequestMapping("/manutencoes")
@RequiredArgsConstructor
public class ManutencaoController {

    private final ManutencaoService manutencaoService;


    @GetMapping
    public List<ManutencaoResponse> listarManutencoes(){

        return manutencaoService.buscarTodasManutencoes();
    }

    @GetMapping("/{id}")
    public ManutencaoResponse buscarPorId(@PathVariable Long id){

        return manutencaoService.buscarPorId(id);
    }

    @GetMapping("/placa/{placa}")
    public List<ManutencaoResponse> buscarPorPlaca(@PathVariable String placa){

        return manutencaoService.buscarPorVeiculoPlaca(placa);
    }

    @GetMapping("/status/{statusManutencao}")
    public List<ManutencaoResponse> buscarPorStatus(@PathVariable StatusManutencao statusManutencao){

        return manutencaoService.buscarPorStatus(statusManutencao);
    }

    @GetMapping("/placa/{placa}/tipo/{tipoManutencao}")
    public List<ManutencaoResponse> buscarPorPlacaETipo(@PathVariable String placa,
                                                        @PathVariable TipoManutencao tipoManutencao){

        return manutencaoService.buscarPorVeiculoPlacaETipoManutencao(placa, tipoManutencao);
    }


    @PostMapping
    public ResponseEntity<ManutencaoResponse> iniciarManutencao(@Valid @RequestBody ManutencaoRequest request){

        ManutencaoResponse response = manutencaoService.iniciarManutencao(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @PatchMapping("/{id}/encerrar")
    public ManutencaoResponse encerrarManutencao(@PathVariable Long id,
                                                 @Valid @RequestBody FinalizarManutencaoRequest request){
        return manutencaoService.encerrarManutencao(id, request);

    }

    @PatchMapping("/{id}/cancelar")
    public ManutencaoResponse cancelarManutencao(@PathVariable Long id){
        return manutencaoService.cancelarManutencao(id);

    }



}
