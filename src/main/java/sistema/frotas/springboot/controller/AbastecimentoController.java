package sistema.frotas.springboot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoRequest;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoResponse;
import sistema.frotas.springboot.enums.TipoCombustivel;
import sistema.frotas.springboot.services.AbastecimentoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/abastecimentos")
@RequiredArgsConstructor
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;


    @GetMapping
    public List<AbastecimentoResponse> listarAbastecimentos(){
        return abastecimentoService.buscarTodosAbastecimentos();

    }

    @PostMapping
    public ResponseEntity<AbastecimentoResponse> abastecerVeiculo(@Valid @RequestBody  AbastecimentoRequest request){

        AbastecimentoResponse response = abastecimentoService.abastecerVeiculo(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public AbastecimentoResponse buscarPorId(@PathVariable Long id){
        return abastecimentoService.buscarAbastecimentoPorId(id);

    }

    @GetMapping("/veiculo/{veiculoId}")
    public List<AbastecimentoResponse> buscarPorVeiculo(@PathVariable Long veiculoId){
        return abastecimentoService.buscarPorVeiculo(veiculoId);

    }

    @GetMapping("/data/{data}")
    public List<AbastecimentoResponse> buscarPorData(@PathVariable LocalDate data){
        return abastecimentoService.buscarPorData(data);

    }

    @GetMapping("/combustivel/{tipoCombustivel}")
    public List<AbastecimentoResponse> buscarPorTipoCombustivel (@PathVariable TipoCombustivel tipoCombustivel){
        return abastecimentoService.buscarPorTipoCombustivel(tipoCombustivel);

    }




}
