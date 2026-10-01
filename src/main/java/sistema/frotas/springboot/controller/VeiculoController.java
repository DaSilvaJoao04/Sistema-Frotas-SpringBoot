package sistema.frotas.springboot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.veiculo.VeiculoAtualizacaoRequest;
import sistema.frotas.springboot.dto.veiculo.VeiculoRequest;
import sistema.frotas.springboot.dto.veiculo.VeiculoResponse;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;
import sistema.frotas.springboot.services.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;


    @GetMapping
    public List<VeiculoResponse> listarVeiculos(){
        return veiculoService.findAll();

    }

    @GetMapping("/{id}")
    public VeiculoResponse buscarPorId(@PathVariable Long id){
        return veiculoService.buscarPorId(id);

    }

    @GetMapping("/placa/{placa}")
    public VeiculoResponse buscarPelaPlaca(@PathVariable String placa){
        return veiculoService.buscarPelaPlaca(placa);
    }

    @GetMapping("/status/{status}")
    public List<VeiculoResponse> buscarPorStatus(@PathVariable StatusVeiculo status){
        return veiculoService.buscarPorStatus(status);

    }

    @GetMapping("/combustivel/{combustivel}")
    public List<VeiculoResponse> buscarPorCombustivel(@PathVariable TipoCombustivel combustivel){
        return veiculoService.buscarPorCombustivel(combustivel);

    }

    @GetMapping("/marca/{marca}")
    public List<VeiculoResponse> buscarPorMarca(@PathVariable String marca){
        return veiculoService.buscarPelaMarca(marca);

    }

    @GetMapping("/ano/{ano}")
    public List<VeiculoResponse> buscarPorAno(@PathVariable Integer ano){
        return veiculoService.buscarPorAnoFabricacao(ano);

    }

    @GetMapping("/filtro")
    public List<VeiculoResponse> buscarPorStatusECombustivel
            (@RequestParam StatusVeiculo status,
             @RequestParam TipoCombustivel combustivel ){

        return veiculoService.buscarPorStatusECombustivel(status, combustivel);

    }

    @GetMapping("/ano-a-partir-de")
    public List<VeiculoResponse> filtrarPorAno(@RequestParam Integer ano){
        return veiculoService.buscarPorAnoFabricacaoGreaterThanEqual(ano);

    }

    @GetMapping("/quilometragem-maior-que")
    public List<VeiculoResponse> filtrarPorKM(@RequestParam Long quilometragem){
        return veiculoService.buscarPorQuilometragemGreaterThan(quilometragem);

    }




    @PostMapping
    public ResponseEntity<VeiculoResponse> criarVeiculo(@Valid @RequestBody VeiculoRequest request){

        VeiculoResponse response = veiculoService.criarVeiculo(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @PatchMapping("/{id}")
    public VeiculoResponse atualizarVeiculo
            (@PathVariable Long id,
             @Valid @RequestBody VeiculoAtualizacaoRequest request){

        return veiculoService.atualizarVeiculo(id, request);

    }




    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarVeiculo(@PathVariable Long id){
        veiculoService.inativarVeiculo(id);

        return ResponseEntity.noContent().build();


    }









}
