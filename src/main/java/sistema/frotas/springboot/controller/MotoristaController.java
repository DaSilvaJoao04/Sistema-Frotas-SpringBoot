package sistema.frotas.springboot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.motorista.MotoristaRequest;
import sistema.frotas.springboot.dto.motorista.MotoristaResponse;
import sistema.frotas.springboot.enums.CategoriaCNH;
import sistema.frotas.springboot.enums.StatusMotorista;
import sistema.frotas.springboot.services.MotoristaService;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
public class MotoristaController {

    private final MotoristaService motoristaService;


    @GetMapping
    public List<MotoristaResponse> listarMotoristas(){
        return motoristaService.buscarTodosMotoristas();

    }

    @PostMapping
    public ResponseEntity<MotoristaResponse> criarMotorista
            (@Valid @RequestBody MotoristaRequest request){

        MotoristaResponse response = motoristaService.criarMotorista(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public MotoristaResponse buscarPorId(@PathVariable Long id){
        return motoristaService.buscarMotoristaPorId(id);
    }

    @PutMapping("/{id}")
    public MotoristaResponse atualizarMotorista
            (@PathVariable Long id,
            @Valid @RequestBody MotoristaRequest request){

        return motoristaService.atualizarMotorista(id, request);

    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarMotorista(@PathVariable Long id){

        motoristaService.inativarMotorista(id);

        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{id}/afastar")
    public ResponseEntity<Void> afastarMotorista(@PathVariable Long id){

        motoristaService.afastarMotorista(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/status/{status}")
    public List<MotoristaResponse> buscarPorStatus(@PathVariable StatusMotorista status){

        return motoristaService.buscarMotoristaPorStatus(status);

    }

    @GetMapping("/categoria/{categoria}")
    public List<MotoristaResponse> buscarPorCategoria(@PathVariable CategoriaCNH categoria){

        return motoristaService.buscarMotoristaPorCategoria(categoria);

    }




}
