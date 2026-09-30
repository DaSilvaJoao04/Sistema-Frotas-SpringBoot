package sistema.frotas.springboot.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.frotas.springboot.dto.multa.MultaRequest;
import sistema.frotas.springboot.dto.multa.MultaResponse;
import sistema.frotas.springboot.dto.multa.PagarMultaRequest;
import sistema.frotas.springboot.enums.StatusMulta;
import sistema.frotas.springboot.services.MultaService;


import java.util.List;

@RestController
@RequestMapping("/multas")
@RequiredArgsConstructor
public class MultaController {

    private final MultaService multaService;


    @GetMapping
    public List<MultaResponse> listarMultas(){

        return multaService.buscarTodasMultas();

    }

    @GetMapping("/{id}")
    public MultaResponse buscarMultaPorId(@PathVariable Long id){

        return multaService.buscarPorId(id);

    }

    @PostMapping
    public ResponseEntity<MultaResponse> registrarMulta(@Valid @RequestBody MultaRequest request){

        MultaResponse response = multaService.criarMulta(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/{id}/pagar")
    public MultaResponse pagarMulta(@PathVariable Long id,
                                    @Valid @RequestBody PagarMultaRequest request){

        return multaService.pagarMulta(id, request);

    }

    @GetMapping("/placa/{placa}")
    public List<MultaResponse> buscarPorPlaca(@PathVariable String placa){

        return multaService.buscarMultaPorPlaca(placa);


    }

    @GetMapping("/status/{statusMulta}")
    public List<MultaResponse> buscarPorStatus(@PathVariable StatusMulta statusMulta){

        return multaService.buscarMultaPorStatus(statusMulta);


    }




}
